package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	final int WAIT_FOR_SECONDS = 120;

	public void waitForElementVisibilityByElement(WebDriver driver, WebElement elem) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, WAIT_FOR_SECONDS);
		webDriverWait.until(ExpectedConditions.visibilityOf(elem));
	}

	public void waitForElementVisibilityByLocator(WebDriver driver, By locator) {
		WebDriverWait webDriverWait = new WebDriverWait(driver, WAIT_FOR_SECONDS);
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void waitForFieldToBePopulated(WebDriver driver, By locator, String value) {
		WebDriverWait wait = new WebDriverWait(driver, WAIT_FOR_SECONDS);
		wait.until(ExpectedConditions.textToBePresentInElementValue(driver.findElement(locator), value));
	}

	public void enterData(WebDriver driver, By locator, String value) {
		waitForElementVisibilityByLocator(driver, locator);
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(value);
		waitForFieldToBePopulated(driver, locator, value);
	}


	public void click(WebDriver driver, By locator) throws InterruptedException {
		waitForElementVisibilityByLocator(driver, locator);
		try {
			driver.findElement(locator).click();
		} catch (StaleElementReferenceException e) {
			driver.findElement(locator).click();
		}
	}

	public void clickByJS(WebDriver driver, By locator) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", driver.findElement(locator));

	}

	

	
	
	
}
