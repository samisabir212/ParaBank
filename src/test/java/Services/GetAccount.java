package Services;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.openqa.selenium.support.PageFactory;
import org.xml.sax.SAXException;

import Constants.Constants;
import PageObjects.OverviewPage;
import Util.Lib;
import WebDriverManager.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAccount extends Constants {

	
	public GetAccount() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}


	
	public static HashMap<String,String> getAccount_Request(String accountID) throws ClientProtocolException, IOException, SAXException, ParserConfigurationException {

		endpoint = "https://parabank.parasoft.com/parabank/services/ParaBank";
				
		System.out.println("account id is : " + accountID);
		requestBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.parabank.parasoft.com/\">\n"
				+ "   <soapenv:Header/>\n" + "   <soapenv:Body>\n" + "      <ser:getAccount>\n"
				+ "         <ser:accountId>"+accountID+"</ser:accountId>\n" + "      </ser:getAccount>\n"
				+ "   </soapenv:Body>\n" + "</soapenv:Envelope>";

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
		String actualCustomerID = Lib.returnxmlvalue(responseBody, "customerId", 0);
		String actualType = Lib.returnxmlvalue(responseBody, "type", 0);
		String actualBalance = Lib.returnxmlvalue(responseBody, "balance", 0);

		
		getAccount_ResponseMap.put("id", actualID);
		getAccount_ResponseMap.put("customerId", actualCustomerID);
		getAccount_ResponseMap.put("type", actualType);
		getAccount_ResponseMap.put("balance", actualBalance);
		
		return getAccount_ResponseMap;
			
	}

}
