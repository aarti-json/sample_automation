package tests;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import constants.Constants;
import init.DriverFactory;
import utils.FileOperations;



public class EnviromentSetUP {
	WebDriver driver = null;
	FileOperations fileOperations = new FileOperations();
	Constants constants = new Constants();
	DriverFactory df = new DriverFactory();
	
	Logger log = null;

	@BeforeClass
	public void setUp() throws InterruptedException {
		driver = df.createDriver();
	//	driver.get(fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "url"));
		Thread.sleep(2000);
	
	}
	@Test
	public void verifyBuyNow(){
		driver.get("https://demo.midtrans.com/");
		
	}
}
