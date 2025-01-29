package com.studentApp.StudentApplication;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class CheckboxesAndAlerts {
String URL= "https://testautomationpractice.blogspot.com/";
    
    ChromeOptions options=new ChromeOptions();
    WebDriver driver = new ChromeDriver(options);
    WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
   

  @Test
  public void CheckboxesAlerts() {
	  driver.manage().window().maximize();
	  driver.get(URL);
	  options.addArguments("--disable-notifications");
	  List<WebElement> checkbox=driver.findElements(By.xpath("//input[@class='form-check-input' and @type='checkbox']"));
	  for (int i=0;i<checkbox.size();i++) {
		  checkbox.get(i).click();
	  }
	  for (int i=0;i<checkbox.size();i++) {
		  if(checkbox.get(i).isSelected())
		  checkbox.get(i).click();
	  }
	 // List<WebElement> ElementSimpleAlert=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//button[@id='alertBtn']")));
	 // highlightElement(driver,ElementSimpleAlert);
	  WebElement ElementSimpleAlert=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='alertBtn']")));
      highlightElement(driver, ElementSimpleAlert);
	  ElementSimpleAlert.click();
	  Alert SimpleAlert=driver.switchTo().alert();
	  SimpleAlert.accept();
	  //confirmBtn
	  WebElement ElementConfirmationAlert=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='confirmBtn']")));
      highlightElement(driver, ElementConfirmationAlert);
      ElementConfirmationAlert.click();
	  Alert ConfirmationAlert=driver.switchTo().alert();//promptBtn
	  SimpleAlert.dismiss();
	  
	  WebElement ElementPopupAlert=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='promptBtn']")));
      highlightElement(driver, ElementPopupAlert);
      ElementPopupAlert.click();
	  Alert PopupAlert=driver.switchTo().alert();//promptBtn
	  PopupAlert.sendKeys("you are Mad");
	  PopupAlert.accept();
	  driver.navigate().to("https://ui.vision/demo/webtest/frames/");
	  WebElement frame1=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//frame[@src='frame_1.html']")));
	  highlightElement(driver, frame1);
	  driver.switchTo().frame(frame1);
	  WebElement textbox1=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='mytext1']")));
	  highlightElement(driver, textbox1);
	  textbox1.sendKeys("Hello my name is ");
	  driver.switchTo().defaultContent();
	  driver.navigate().to(URL);
	  WebElement ElementCountry=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//select[@id='country']")));
	  highlightElement(driver, ElementCountry);
	  Select dropDown=new Select(ElementCountry);
	  dropDown.selectByVisibleText("India");
	  //dropDown.selectByValue("Japan");
	  List<WebElement>options=dropDown.getOptions();
	  System.out.println("No of the options avaialble"+options.size());
	  for(int i=0;i<options.size();i++) {
		  System.out.println(options.get(i).getText());
		  
	  }
	  // dynamic drpdown
	  driver.navigate().to("https://www.google.com/");
	  WebElement Elementgooglesearch=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//textarea[@title='Search']")));
	  Elementgooglesearch.sendKeys("Selenium");
	  List<WebElement> op=driver.findElements(By.xpath("//ul[@role='listbox']//li//div[@role='option']"));
	  for(WebElement o:op) {
		  if(o.equals("Selenium")) {
			  o.click();
			  break;
		  }
		 }
	  driver.navigate().to(URL); 
	  
  }
  public static void highlightElement(WebDriver driver, WebElement element) {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      String originalStyle = element.getAttribute("style");

      // Apply dark blue border with solid, non-fading glow
      js.executeScript("arguments[0].setAttribute('style', 'border: 4px solid #4682B4; box-shadow: 0 0 10px #4682B4;');", element);

      try {
          Thread.sleep(500); // Keeps highlight visible for a short period, adjust if needed
      } catch (InterruptedException e) {
          e.printStackTrace();
      }

      // Revert to the original style of the element
      js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
  }

  
}
