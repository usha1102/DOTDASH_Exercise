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

public class UpdateTask extends DotDashLibrary {
	
	@BeforeClass
	public void beforeTest(){
		System.out.println("Entering " + this.getClass().getSimpleName());
	}
	
	@AfterClass
	public void afterTest(){
		System.out.println("Exiting " + this.getClass().getSimpleName());
	}

	@Test
	public void updateTask() throws IOException{
		
		ClickTaskLink("MyTask");
		EnterText("data","MyTask_Updated");
		SelectDropDown("category", "Personal");
		SelectDropDown("due_day", "29");
		SelectDropDown("due_month", "Aug");
		SelectDropDown("due_year", "2018");
		ClickButton("Update");
	}
	
	@Test
	public void verifyUpdatedTask() throws InterruptedException{		
		if(!VerifyColor("Task_Color_Before_Xpath","Task_Color_After_Xpath","MyTask","BurntOrange")){
			Assert.fail("Color of Added Task is not Matching");
		}
		
		if(!VerifyTextPresent("Task_DueDate_Before_Xpath","Task_DueDate_After_Xpath","MyTask","MyTask_Updated (29/08/18)")){
			Assert.fail("Added Task Text is Not Matching");
		}else
			System.out.println("Succesfully Updated Task");
	}
}
