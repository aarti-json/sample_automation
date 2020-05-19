package utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.Assert;

public class FileOperations {

	public String getValueFromPropertyFile(String filePath, String key) {
		String keyValue = null;
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(filePath));
			keyValue = prop.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
		return keyValue;
	}

	
	
}
