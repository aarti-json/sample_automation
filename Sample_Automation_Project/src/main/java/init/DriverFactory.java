package init;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import constants.Constants;
import utils.FileOperations;

public class DriverFactory {

	private static String detectedOS = null;
	public static WebDriver webdriver = null;
	Constants constants = new Constants();
	FileOperations fileOperations = new FileOperations();

	/**
	 * @return current OS
	 */
	public static String getOperatingSystemType() {

		String OS = System.getProperty("os.name", "generic").toLowerCase();
		if ((OS.indexOf("mac") >= 0)) {
			detectedOS = "MacOS";
		} else if (OS.indexOf("win") >= 0) {
			detectedOS = "Windows";
		} else if (OS.indexOf("nux") >= 0) {
			detectedOS = "Linux";
		} else {
			detectedOS = "Other";
		}

		return detectedOS;
	}

	/**
	 * @return the instance of web driver
	 */

	public WebDriver createDriver() {
		String osType = getOperatingSystemType();
		String browserName = getBrowserType();
		if (browserName.equalsIgnoreCase("firefox") || browserName.equalsIgnoreCase("ff")) {
			if (osType.equals("Linux")) {
				System.setProperty("webdriver.chrome.driver", "src/../drivers/linux/geckodriver");
			} else if (osType.equals("MacOS")) {
				System.setProperty("webdriver.chrome.driver", "src/../drivers//mac/geckodriver");
			} else {
				System.setProperty("webdriver.gecko.driver", "src/../drivers/win/geckodriver.exe");
			}
			webdriver = new FirefoxDriver();
			webdriver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("ch")) {
			if (osType.equals("Linux")) {
				System.setProperty("webdriver.chrome.driver", "src/../drivers/chromedriver");
			} else if (osType.equals("MacOS")) {
				System.setProperty("webdriver.chrome.driver", "src/../drivers/chromedriver");
			} else {
				System.setProperty("webdriver.chrome.driver", "src/../drivers/chromedriver.exe");
			}
			webdriver = new ChromeDriver();
			webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			webdriver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("internet explorer") || browserName.equalsIgnoreCase("ie")) {
			
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);

			System.setProperty("webdriver.ie.driver", "src/../drivers/IEDriverServer.exe");

			webdriver= new InternetExplorerDriver(capabilities);
			webdriver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("BrowserStack") || browserName.equalsIgnoreCase("bs")) {
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("browser", "Chrome");
			caps.setCapability("browser_version", "62.0");
			caps.setCapability("os", "Windows");
			caps.setCapability("os_version", "10");
			caps.setCapability("browserstack.debug", "true");
		} else if (browserName.equalsIgnoreCase("edge") || browserName.equalsIgnoreCase("Microsoft edge")) {
			System.setProperty("webdriver.edge.driver", "src/../drivers/MicrosoftWebDriver.exe");
			webdriver = new EdgeDriver();
			webdriver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("safari") || browserName.equalsIgnoreCase("mac safari")) {
			webdriver = new SafariDriver();
			webdriver.manage().window().maximize();
		}
		return webdriver;
	}

	/**
	 * @return browsers name
	 */
	public String getBrowserType() {
		return fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "browserName");
	}

	/**
	 * @return already created Webdriver instance
	 */
	public WebDriver getCurrentWebDriver() {
		if (webdriver == null)
			webdriver = createDriver();
		return webdriver;
	}

}