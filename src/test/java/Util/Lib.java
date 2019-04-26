package Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import Constants.Constants;
import PageObjects.BasePage;

public class Lib {

	public Lib() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static String projpath = null;
	
	
	public void executeCommandLine(String cmd) {
		
		try {
			
			String command = cmd;
			Process process = Runtime.getRuntime().exec(command);
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("couldnt execute the command");
		}
		
	}
	
	
	public static void generateRandomUsername() {
		// ~~~~~ make username unique by appendning random string just for testing
		// purposes so we wont get "User already created error message" ~~~~~
		String randomString = Lib.generateRandomString();
		
		Constants.RANDOMUSERNAME = Constants.USERNAME + randomString;

		System.out.println("**********************************");
		System.out.println("random string : " + randomString);
		System.out.println("random username : " + Constants.RANDOMUSERNAME);
		System.out.println("**********************************");

		
	}

	public static String returnxmlvalue(String xml, String key, int i)
			throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource src = new InputSource();
		src.setCharacterStream(new StringReader(xml));

		Document doc = builder.parse(src);
		String value = doc.getElementsByTagName(key).item(i).getTextContent();
		return value;
	}

	public static void createParaBankLogFolders() {

		// create log folder
		Lib.createLogFolder(Constants.logpath);

		// define all log folder name by service name or scenario name
		String Registration = Constants.path + "/Logs/Registration";
		String CreateNewAccount = Constants.path + "/Logs/CreateNewAccount";
		String Withdraw = Constants.path + "/Logs/Withdraw";
		String Deposit = Constants.path + "/Logs/Deposit";
		String TransferFunds = Constants.path + "/Logs/TransferFunds";

		// create service/scenario log folder
		Lib.createLogFolder_for_Service(Registration, "Registration");
		Lib.createLogFolder_for_Service(CreateNewAccount, "CreateNewAccount");
		Lib.createLogFolder_for_Service(Withdraw, "Withdraw");
		Lib.createLogFolder_for_Service(Deposit, "Deposit");
		Lib.createLogFolder_for_Service(Registration, "Registration");
		Lib.createLogFolder_for_Service(TransferFunds, "TransferFunds");

	}

	public static void setPermissions2(String strPath) {
		String path = strPath;
		File file = new File(path);
		if (file.exists()) {
			System.out.println("read=" + file.canRead());
			System.out.println("write=" + file.canWrite());
			System.out.println("Execute=" + file.canExecute());
			file.setWritable(true);
			file.setReadable(true);
			file.setExecutable(true);
		}
	}

	public static void setPermissions3(String strPath) {

		final File file = new File(strPath);
		System.out.println("file path to change permissions : " + strPath);
		file.setReadable(true, false);
		file.setExecutable(true, false);
		file.setWritable(true, false);
	}

	public static void setPermission(File file) throws IOException {
		Set<PosixFilePermission> perms = new HashSet<>();
		perms.add(PosixFilePermission.OWNER_READ);
		perms.add(PosixFilePermission.OWNER_WRITE);
		perms.add(PosixFilePermission.OWNER_EXECUTE);

		perms.add(PosixFilePermission.OTHERS_READ);
		perms.add(PosixFilePermission.OTHERS_WRITE);
		perms.add(PosixFilePermission.OTHERS_EXECUTE);

		perms.add(PosixFilePermission.GROUP_READ);
		perms.add(PosixFilePermission.GROUP_WRITE);
		perms.add(PosixFilePermission.GROUP_EXECUTE);

		Files.setPosixFilePermissions(file.toPath(), perms);
	}

	public static String generateRandomString() {
		String mychars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder stringBuildr = new StringBuilder();
		Random rnd = new Random();
		while (stringBuildr.length() < 3) { // length of the random string.
			int index = (int) (rnd.nextFloat() * mychars.length());
			stringBuildr.append(mychars.charAt(index));
		}

		String characters = stringBuildr.toString();
		String string = "0";
		StringBuilder stringBuilder = new StringBuilder(string);

		String randomString = stringBuilder.append(characters).toString();

		return randomString;

	}

	public static String readexcelvalue_ParaBankTestData(int rownm, int colnm) throws IOException {

		XSSFSheet sht = connectParaBankTestDataSheet();
		XSSFRow rw = sht.getRow(rownm);
		XSSFCell cell = rw.getCell(colnm);

		cell.setCellType(Cell.CELL_TYPE_STRING);
		System.out.println(cell.getStringCellValue());

		return cell.getStringCellValue();

	}

	public static int return_Match_RowNum_By_ENVIRONMENT_value(String env) throws IOException {

		int i = 0;

		XSSFSheet sht = connectParaBankTestDataSheet();

		for (i = 0; i < sht.getPhysicalNumberOfRows(); i++) { // check each row
			XSSFRow rw = sht.getRow(i);
			if (returncellvalue(rw, 0).equalsIgnoreCase(env)) {
				System.out.println("we found matching row number   " + i);
				break;
			}
		}

		return i;
	}

	public static XSSFSheet connectParaBankTestDataSheet() throws IOException {

		String paraBankTestDatPath = "/Users/sami/Desktop/Automation/sidrissi/Parabank/Properties/ParaBankTestData.xlsx";
		setPermissions2(paraBankTestDatPath);

		File fl = new File(paraBankTestDatPath);

		FileInputStream fis = new FileInputStream(fl);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sht = wb.getSheetAt(0);
		return sht;
	}

	public static String returncellvalue(XSSFRow rw, int colnum) {

		XSSFCell cll = rw.getCell(colnum);

		return cll.getStringCellValue();

	}

	public static String getBrowserDriverPathProperties(String key) throws IOException {
		File fl = new File(
				"/Users/sami/Desktop/GitTechTekSolution/ParaBank/src/test/resources/Properties/BrowserDriverPath.properties/browserVersionsFilePath.properties");
		FileInputStream fio = new FileInputStream(fl);
		Properties prop = new Properties();
		prop.load(fio);

		return prop.getProperty(key);

	}

	public static String getEndpointProperties(String key) throws IOException {
		File fl = new File("src/resources/java/Properties/Endpoints/Endpoints.properties");
		FileInputStream fio = new FileInputStream(fl);
		Properties prop = new Properties();
		prop.load(fio);

		return prop.getProperty(key);
	}

	public static String CreateRunResultFolder(String testerDirName) throws IOException {

		projpath = "/Users/sami/Desktop/Automation/" + testerDirName + "/Parabank/Runs/";

		/// Users/sami/Desktop/Automation/sidrissi/Parabank/Runs
		File dir = new File(projpath + getcurrentdatefolder() + Constants.Environment);

		System.out.println("prject path : " + projpath + getcurrentdatefolder() + Constants.Environment);
		dir.mkdir();

		return dir.getAbsolutePath();
	}

	public static String getcurrentdatefolder() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HHmm");
		Date date = new Date();
		return dateFormat.format(date).toString();

	}

	public static String createLogFolder(String path) {

		File dir = new File(path);

		System.out.println("creating log folder");
		dir.mkdir();
		System.out.println("log folder created");

		return dir.getAbsolutePath();

	}

	public static String createLogFolder_for_Service(String path, String service) {

		File dir = new File(path);

		dir.mkdir();

		return dir.getAbsolutePath();

	}

	public static void create_SetupExcelResultsSheet(String runResultsFileName) throws IOException {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Result");
		String[] headers = new String[] { "Date", "Enviornment", "Application", "Account Status", "Service Name",
				"Test Name", "Status", "Expected Status Code", "Actual Status Code", "Endpoint", "RC ticket", "Release Number",
				"Comment"};

		// System.out.println("Creating excel");

		Row r = sheet.createRow(0);
		for (int rn = 0; rn < headers.length; rn++) {

			r.createCell(rn).setCellValue(headers[rn]);
		}

		try {
			FileOutputStream outputStream = new FileOutputStream(runResultsFileName);
			workbook.write(outputStream);
			workbook.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String readproperties(String key) throws IOException {
		File fl = new File("src/test/resources/Properties/Endpoints/Endpoints.properties");
		FileInputStream fio = new FileInputStream(fl);
		Properties prop = new Properties();
		prop.load(fio);

		return prop.getProperty(key);

	}

	public static HashMap<String, Integer> getStatusDataReport(String path) throws IOException {

		XSSFSheet sht = Lib.getExcelResultsSheet(path);

		HashMap<String, Integer> resultsMap = new HashMap<String, Integer>();

		System.out.println("total rows : " + sht.getLastRowNum());
		for (int i = 0; i <= sht.getLastRowNum(); i++) {
			String status = readexcelvalue_GetTotalcaseAmountResult(i, 5, path);
			// System.out.println("iteration : " +i +" | Status = "+status);
			if (status.equalsIgnoreCase("Passed")) {
				Constants.passed++;
			} else if (status.equalsIgnoreCase("Failed")) {
				Constants.failed++;
			}
		}

		resultsMap.put("Passed", Constants.passed);
		resultsMap.put("Failed", Constants.failed);

		for (Entry<String, Integer> e : resultsMap.entrySet()) {
			// System.out.println(e.getKey()+" : "+e.getValue());
		}
		return resultsMap;
	}

	public static String readexcelvalue_GetTotalcaseAmountResult(int rownm, int colnm, String path) throws IOException {
		XSSFSheet sht = getExcelResultsSheet(path);
		XSSFRow rw = sht.getRow(rownm);
		XSSFCell cell = rw.getCell(colnm);

		cell.setCellType(Cell.CELL_TYPE_STRING);

		return cell.getStringCellValue();

	}

	public static XSSFSheet getExcelResultsSheet(String path) throws IOException {

		File fl = new File(path);
		// D:\Automation\
		FileInputStream fis = new FileInputStream(fl);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sht = workbook.getSheetAt(0);
		return sht;
	}

	public static String getcurrentdate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static void excelwrite(String Filename, Object[] ob)
			throws EncryptedDocumentException, InvalidFormatException, IOException {
		FileInputStream inp = new FileInputStream(Filename);

		Workbook wb = WorkbookFactory.create(inp);
		Sheet sheet = wb.getSheetAt(0);
		int num = sheet.getLastRowNum() + 1;
		System.out.println("row number is   " + num);
		// Row row = sheet.createRow(++num);

		// This data needs to be written (Object[])
		Map<String, Object[]> data = new TreeMap<String, Object[]>();

		data.put("1", ob);
		// Iterate over data and write to sheet
		Set<String> keyset = data.keySet();

		// System.out.println("keysets are :::: " + keyset);

		// int rownum = 0;
		for (String key : keyset) {
			// this creates a new row in the sheet
			System.out.println(key);
			Row row = sheet.createRow(num++);

			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				// this line creates a cell in the next column of that row
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}

		}
		try {
			// this Writes the workbook gfgcontribute
			FileOutputStream fileOut = new FileOutputStream(Filename);
			wb.write(fileOut);
			fileOut.close();
			System.out.println("write.xlsx written successfully on disk.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
