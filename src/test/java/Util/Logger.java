package Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import Constants.Constants;

public class Logger {
	
	///Users/sami/Desktop/Automation/sidrissi/Parabank/Runs/2019_04_08_1046STAGING/Logs/Withdraw
	
	
	public static void createlogfile(String LogsPath, String ServiceName, String logFileName) throws IOException {

		//LogsPath+"/"+ServiceName+"/"+logFileName+".txt"
	      File file = new File("/Users/sami/Desktop/Automation/sidrissi/test3.txt");   

	      
	      if(!file.exists()) {
		      file.createNewFile(); // create the file
	      }else {
	    	  System.out.println("file already exists : "+file.getAbsolutePath().toString());
	      }
	}
	
	
	
	public static void log(String strLine,String ServiceName, String logFileName) throws IOException {

	      
	      File file = new File(Constants.logpath+"/"+ServiceName+"/"+logFileName+".txt");   
	      System.out.println(file.getAbsolutePath().toString());
	      FileWriter fileWriter = new FileWriter(file,true);
	      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	      
	      bufferedWriter.write(strLine+"\n");
	      bufferedWriter.close();
	      fileWriter.close();
	}
	

}
