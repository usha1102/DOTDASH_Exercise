package com.dotdash.execises;

import java.io.IOException;

import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Library.ExcelUtils;

public class TestConfig extends ExcelUtils {
	
	String TestSuiteNamePublic;
	String RunFromXMLLocal;
	
	@BeforeSuite
	@Parameters({"SuiteName","RunFromXML"})
	public void beforeTest(String TestSuiteName,String RunFromXML) throws IOException, ClassNotFoundException{
		LaunchApp(TestSuiteName);
		TestSuiteNamePublic = TestSuiteName;
		RunFromXMLLocal = RunFromXML;
	}
	
	@Test(dataProvider="getData")
	public void TestRunner(String SuiteName , String TestCase) throws ClassNotFoundException, IOException{
		
		ExcelUtils.TestSuiteName = SuiteName;
		ExcelUtils.TestCaseName = TestCase;
		if(RunFromXMLLocal.equalsIgnoreCase("true")){
			TestNG testCaseRunner = new TestNG();
			ITestNGListener lis = new TestListenerAdapter();
			
			int TestCaseActionsArray = ExcelUtils.getTestCaseActions(TestCase).length;
			
			Class[] classes =  new Class[TestCaseActionsArray];
			
			for(int i=0;i<TestCaseActionsArray;i++){
				classes[i] = Class.forName("com.dotdash.excercise."+ExcelUtils.getTestCaseActions(TestCase)[i]);
			}
			
			testCaseRunner.addListener(lis);
			testCaseRunner.setTestClasses(classes);
			testCaseRunner.run();
		}
	}
	
	@AfterSuite
	public void afterTest(){
		CloseApp();
		
	}
	
	@DataProvider(name="getData")
	public Object[][] getData() throws ClassNotFoundException, IOException{
		return ExcelUtils.getTestCase(TestSuiteNamePublic);
		
	}
	
	
}
