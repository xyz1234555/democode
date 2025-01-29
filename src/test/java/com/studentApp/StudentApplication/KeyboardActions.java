package com.studentApp.StudentApplication;

import org.testng.annotations.Test;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KeyboardActions {
	ChromeOptions  option=new ChromeOptions();
	WebDriver driver=new ChromeDriver(option);
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(0));
	String URL="https://text-compare.com/";
	String URL2="https://demo.nopcommerce.com/";
	Actions act=new Actions(driver);
	
  @Test
  public void keyboardaction() {
	  driver.get(URL2);
	  driver.manage().window().maximize();
	  //driver.findElement(By.xpath("//textarea[@id='inputText1']")).sendKeys("hello this is automation key action practice");
	  /*act.keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).perform();
	  act.keyDown(Keys.CONTROL).sendKeys("C").keyUp(Keys.CONTROL).perform();
	  act.keyDown(Keys.TAB).keyUp(Keys.TAB).perform();
	  act.keyDown(Keys.CONTROL).sendKeys("V").keyUp(Keys.CONTROL).perform();
	  driver.navigate().to(URL2);*/
	  WebElement ElementRegister=driver.findElement(By.xpath("//a[@class='ico-register']"));
	  act.keyDown(Keys.CONTROL).click(ElementRegister).keyUp(Keys.CONTROL).perform();
	  List<String> WindowIds=new ArrayList<String>(driver.getWindowHandles());
	  driver.switchTo().window(WindowIds.get(1));
	  driver.findElement(By.xpath("//input[@id='FirstName']")).isDisplayed();
	  driver.switchTo().window(WindowIds.get(0));
	  driver.findElement(By.xpath("//input[@placeholder ='Search store']")).isDisplayed();
	
  }
}
