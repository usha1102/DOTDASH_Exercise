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

public class AddTaskWithDueDate extends DotDashLibrary {
	
	@BeforeClass
	public void beforeTest(){
		System.out.println("Entering " + this.getClass().getSimpleName());
	}
	
	@AfterClass
	public void afterTest(){
		System.out.println("Exiting " + this.getClass().getSimpleName());
	}
	
	@Test
	public void addTaskWithDueDate() throws IOException{
		EnterText("data","MyTask");
		SelectDropDown("category", "Work");
		SelectDropDown("due_day", "31");
		SelectDropDown("due_month", "Aug");
		SelectDropDown("due_year", "2018");
		ClickButton("Add");
	}
	
	@Test
	public void verifyTaskWithDueDate() throws InterruptedException{		
		if(!VerifyColor("Task_Color_Before_Xpath","Task_Color_After_Xpath","MyTask","Blue")){
			Assert.fail("Color of Added Task is not Matching");
		}
		
		if(!VerifyTextPresent("Task_DueDate_Before_Xpath","Task_DueDate_After_Xpath","MyTask","MyTask (31/08/18)")){
			Assert.fail("Added Task Text is Not Matching");
		}else
			System.out.println("Succesfully Added Task");
	}
}
