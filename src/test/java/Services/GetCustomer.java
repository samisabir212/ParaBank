package Services;

import java.util.HashMap;

import Constants.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetCustomer extends Constants {
	
	public static HashMap<String,String> getCustomerInfoMap = new HashMap<String,String>();
	
	
	
	public static HashMap<String,String> getCustomerInfo(String customerID){
		
		
		endpoint = EndpointRunner+GETCUSTOMER_URI;
		
		RequestSpecification request = RestAssured.given();
		
		requestBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.parabank.parasoft.com/\">\n" + 
				"   <soapenv:Header/>\n" + 
				"   <soapenv:Body>\n" + 
				"      <ser:getCustomer>\n" + 
				"         <ser:customerId>"+customerID+"</ser:customerId>\n" + 
				"      </ser:getCustomer>\n" + 
				"   </soapenv:Body>\n" + 
				"</soapenv:Envelope>";
		
		request.body(requestBody);
		
		Response response = request.post(endpoint);
		responseStatusCode = response.getStatusCode();
		responseBody = response.getBody().asString();
		
		System.out.println("getCustomer status code : "+ responseStatusCode);
		System.out.println("getCustomer responseBody : "+ responseBody);
		
		
		
		return null;
	}
	

}
