package com.dotdash.execises;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Library.DotDashLibrary;

public class AddTaskNoDueDate extends DotDashLibrary {
	
	
	@BeforeClass
	public void beforeTest(){
		System.out.println("Entering " + this.getClass().getSimpleName());
	}
	
	@AfterClass
	public void afterTest(){
		System.out.println("Exiting " + this.getClass().getSimpleName());
	}
	
	@Test
	public void addTaskNoDueDate() throws IOException{
		EnterText("data","MyTask");
		SelectDropDown("category", "Work");
		ClickButton("Add");
	}
	
	@Test
	public void verifyTaskNoDueDate() throws InterruptedException{		
		if(!VerifyColor("Task_Color_Before_Xpath","Task_Color_After_Xpath","MyTask","Blue")){
			Assert.fail("Color of Added Task is not Matching");
		}
		
		if(!VerifyTextPresent("Task_DueDate_Before_Xpath","Task_DueDate_After_Xpath","MyTask","MyTask (None)")){
			Assert.fail("Added Task Text is Not Matching");
		}else
			System.out.println("Succesfully Added Task");
	}
	
}
