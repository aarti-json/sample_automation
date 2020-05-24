package tests;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import constants.Constants;
import init.DriverFactory;
import utils.FileOperations;


public class Product_CreationTest {

	WebDriver driver = null;
	DriverFactory df = new DriverFactory();
	FileOperations fileOperations = new FileOperations();
	Constants constants = new Constants();
	

	public static String Error_message;
	public static Logger logger;
	public static String Username;
	public static String Password;

	@BeforeClass
	public void setUp() {
		driver = df.createDriver();
	   driver.get(fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "url"));
	  /* logger = Logger.getLogger("xys");
	   PropertyConfigurator.configure("Log4j.properties");*/
	}

	@Test
	public void TestRun(){
		
		
		
	}
	
	
	
}
