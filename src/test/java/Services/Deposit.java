package Services;

import java.io.IOException;

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

public class Deposit extends Constants {

	
	
	public static void makeDeposit_SOAPRequest(String accountID, String amount) throws ClientProtocolException, IOException, SAXException, ParserConfigurationException {
		
		
		requestBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.parabank.parasoft.com/\">\n" + 
				"   <soapenv:Header/>\n" + 
				"   <soapenv:Body>\n" + 
				"      <ser:deposit>\n" + 
				"         <ser:accountId>"+accountID+"</ser:accountId>\n" + 
				"         <ser:amount>"+amount+"</ser:amount>\n" + 
				"      </ser:deposit>\n" + 
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

		System.out.println("Soap makeDeposit_SOAPRequest response Code is ::" + responseStatusCode);
		System.out.println("Soap makeDeposit_SOAPRequest response body = " + responseBody);


		String id = Lib.returnxmlvalue(responseBody, "id", 0);
	}
}
