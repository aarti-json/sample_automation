package tests;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import constants.Constants;
import init.DriverFactory;
import utils.FileOperations;



public class testbrokenlink {


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
			String env=System.getenv("env_var");
			
			driver.get(fileOperations.getValueFromPropertyFile(constants.CONFIG_WEB_FILE_PATH, "url"));


			}
		
		
		//@Test
		public void validateBrokenLink(){
			
			int invalidImageCount = 0; 

			List<WebElement> imagesList = driver.findElements(By.tagName("img")); 
			System.out.println("Total no. of images are " + imagesList.size()); 

			for (WebElement imgElement : imagesList) { 
			 if (imgElement != null) { 
			  try { 
			   HttpClient client = HttpClientBuilder.create().build(); 
			   HttpGet request = new HttpGet(imgElement.getAttribute("src")); 
			   HttpResponse response = client.execute(request); 

			   // verifying response code he HttpStatus should be 200 if not, 
			   // increment as invalid images count 

			   if (response.getStatusLine().getStatusCode() != 200) 
			     invalidImageCount++; 
			   } catch (Exception e) { 
			     e.printStackTrace(); 
			   }
			  } 
			 } 

			System.out.println("Total no. of invalid images are " + invalidImageCount);
		}
		
		
		
		@Test
		public void test2(){
			
			
			String homePage = "http://www.zlti.com";
	        String url = "";
	        HttpURLConnection huc = null;
	        int respCode = 200;
	        
	      //  driver = new ChromeDriver();
	        
	       // driver.manage().window().maximize();
	        
	        driver.get(homePage);
	        
	        List<WebElement> links = driver.findElements(By.tagName("a"));
	        
	        Iterator<WebElement> it = links.iterator();
	        
	        while(it.hasNext()){
	            
	            url = it.next().getAttribute("href");
	            
	            System.out.println(url);
	        
	            if(url == null || url.isEmpty()){
	System.out.println("URL is either not configured for anchor tag or it is empty");
	                continue;
	            }
	            
	            if(!url.startsWith(homePage)){
	                System.out.println("URL belongs to another domain, skipping it.");
	                continue;
	            }
	            
	            try {
	                huc = (HttpURLConnection)(new URL(url).openConnection());
	                
	                huc.setRequestMethod("HEAD");
	                
	                huc.connect();
	                
	                respCode = huc.getResponseCode();
	                
	                if(respCode >= 400){
	                    System.out.println(url+" is a broken link");
	                }
	                else{
	                    System.out.println(url+" is a valid link");
	                }
	                    
	            } catch (MalformedURLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	        
	        driver.quit();
		}
		


}
