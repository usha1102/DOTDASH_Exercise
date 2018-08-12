package Library;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils extends DotDashLibrary {
	
	static int TestSuiteLastRow,TotalTestCases=0,TestCaseRow;
	static XSSFSheet sheet,TestSuiteSheet;
	static XSSFWorkbook workbook;
	static HashMap<Object,Class[]> TestCaseActionMap = new HashMap<Object, Class[]>();
	public static String TestSuiteName, TestCaseName;
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		initiateTestData("Suite1");
		System.out.println(getTestCase("Suite1"));
		System.out.println(getTestCaseActions("TestCase1"));
	}
	
	public static void initiateTestData(String TestSuiteName) throws IOException, ClassNotFoundException {
		
		loadProperties();
		TestDataFilePath = System.getProperty("user.dir")+config.getProperty("TestDataFilePath");
		FileInputStream excelFile = new FileInputStream(new File(TestDataFilePath));
        workbook = new XSSFWorkbook(excelFile);
        TestSuiteSheet = workbook.getSheet("TestSuite");
        TestSuiteLastRow = TestSuiteSheet.getLastRowNum();
	}
	
	public static Object[][] getTestCase(String TestSuite) throws IOException, ClassNotFoundException {
		
		int LastRow = TestSuiteLastRow;
		int counter = 0;
		Object[][] abc = null;
		for(int i=0;i<LastRow;i++){
			if(TestSuiteSheet.getRow(i).getCell(0).getStringCellValue().contentEquals(TestSuite)){
				TotalTestCases = TotalTestCases + 1;
			}
		}
		abc = new Object[TotalTestCases][2];
		
		for(int i=0;i<LastRow;i++){
			if(TestSuiteSheet.getRow(i).getCell(0).getStringCellValue().contentEquals(TestSuite)){
				abc[counter][0] = TestSuiteSheet.getRow(i).getCell(0).getStringCellValue();
				abc[counter][1] = TestSuiteSheet.getRow(i).getCell(1).getStringCellValue();
				counter++;
			}
		}
		
//		System.out.println(abc);
		return abc;
	}
	

	public static String[] getTestCaseActions(String TestCase) throws IOException, ClassNotFoundException {
		int LastRow = TestSuiteLastRow;
		int TotalActions = 0;
		for(int i=0;i<LastRow;i++){
			if(TestSuiteSheet.getRow(i).getCell(1).getStringCellValue().contentEquals(TestCase)){
				TotalActions = TestSuiteSheet.getRow(i).getLastCellNum();
				TestCaseRow = i;
				break;
			}
		}
		
		String[] TestActionsArray = new String[TotalActions-2]; 
		for(int i=0;i<TotalActions-2;i++){
			TestActionsArray[i] = TestSuiteSheet.getRow(TestCaseRow).getCell(i+2).getStringCellValue();
		}
		
		return TestActionsArray;
	}
	
	public static String[] getTestCaseData(String SheetName, String MethodName) throws IOException, ClassNotFoundException {
		
		XSSFSheet TestDataSheet = workbook.getSheet(SheetName);
		int counter=0;
		
		int LastRow = TestDataSheet.getLastRowNum();
		int LastColumn = TestDataSheet.getRow(0).getLastCellNum();
		int TestCaseRow = 0;
		String TestCaseParamValues[] = null;
		
		for(int i=0; i<LastRow+1;i++){
			if(TestDataSheet.getRow(i).getCell(1).getStringCellValue().equalsIgnoreCase(TestCaseName)){
				TestCaseRow=i;
				break;
			}
		}
		
		for(int i=2; i<LastColumn;i++){
			if(TestDataSheet.getRow(0).getCell(i).getStringCellValue().contains(MethodName)){
				counter++;
			}
		}
		
		TestCaseParamValues = new String[counter];
		for(int i=2; i<LastColumn;i++){
			if(TestDataSheet.getRow(0).getCell(i).getStringCellValue().contains(MethodName)){
				for(int j=0;j<counter;j++){
					TestCaseParamValues[j] = TestDataSheet.getRow(TestCaseRow).getCell(i+j).getStringCellValue();
				}
				break;
			}
		}

	
		return TestCaseParamValues;
	}
	
}
