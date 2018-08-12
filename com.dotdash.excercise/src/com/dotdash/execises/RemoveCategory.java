package com.dotdash.execises;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Library.DotDashLibrary;

public class RemoveCategory extends DotDashLibrary {
	
	@BeforeClass
	public void beforeTest(){
		System.out.println("Entering " + this.getClass().getSimpleName());
	}
	
	@AfterClass
	public void afterTest(){
		System.out.println("Exiting " + this.getClass().getSimpleName());
	}

	@Test
	public void doNotRemoveCategory() throws IOException{
		
		ClickLink("Work");
		ClickLink("Nevermind");
	}
	
	@Test
	public void verifyDoNotRemoveCategory() throws InterruptedException{		
		if(!VerifyColor("Category_Before_Xpath","Category_After_Xpath","Work","Blue")){
			Assert.fail("Color of Added Category is not Matching");
		}
		if(!VerifyDropDown("category","Work")){
			Assert.fail("Category is not Added in the Category Dropdown");
		}
	}

	@Test
	public void removeCategory() throws IOException{

		ClickLink("Work");
		ClickLink("Yes");
	}
	
	
	@Test(groups={"CategoryAndTask","TaskWithNoDueDate"})
	public void verifyRemoveCategory() throws InterruptedException{		
		if(VerifyDropDown("category","Work")){
			Assert.fail("Category is not removed");
		}
	}
}
