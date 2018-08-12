package Library;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

public class DotDashLibrary{
	
public static Properties or;
public static Properties config;
public static File fl;
public static FileInputStream file;
public static WebDriver driver;
public static Wait<WebDriver> wait;
public static Wait<WebDriver> elementNotPresentWait;
protected static String TestDataFilePath;



	public static void LaunchApp(String TestSuiteName) throws IOException, ClassNotFoundException{
		
		System.setProperty("webdriver.gecko.driver","C:\\MAHIX\\Selenium\\Drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("http://localhost/dotdash-project/index.php");
		ExcelUtils.initiateTestData(TestSuiteName);
		loadProperties();
		
		wait = new FluentWait<WebDriver>(driver)    
				.withTimeout(15, TimeUnit.SECONDS)    
			    .pollingEvery(3, TimeUnit.SECONDS)   
			    .ignoring(NoSuchElementException.class)
			    .ignoring(StaleElementReferenceException.class)
			    .ignoring(WebDriverException.class);
		
		elementNotPresentWait = new FluentWait<WebDriver>(driver)    
				.withTimeout(5, TimeUnit.SECONDS)    
			    .pollingEvery(1, TimeUnit.SECONDS)   
			    .ignoring(NoSuchElementException.class)
			    .ignoring(StaleElementReferenceException.class)
			    .ignoring(WebDriverException.class);
		
//		driver.findElement(By.xpath("//li[contains(.,'Download installation')]/input[contains(@name,'todo')]")).click();
//		driver.findElement(By.xpath("//li[contains(.,'Download installation')]/a[@title='Edit']")).click();
//	
	}
	
	
	public static void CloseApp(){
		
		driver.close();
		driver.quit();
		or = null;
		config = null;
	}
	
	
	public static void loadProperties() throws IOException {
		or=new Properties();
		config=new Properties();
		
		fl = new File(System.getProperty("user.dir")+"/src/resources/ObjectRepository.properties"); 
		file=new FileInputStream(fl);
		or.load(file);
		
		fl = new File(System.getProperty("user.dir")+"/src/resources/Config.properties"); 
		file=new FileInputStream(fl);
		config.load(file);
	} 

	public static boolean isElementPresent(String ElementXpath) {
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ElementXpath)));
            return true;
        }
        catch(Exception e){
            return false;
        }
	}
	
	public boolean isElementPresent(String ElementBeforeXpath,String ElementAfterXpath,String ElementXpath) {
		try{
			elementNotPresentWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ElementBeforeXpath+ElementXpath+ElementAfterXpath)));
            return true;
        }
        catch(Exception e){
            return false;
        }
	}
	
	public static void EnterText(String SheetName) throws IOException, ClassNotFoundException{
		
		String[] ParamValues = ExcelUtils.getTestCaseData(SheetName,"EnterText");
		
		String Xpath_Before = or.getProperty(ParamValues[0]);
		String Xpath_After = or.getProperty(ParamValues[1]);
		String Xpath_Element = ParamValues[2];
		String Element_Value = ParamValues[3];
		
		if (isElementPresent(Xpath_Before + Xpath_Element + Xpath_After)){
			driver.findElement(By.xpath(Xpath_Before + Xpath_Element + Xpath_After)).clear();
			driver.findElement(By.xpath(Xpath_Before + Xpath_Element + Xpath_After)).sendKeys(Element_Value);
			System.out.println("Text entered: "+Element_Value +" at " + Xpath_Element) ;
		}else
			Assert.fail("Text Box with Xpath->" + Xpath_Before + Xpath_Element + Xpath_After + " is Missing");
	}
	
	public void ClickButton(String SheetName) throws IOException, ClassNotFoundException{
		
		String[] ParamValues = ExcelUtils.getTestCaseData(SheetName,"ClickButton");
		
		String Xpath_Before = or.getProperty(ParamValues[0]);
		String Xpath_After = or.getProperty(ParamValues[1]);
		String Xpath_Element = ParamValues[2];
		
		if (isElementPresent(Xpath_Before + Xpath_Element + Xpath_After)){
			driver.findElement(By.xpath(Xpath_Before + Xpath_Element + Xpath_After)).click();
			System.out.println(Xpath_Element +" is Clicked") ;
		}else
			Assert.fail("Button with Xpath->" + Xpath_Before + Xpath_Element + Xpath_After + " is Missing");
	}
	

	
	public void ClickLink(String ElementXpath) throws IOException{
		
		String Xpath_Before = or.getProperty("Link_Before_Xpath");
		String Xpath_After = or.getProperty("Link_After_Xpath");
		if (isElementPresent(Xpath_Before + ElementXpath + Xpath_After)){
			driver.findElement(By.xpath(Xpath_Before + ElementXpath + Xpath_After)).click();
			System.out.println(ElementXpath +" is Clicked") ;
		}else
			Assert.fail("Link with Xpath->" + Xpath_Before + ElementXpath + Xpath_After + " is Missing");
	}
		
	
	public void ClickTaskLink(String ElementXpath) throws IOException{
		
		String Xpath_Before = or.getProperty("Task_DueDate_Before_Xpath");
		String Xpath_After = or.getProperty("Task_DueDate_After_Xpath");
		if (isElementPresent(Xpath_Before + ElementXpath + Xpath_After + "/a")){
			driver.findElement(By.xpath(Xpath_Before + ElementXpath + Xpath_After + "/a")).click();
			System.out.println(ElementXpath +" is Clicked") ;
		}else
			Assert.fail("Link with Xpath->" + Xpath_Before + ElementXpath + Xpath_After + "/a is Missing");
	}

	
	public void ClickTaskCheckBox(String ElementXpath) throws IOException{
		
		String Xpath_Before = or.getProperty("Task_DueDate_Before_Xpath");
		String Xpath_After = or.getProperty("Task_DueDate_After_Xpath");
		if (isElementPresent(Xpath_Before + ElementXpath + Xpath_After + "/a")){
			if(!driver.findElement(By.xpath(Xpath_Before + ElementXpath + Xpath_After + "/input")).isSelected()){
				driver.findElement(By.xpath(Xpath_Before + ElementXpath + Xpath_After + "/input")).click();
				System.out.println("CheckBox "+ElementXpath +" is Clicked") ;
			}
		}else
			Assert.fail("Link with Xpath->" + Xpath_Before + ElementXpath + Xpath_After + "/input is Missing");
	}
	
	public void SelectDropDown(String SheetName) throws ClassNotFoundException, IOException{
		
		String[] ParamValues = ExcelUtils.getTestCaseData(SheetName,"SelectDropDown");

		
		String Xpath_Before = or.getProperty(ParamValues[0]);
		String Xpath_After = or.getProperty(ParamValues[1]);
		String Xpath_Element = ParamValues[2];
		String Element_Value = ParamValues[3];
		
		if (isElementPresent(Xpath_Before + Xpath_Element + Xpath_After)){
			System.out.println("Inside If of Select Drop Down");
			WebElement dropDownElement = driver.findElement(By.xpath(Xpath_Before + Xpath_Element + Xpath_After));
			Select dropdown = new Select(dropDownElement);
			dropdown.selectByVisibleText(Element_Value);
		}else
			Assert.fail("DropDown with Xpath->" + Xpath_Before + Xpath_Element + Xpath_After + " is Missing");
	}
	
	public static boolean VerifyDropDown(String SheetName) throws ClassNotFoundException, IOException{
		
		String[] ParamValues = ExcelUtils.getTestCaseData(SheetName,"VerifyDropDown");

		
		String Xpath_Before = or.getProperty(ParamValues[0]);
		String Xpath_After = or.getProperty(ParamValues[1]);
		String Xpath_Element = ParamValues[2];
		String Element_Value = ParamValues[3];

		
		if (isElementPresent(Xpath_Before + Xpath_Element + Xpath_After)){
			WebElement dropDownElement = driver.findElement(By.xpath(Xpath_Before + Xpath_Element + Xpath_After));
			Select dropdown = new Select(dropDownElement);
			Boolean found = false;
			List<WebElement> allOptions = dropdown.getOptions();
			for(WebElement option : allOptions) {
					found = option.getText().contentEquals(Element_Value);
					if(found){
						break;
					}
			}
			if(found){
				System.out.println(Element_Value + " is found in the Category Dropdown");
				return found;
			}else
				System.out.println(Element_Value + " is Not found in the Category Dropdown");
			    return found;
		}else
			Assert.fail("DropDown with Xpath->" + Xpath_Before + Element_Value + Xpath_After + " is Missing");
			return false;
	}

	public static boolean VerifyColor(String SheetName) throws ClassNotFoundException, IOException{
		
		String[] ParamValues = ExcelUtils.getTestCaseData(SheetName,"VerifyColor");
		
		String Xpath_Before = or.getProperty(ParamValues[0]);
		String Xpath_After = or.getProperty(ParamValues[1]);
		String Xpath_Element = ParamValues[2];
		String Element_Value = ParamValues[3];
	
		String ConfigColor = config.getProperty(Element_Value);
		
		if (isElementPresent(Xpath_Before + Xpath_Element + Xpath_After)){
			String ElementColor = driver.findElement(By.xpath(Xpath_Before + Xpath_Element + Xpath_After)).getCssValue("color");
			String hex = Color.fromString(ElementColor).asHex().toUpperCase();
			if(hex.contains(ConfigColor)){
				System.out.println("Element Color Matches to Selected Color : " + Element_Value);
				return true;
			}
			else
				return false;
		}else
			Assert.fail("Element with Xpath->" + Xpath_Before + Xpath_Element + Xpath_After + " is Missing");
			return false;
	}
	
	public static boolean VerifyTextPresent(String BeforeXpathKey,String AfterXpathKey,String ElementXpath,String TextToVerify){
		String Xpath_Before = or.getProperty(BeforeXpathKey);
		String Xpath_After = or.getProperty(AfterXpathKey);
		
		if (isElementPresent(Xpath_Before + ElementXpath + Xpath_After)){
			String Text = driver.findElement(By.xpath(Xpath_Before + ElementXpath + Xpath_After)).getText();
			if(Text.contains(TextToVerify)){
				return true;
			}else
				return false;
		}else
			Assert.fail("Element with Xpath->" + Xpath_Before + ElementXpath + Xpath_After + " is Missing");
			return false;
	}


}
