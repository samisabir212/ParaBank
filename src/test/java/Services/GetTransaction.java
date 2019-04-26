package Services;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.xml.sax.SAXException;

import Constants.Constants;
import Util.Lib;

public class GetTransaction extends Constants {

	
	public static HashMap<String,String> getTransaction_Request(String transactionID) throws SAXException, IOException, ParserConfigurationException {
		
		
		endpoint = "https://parabank.parasoft.com/parabank/services/ParaBank";

		requestBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.parabank.parasoft.com/\">\n" + 
				"   <soapenv:Header/>\n" + 
				"   <soapenv:Body>\n" + 
				"      <ser:getTransaction>\n" + 
				"         <ser:transactionId>"+transactionID+"</ser:transactionId>\n" + 
				"      </ser:getTransaction>\n" + 
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

		System.out.println("Soap getTransaction_Request response Code is ::" + responseStatusCode);
		System.out.println("Soap getTransaction_Request response body = " + responseBody);


		String id = Lib.returnxmlvalue(responseBody, "id", 0);
		String accountId = Lib.returnxmlvalue(responseBody, "accountId", 0);
		String type = Lib.returnxmlvalue(responseBody, "type", 0);
		String date = Lib.returnxmlvalue(responseBody, "date", 0);
		String amount = Lib.returnxmlvalue(responseBody, "amount", 0);

		getTransaction_ResponseMap.put("id", id);
		getTransaction_ResponseMap.put("accountId", accountId);
		getTransaction_ResponseMap.put("type", type);
		getTransaction_ResponseMap.put("date", date);
		getTransaction_ResponseMap.put("amount", amount);
		
		
		return getTransaction_ResponseMap;
			
	}
	
}
