package UnitTest;


import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import Constants.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetCustomer extends Constants {
	
	static String customerNum = "12434";
	
	public static void getCustomer() {
		
		
	}
	
	@Test()
	public void testGetCustomer() {
		//http://carp.parasoft.com:80/parabank/services/bank/customers/12434/accounts
		
		RestAssured.baseURI = "https://parabank.parasoft.com";
		String resource = "/parabank/services/bank/customers/"+customerNum+"/accounts";
		RequestSpecification httpRequest = RestAssured.given();
		
		httpRequest.accept(ContentType.JSON);
				
		Response response = httpRequest.get(resource);
		
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody().asString());
		
		String jsonresponse = response.getBody().asString();
	  
		JSONArray array = new JSONArray(jsonresponse);

		System.out.println("length : "+array.length());
		
		for(int i = 0; i<array.length();i++) {
			
			Object array0 = array.get(i);
			String arrayToStringArray = array0.toString();

			JSONObject object = new JSONObject(arrayToStringArray);
			String[] keys = JSONObject.getNames(object);
			System.out.println("json object index : "+i+" ***************************");

			for (String key : keys)
			{
			    Object value = object.get(key);
			    System.out.println("key : "+key +" "+ "value : "+value);
				
			}

			
		}
		
	
	}

}
