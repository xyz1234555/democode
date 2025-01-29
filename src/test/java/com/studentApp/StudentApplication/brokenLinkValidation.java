package com.studentApp.StudentApplication;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.openqa.selenium.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class brokenLinkValidation {
	 String URL = "http://www.deadlinkcity.com/";
	    ChromeOptions options = new ChromeOptions();
	    WebDriver driver=new ChromeDriver(options);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    
	    
  @Test
  public void brokenLink()  {
	  driver.manage().window().maximize();
	  driver.get(URL);
	  List<WebElement> links=driver.findElements(By.tagName("a"));
	  System.out.println("no of links on the page: "+links.size());
	  int noOfBrokenLinks=0;
	  try {
	  for(WebElement httpLinks:links) {
		  String herfAttribute=httpLinks.getAttribute("href");
		  if(herfAttribute==null || herfAttribute.isEmpty()) {
			  System.out.println("href attribute -------> empty");
			  continue;
		  }
		  //hit URL to the Server
		  URL LinkURL=new URL(herfAttribute);
		  HttpURLConnection conn=(HttpURLConnection) LinkURL.openConnection();
		  //open connection to the server
		  conn.connect();
		  //connect to server and sent request to the server
		  if(conn.getResponseCode()>=400) {
			  System.out.println(herfAttribute+"-------->Broken Link");
			  noOfBrokenLinks++;
		  }
		  else {
			  System.out.println(herfAttribute+"-------->not a Broken Link");
		  }
		  
		  
	  }
	  
	  System.out.println(noOfBrokenLinks);
	  
  }catch(Exception e) {
	  System.out.println(e.getMessage());
  }
}
}
