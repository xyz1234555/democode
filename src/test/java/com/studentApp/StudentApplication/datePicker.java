package com.studentApp.StudentApplication;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class datePicker {
	String Month="May";
	String Year="2025";
	String Date="14";
	
	 String URL = "https://jqueryui.com/datepicker/";
	    ChromeOptions options = new ChromeOptions();
	    WebDriver driver = new ChromeDriver(options);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  @Test
  public void validateDatePicker() throws InterruptedException {
	  driver.get(URL);
	  driver.manage().window().maximize();
	  driver.switchTo().frame(0);
	  driver.findElement(By.xpath("//input[@id='datepicker']")).click();
	
	  while(true) {
		  String expectedMonth=driver.findElement(By.xpath("//span[@class='ui-datepicker-month']")).getText();
		  String expectedYear=driver.findElement(By.xpath("//span[@class='ui-datepicker-year']")).getText();
		  System.out.println(expectedMonth);
		  System.out.println(expectedYear);
		  if(expectedMonth.equals(Month) && expectedYear.equals(Year)) {
			  break;
		  }
			  driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
			  //Thread.sleep(3000);
	 }
	int tablerow=driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//tr")).size();  
	int tablecol=driver.findElements(By.xpath("//table[@class=\"ui-datepicker-calendar\"]//tr//th")).size();
	List<WebElement> Element=driver.findElements(By.xpath("//table[@class=\"ui-datepicker-calendar\"]//tr//td//a"));
	for(WebElement El:Element) {
		if(El.getText().equals(Date)) {
		   El.click();
		   break;
		}
	}
	
  }
}
