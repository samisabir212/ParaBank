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

public class GetAccounts extends Constants  {
	
	/**
	 * <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://service.parabank.parasoft.com/">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:getAccounts>
         <ser:customerId>12434</ser:customerId>
      </ser:getAccounts>
   </soapenv:Body>
</soapenv:Envelope>
	 */
	
	
	public static HashMap<String,String> getAccounts_Request(String customerId) throws ClientProtocolException, IOException, SAXException, ParserConfigurationException {

		endpoint = "https://parabank.parasoft.com/parabank/services/ParaBank";
				
		requestBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.parabank.parasoft.com/\">\n" + 
				"   <soapenv:Header/>\n" + 
				"   <soapenv:Body>\n" + 
				"      <ser:getAccounts>\n" + 
				"         <ser:customerId>"+customerId+"</ser:customerId>\n" + 
				"      </ser:getAccounts>\n" + 
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
		
		
		System.out.println("Soap getAccounts response Code is ::" + responseStatusCode);
		System.out.println("Soap getAccounts response body = " + responseBody);
		
	
		
		String actualID = Lib.returnxmlvalue(responseBody, "id", 0);
		String actualCustomerID = Lib.returnxmlvalue(responseBody, "customerId", 0);
		String actualType = Lib.returnxmlvalue(responseBody, "type", 0);
		String actualBalance = Lib.returnxmlvalue(responseBody, "balance", 0);

		
		getAccounts_ResponseMap.put("id", actualID);
		getAccounts_ResponseMap.put("customerId", actualCustomerID);
		getAccounts_ResponseMap.put("type", actualType);
		getAccounts_ResponseMap.put("balance", actualBalance);
		
		return getAccounts_ResponseMap;
			
	}

}
