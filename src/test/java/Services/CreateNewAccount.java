package Services;

import Constants.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateNewAccount extends Constants {

	public static void createNewAccount(String customerID, String newAccountType, String fromAccountID) {
		
		System.out.println("~~~Create new account test~~~");
		endpoint = EndpointRunner+ REGISTER_URI;
		System.out.println("createNewAccount Endpoint is : "+ endpoint);
		RequestSpecification request = RestAssured.given();
		
		String xmlRequest = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.parabank.parasoft.com/\">\n" + 
				"   <soapenv:Header/>\n" + 
				"   <soapenv:Body>\n" + 
				"      <ser:createAccount>\n" + 
				"         <ser:customerId>"+customerID+"</ser:customerId>\n" + 
				"         <ser:newAccountType>"+newAccountType+"</ser:newAccountType>\n" + 
				"         <ser:fromAccountId>"+fromAccountID+"</ser:fromAccountId>\n" + 
				"      </ser:createAccount>\n" + 
				"   </soapenv:Body>\n" + 
				"</soapenv:Envelope>";
		
		request.body(xmlRequest);
		
		Response response = request.post(endpoint);
		
		 responseBody = response.getBody().asString();
		 responseStatusCode = response.getStatusCode();
		 System.out.println("Create new account response status code : "+ responseStatusCode);
		 System.out.println("createNewAccount_ResponseBody : "+responseBody);
		 
		 
		
	}
	
}
