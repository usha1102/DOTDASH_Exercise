package com.dotdash.execises;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Library.DotDashLibrary;


public class AddCategory extends DotDashLibrary {
	
	String NameOfClass="AddCategory";
	
	
	@BeforeClass
	public void beforeTest() throws IOException, ClassNotFoundException{
		
		System.out.println("Entering " + this.getClass().getSimpleName());
		//System.out.println("Entering " + this.getClass().getCanonicalName());
	}
	
	@AfterClass
	public void afterTest(){
		System.out.println("Exiting " + this.getClass().getSimpleName());
	}
	
	@Test
	@Parameters("SuiteName")
	public void addCategory() throws IOException, ClassNotFoundException, InterruptedException{
		
		NameOfClass = this.getClass().getSimpleName();
		EnterText(NameOfClass);
		SelectDropDown(NameOfClass);
		ClickButton(NameOfClass);
	}
	
	@Test
	@Parameters("SuiteName")
	public void verifyCategory() throws InterruptedException, ClassNotFoundException, IOException{		
		if(!VerifyColor(NameOfClass)){
			Assert.fail("Color of Added Category is not Matching");
		}
		if(!VerifyDropDown(NameOfClass)){
			Assert.fail("Category is not Added in the Category Dropdown");
		}
	}

}




