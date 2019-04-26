package UnitTest;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import Constants.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAccount extends Constants {
	

	
	@Test(enabled= true)
	public void getAccountRequestHTTPCLIENT() throws ClientProtocolException, IOException {
		
		String Endpoint = "https://parabank.parasoft.com/parabank/services/ParaBank";
		
		
		String getAccountRequestBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.parabank.parasoft.com/\">\n" + 
				"   <soapenv:Header/>\n" + 
				"   <soapenv:Body>\n" + 
				"      <ser:getAccount>\n" + 
				"         <ser:accountId>13899</ser:accountId>\n" + 
				"      </ser:getAccount>\n" + 
				"   </soapenv:Body>\n" + 
				"</soapenv:Envelope>";
		
		
		
		StringEntity stringEntity = new StringEntity(getAccountRequestBody, "UTF-8");
		stringEntity.setChunked(true);
		HttpPost httpPost = new HttpPost(Endpoint);
		httpPost.setEntity(stringEntity);
		HttpClient httpClient = null;
		httpClient = new DefaultHttpClient();
		httpResponse = null;
		httpResponse = httpClient.execute(httpPost);
		entity = response.getEntity();
		responseStatusCode = response.getStatusLine().getStatusCode();
		responseBody = EntityUtils.toString(entity);
		
		System.out.println("Soap getAccount response Code is ::" + responseStatusCode);
		System.out.println("Soap getAccount response body = " + responseBody);
		
		
		
		getAccount_ResponseMap.put("id", "");
		getAccount_ResponseMap.put("customerId", "");
		getAccount_ResponseMap.put("type", "");
		getAccount_ResponseMap.put("balance", "");

		
	}	

}
