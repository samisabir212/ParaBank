package Runners;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Constants.Constants;
import Util.Lib;
import WebDriverManager.WebDriverManager;

public class ParaBank_Runner_Exec_ALL_ENVIRONMENTS {
	
	
	@BeforeSuite
	public void beforeSuite() throws Exception {

		// define and assign environmen variables found in POM.xml
		Constants.RELEASE_NUMBER = System.getProperty("releaseNumber");
		Constants.companyUsername = System.getProperty("Username");
		Constants.rcTicket = System.getProperty("rcticket");

		// hardcoding enviornment and username for now :
		//Constants.Environment = "STAGING";

		System.out.println("ENV under test : " + Constants.Environment);
		
		// create results folder
		Constants.path = Lib.CreateRunResultFolder(Constants.companyUsername);
		System.out.println("results folder was create in this path : " + Constants.path);

		// define log path
		Constants.logpath = Constants.path + "/Logs";
		System.out.println("Path to logs folder is : " + Constants.logpath);

		// create log folders
		Lib.createParaBankLogFolders();

		// define variable results log folder path
		Constants.PARABANK_RESULTS_LOG_FOLDER_PATH = "/Users/sami/Desktop/Automation/Parabank/Runs"
				+ Lib.getcurrentdatefolder() + Constants.Environment + "/Logs";

		// create and write column names for excel result
		Constants.runResultsFileName = Constants.path + "/RunResults_PARABANK.xlsx";
		Lib.create_SetupExcelResultsSheet(Constants.runResultsFileName);
		Lib.setPermissions3(Constants.runResultsFileName);

		
	}

	@Parameters({ "osType", "browserType", "runLocal", "useGrid", "useAWS", "useSauceLabs","accountStatus","environment" })
	@BeforeClass
	public void beforClass(@Optional String osType, @Optional String browserType, @Optional String runLocal,
			@Optional String useGrid, @Optional String useAWS, @Optional String useSauceLabs,@Optional String accountStatus,String environment) throws IOException {

		Constants.ACCOUNTSTATUS = accountStatus;
		Constants.Environment = environment;
		getEnvironmentEndpoint();
		getParaBankTestData();
		
		
		
		if (runLocal.equalsIgnoreCase("true")) {

			WebDriverManager.getLocalDriver(osType, browserType);

		} else if (useGrid.equalsIgnoreCase("true")) {

			WebDriverManager.launchSeleniumGrid(osType, browserType);

		} else if (useSauceLabs.equalsIgnoreCase("true")) {

			
		}
		

	}

	
	public static void getEnvironmentEndpoint() throws IOException {
		
		// create if else blocks for enviornment endpoints
				if (Constants.Environment.equalsIgnoreCase("DEV")) {

					Constants.EndpointRunner = Lib.readproperties("DEV_BASEURL");

				} else if (Constants.Environment.equalsIgnoreCase("STAGING")) {

					Constants.EndpointRunner = Lib.readproperties("STAGING_BASEURL");

				} else if (Constants.Environment.equalsIgnoreCase("FT")) {
					
					Constants.EndpointRunner = Lib.readproperties("FT_BASEURL");

				} else if (Constants.Environment.equalsIgnoreCase("SIT")) {
					
					Constants.EndpointRunner = Lib.readproperties("SIT_BASEURL");

				} else if (Constants.Environment.equalsIgnoreCase("PERF")) {
					
					Constants.EndpointRunner = Lib.readproperties("PERF_BASEURL");
					
				}else if (Constants.Environment.equalsIgnoreCase("PROD")) {
					
					Constants.EndpointRunner = Lib.readproperties("PROD_BASEURL");
				}
	}
	
	
	@AfterClass()
	public void afterTest() {

		System.out.println("quiting browser");
		//WebDriverManager.driver.close();
		WebDriverManager.driver.quit();

	}

	@AfterSuite
	public void afterSuite()
			throws IOException, InterruptedException, EncryptedDocumentException, InvalidFormatException {

		// do something after all the tests have finished running like count the total

		// Generate total test cases that passed and failed
//		String excelFilePath = Constants.path + "/RunResults_PARABANK.xlsx";
//
//		System.out.println("Excel results file path : "+excelFilePath);
//		HashMap<String,Integer> resultsMap = Lib.getStatusDataReport(excelFilePath);
//		Integer passed = resultsMap.get("Passed");
//		Integer failed = resultsMap.get("Failed");
//		Integer totalTestCases = resultsMap.get("Passed") + resultsMap.get("Failed");
//		
//		System.out.println("Total Passed : "+passed);
//		System.out.println("Total Failed : "+failed);
//		System.out.println("TotalTestCases : "+totalTestCases);
//		
//		Lib.excelwrite(Constants.runResultsFileName,
//				new Object[] { Lib.getcurrentdate(), null, null, null, null,
//						"Total Test Cases", "Passed TC's", "Failed TC's", null, null, null, null,
//						null, null, null, null, null, null });
//		Lib.excelwrite(Constants.runResultsFileName,
//				new Object[] { Lib.getcurrentdate(), null, null, null, null,
//						totalTestCases, passed, failed, null, null, null, null,
//						null, null, null, null, null, null });
//		

	}

	public static void getParaBankTestData() throws IOException {

		int matchRowNum = Lib.return_Match_RowNum_By_ENVIRONMENT_value(Constants.Environment);

		Constants.testData.put("firstname", Lib.readexcelvalue_ParaBankTestData(matchRowNum, 3));
		Constants.testData.put("lastname", Lib.readexcelvalue_ParaBankTestData(matchRowNum, 4));
		Constants.testData.put("address", Lib.readexcelvalue_ParaBankTestData(matchRowNum, 5));
		Constants.testData.put("city", Lib.readexcelvalue_ParaBankTestData(matchRowNum, 6));
		Constants.testData.put("state", Lib.readexcelvalue_ParaBankTestData(matchRowNum, 7));
		Constants.testData.put("zipcode", Lib.readexcelvalue_ParaBankTestData(matchRowNum, 8));
		Constants.testData.put("phonenumber", Lib.readexcelvalue_ParaBankTestData(matchRowNum, 9));
		Constants.testData.put("ssn", Lib.readexcelvalue_ParaBankTestData(matchRowNum, 10));
		Constants.testData.put("username", Lib.readexcelvalue_ParaBankTestData(matchRowNum, 1));
		Constants.testData.put("password", Lib.readexcelvalue_ParaBankTestData(matchRowNum, 2));

		Constants.FIRSTNAME = Constants.testData.get("firstname");
		Constants.LASTNAME = Constants.testData.get("lastname");
		Constants.ADDRESS = Constants.testData.get("address");
		Constants.CITY = Constants.testData.get("city");
		Constants.STATE = Constants.testData.get("state");
		Constants.ZIPCODE = Constants.testData.get("zipcode");
		Constants.PHONENUM = Constants.testData.get("phonenumber");
		Constants.SSN = Constants.testData.get("ssn");
		Constants.USERNAME = Constants.testData.get("username");
		Constants.PASSWORD = Constants.testData.get("password");


	}
	
	
	

}
