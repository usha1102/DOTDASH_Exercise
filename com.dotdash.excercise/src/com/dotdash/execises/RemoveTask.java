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

public class RemoveTask extends DotDashLibrary {
	
	@BeforeClass
	public void beforeTest(){
		System.out.println("Entering " + this.getClass().getSimpleName());
	}
	
	@AfterClass
	public void afterTest(){
		System.out.println("Exiting " + this.getClass().getSimpleName());
	}

	
	@Test
	public void removeTask() throws IOException{
		
		ClickTaskCheckBox("MyTask_Updated");
		ClickButton("Remove");
	}
	
	
	@Test
	public void verifyRemoveTask() throws InterruptedException{		

		if(!isElementPresent("Task_DueDate_Before_Xpath","MyTask_Updated","Task_DueDate_After_Xpath")){
			System.out.println("Task is Removed Succesfully");
		}else
			Assert.fail("Task is Not Removed");
	}
}
