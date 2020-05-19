package utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomGenrator {
	
	 public String generateRandomNumber(int length){
		  return RandomStringUtils.randomNumeric(length);
		 }
	 
	 public String generateRandomString(int length){
		 String s = RandomStringUtils.randomAlphabetic(8);
		return s; 
		} 
	
	 
}
