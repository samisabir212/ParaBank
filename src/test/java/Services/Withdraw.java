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

public class Withdraw extends Constants {

	public static String withdraw_Request_For_SuccessfulWithdrawScenario(String accountID, String withdrawAmount)
			throws ClientProtocolException, IOException, SAXException, ParserConfigurationException {

		endpoint = "https://parabank.parasoft.com/parabank/services/ParaBank";

		requestBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.parabank.parasoft.com/\">\n"
				+ "   <soapenv:Header/>\n" + "   <soapenv:Body>\n" + "      <ser:withdraw>\n"
				+ "         <ser:accountId>" + accountID + "</ser:accountId>\n" + "         <ser:amount>"
				+ withdrawAmount + "</ser:amount>\n" + "      </ser:withdraw>\n" + "   </soapenv:Body>\n"
				+ "</soapenv:Envelope>";

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

		System.out.println("Soap withdraw_Request response Code is ::" + responseStatusCode);
		System.out.println("Soap withdraw_Request response body = " + responseBody);

//		<ns2:withdrawReturn>Successfully withdrew $115 from account #13899</ns2:withdrawReturn>	

		String withdrawReturn = Lib.returnxmlvalue(responseBody, "ns2:withdrawReturn", 0);

		System.out.println("ns2:withdrawReturn text : " + withdrawReturn);

		expectedWithdrawAmount = "$" + withdrawAmount;
		actualWithdrawAmount = withdrawReturn.split("drew ")[1].split(" from")[0];
		System.out.println("expectedWithdrawAmount : " + expectedWithdrawAmount);
		System.out.println("actualWithdrawAmount : "+ actualWithdrawAmount);
		return expectedWithdrawAmount;

	}

}
