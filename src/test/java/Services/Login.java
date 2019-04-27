package Services;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xml.sax.SAXException;

import Constants.Constants;
import Util.Lib;

public class Login extends Constants {
	
	
	public static HashMap<String,String> login_Request(String username, String password) throws ClientProtocolException, IOException, SAXException, ParserConfigurationException {
		
		
		endpoint = "https://parabank.parasoft.com/parabank/services/ParaBank";
		
		System.out.println("account id is : " + accountID);
		
		requestBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.parabank.parasoft.com/\">\n" + 
				"   <soapenv:Header/>\n" + 
				"   <soapenv:Body>\n" + 
				"      <ser:login>\n" + 
				"         <ser:username>"+username+"</ser:username>\n" + 
				"         <ser:password>"+password+"</ser:password>\n" + 
				"      </ser:login>\n" + 
				"   </soapenv:Body>\n" + 
				"</soapenv:Envelope>";

		stringEntity = new StringEntity(requestBody, "UTF-8");
		stringEntity.setChunked(true);
		httpPost = new HttpPost(endpoint);
		httpPost.setEntity(stringEntity);
		httpClient = new DefaultHttpClient();
		response = null;
		response = httpClient.execute(httpPost);
		HttpEntity entity = response.getEntity();
		responseStatusCode = response.getStatusLine().getStatusCode();
		responseBody = EntityUtils.toString(entity);
		
		
		System.out.println("Soap getAccount response Code is ::" + responseStatusCode);
		System.out.println("Soap getAccount response body = " + responseBody);
		
		String actualID = Lib.returnxmlvalue(responseBody, "id", 0);
		String firstName = Lib.returnxmlvalue(responseBody, "firstName", 0);
		String lastName = Lib.returnxmlvalue(responseBody, "lastName", 0);
		String street = Lib.returnxmlvalue(responseBody, "street", 0);
		String city = Lib.returnxmlvalue(responseBody, "city", 0);
		String state = Lib.returnxmlvalue(responseBody, "state", 0);
		String zipCode = Lib.returnxmlvalue(responseBody, "zipCode", 0);
		String phoneNumber = Lib.returnxmlvalue(responseBody, "phoneNumber", 0);
		String ssn = Lib.returnxmlvalue(responseBody, "ssn", 0);
		
		login_ResponseMap.put("id", actualID);
		login_ResponseMap.put("firstName", firstName);
		login_ResponseMap.put("lastName", lastName);
		login_ResponseMap.put("street", street);
		login_ResponseMap.put("city", city);
		login_ResponseMap.put("state", state);
		login_ResponseMap.put("zipCode", zipCode);
		login_ResponseMap.put("phoneNumber", phoneNumber);
		login_ResponseMap.put("ssn", ssn);
	
		return login_ResponseMap;
			
		
	}

}
