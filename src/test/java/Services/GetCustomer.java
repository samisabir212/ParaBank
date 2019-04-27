package Services;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Constants.Constants;
import Util.Lib;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetCustomer extends Constants {
	
	
	
	
	public static HashMap<String,String> getCustomerInfo_Request(String customerID) throws SAXException, IOException, ParserConfigurationException{
		
		
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
		
		String actualID = Lib.returnxmlvalue(responseBody, "id", 0);
		String firstName = Lib.returnxmlvalue(responseBody, "firstName", 0);
		String lastName = Lib.returnxmlvalue(responseBody, "lastName", 0);
		String street = Lib.returnxmlvalue(responseBody, "street", 0);
		String city = Lib.returnxmlvalue(responseBody, "city", 0);
		String state = Lib.returnxmlvalue(responseBody, "state", 0);
		String zipCode = Lib.returnxmlvalue(responseBody, "zipCode", 0);
		String phoneNumber = Lib.returnxmlvalue(responseBody, "phoneNumber", 0);
		String ssn = Lib.returnxmlvalue(responseBody, "ssn", 0);
		
		getCustomer_ResponseMap.put("id", actualID);
		getCustomer_ResponseMap.put("firstName", firstName);
		getCustomer_ResponseMap.put("lastName", lastName);
		getCustomer_ResponseMap.put("street", street);
		getCustomer_ResponseMap.put("city", city);
		getCustomer_ResponseMap.put("state", state);
		getCustomer_ResponseMap.put("zipCode", zipCode);
		getCustomer_ResponseMap.put("phoneNumber", phoneNumber);
		getCustomer_ResponseMap.put("ssn", ssn);

		return getCustomer_ResponseMap;
	}
	

}
