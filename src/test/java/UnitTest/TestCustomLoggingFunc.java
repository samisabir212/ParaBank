package UnitTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.testng.annotations.Test;

public class TestCustomLoggingFunc {
	
	@Test(enabled = true)
	public void testLogger() throws IOException {
		
		//createWithdrawlogfile();
		log("1");
		log("2");
		log("3");
		log("hey tanks for adding me");
		log("omg again");
		log("awesome");




		
	}
	
	public static void createWithdrawlogfile() throws IOException {

	      File file = new File("/Users/sami/Desktop/Automation/sidrissi/test3.txt");   

	      
	      if(!file.exists()) {
		      file.createNewFile(); // create the file
	      }else {
	    	  System.out.println("file already exists : "+file.getAbsolutePath().toString());
	      }
	}
	
	public static void log(String strLine) throws IOException {

	      
	      File file = new File("/Users/sami/Desktop/Automation/sidrissi/test4.txt");   
	      FileWriter fileWriter = new FileWriter(file,true);
	      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	      
	      bufferedWriter.write(strLine+"\n");
	      bufferedWriter.close();
	      fileWriter.close();
	}
	
	
	/**
	 * PrintWriter out = new PrintWriter(resultsLogPath + "\\" + serviceName
				+ "\\" + testname + ".txt");
		out.println("Request:");
		out.println("");
		out.println("Endpint is :: " + endpoint);
		out.println("");
		out.println(headerinfo);
		out.println("");
		out.println(parameter);
		out.println("");

		out.println("");
		out.println("************************************************************************************");
		out.println("Response:");
		out.println("");
		out.println("HTTP/1.1  " + response.statusCode() + " OK");
		out.println(response.getHeaders());
		out.println("");
		out.println(response.body().asString());
		out.close();
	 */
	

}
