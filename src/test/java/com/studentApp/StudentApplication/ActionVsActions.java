package com.studentApp.StudentApplication;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ActionVsActions {
	 String URL = "https://demo.opencart.com/";
	 String URL2="https://swisnl.github.io/jQuery-contextMenu//demo/trigger-left-click.html";
	    ChromeOptions options = new ChromeOptions();
	    WebDriver driver = new ChromeDriver(options);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    Actions act=new Actions(driver);
	    
  @Test
  public void performingactions() {
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  driver.get(URL);
	  driver.manage().window().maximize();

	WebElement DesktopElement=driver.findElement(By.xpath("//div[@id='narbar-menu']//a[@href='https://demo.opencart.com/en-gb/catalog/desktops' and @class='nav-link dropdown-toggle']"));
	WebElement MacElement=driver.findElement(By.xpath("//a[@href='https://demo.opencart.com/en-gb/catalog/desktops/mac'][starts-with(text(),'Mac ')]"));
	act.moveToElement(DesktopElement).moveToElement(MacElement).click().build().perform();
	driver.navigate().to(URL2);
	WebElement Button=driver.findElement(By.xpath("//span[contains(text(),'left click me')]"));
	act.contextClick(Button).build().perform();
	
  }
}
