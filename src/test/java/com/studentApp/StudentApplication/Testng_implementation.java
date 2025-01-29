package com.studentApp.StudentApplication;

import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeTest;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class Testng_implementation {
    String URL = "https://ddl00000ef19puab-dev-ed.develop.my.salesforce.com/";
    
    ChromeOptions options = new ChromeOptions();
    WebDriver driver = new ChromeDriver(options);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));



    @BeforeTest
    public void setup() {
        options.addArguments("--disable-notifications");
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(20));
        driver.manage().window().maximize();
        driver.get(URL);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('username').value='automation@aut.com';");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Vishisth8778!");
        driver.findElement(By.xpath("//input[@id='Login']")).click();
        options.addArguments("--disable-notifications");

    }

    @Test
    public void LeadCreation() {
        try {
            
            String validation1 = "New Lead";
           
            // Highlight elements as they are located
            WebElement appLauncher = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='App Launcher']")));
            highlightElement(driver, appLauncher);
            appLauncher.click();
            
            WebElement searchBox = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Search apps and items...']")));
          highlightElement(driver, searchBox);
            searchBox.sendKeys("Leads");
            
            WebElement getLeads = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//b[contains(text(),'Leads')]")));
            highlightElement(driver, getLeads);
            getLeads.click();
            
            WebElement newButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='New']")));
          highlightElement(driver, newButton);
            newButton.click();
            
            WebElement H2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'New Lead')]")));
           highlightElement(driver, H2);
            String validation2 = H2.getText();
            System.out.println(validation2);
            Assert.assertEquals(validation2, validation1, "The values should be equal");

            WebElement ElementLeadInformation=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Lead Information')]")));
            highlightElement(driver, ElementLeadInformation);
            ElementLeadInformation.isDisplayed();
            
            WebElement ElementLeadName=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='lastName']")));
            highlightElement(driver, ElementLeadName);
            String expectedString=generateRandomString(9);
            ElementLeadName.sendKeys(expectedString);//input[@name='Company']
            System.out.println("expectedString: "+expectedString);
           
            WebElement ElementCompany=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='Company']")));
            highlightElement(driver, ElementCompany);
            ElementCompany.sendKeys(generateRandomString(9));
            WebElement ElementSave=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name='SaveEdit']")));
            highlightElement(driver, ElementSave);
            ElementSave.click();
           // WebElement elementIspresent=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'New Settings for Record Pages')]")));
           // highlightElement(driver, ElementSave);
            //boolean ispresent=elementIspresent.isDisplayed();
            // System.out.printf(" The element is present or not ",ispresent);
            
            WebElement Elementdropdown=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//lightning-button-menu[@class='menu-button-item slds-dropdown-trigger slds-dropdown-trigger_click']//button[span[contains(text(),'Show more actions') and @class='slds-assistive-text']]")));
            highlightElement(driver, Elementdropdown);
            Elementdropdown.click();
          
            WebElement ElementEdit=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@role='menuitem']//span[contains(text(),'Edit')]")));
            highlightElement(driver, ElementEdit);
            ElementEdit.click();
            WebElement ElementGetheader=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[@class='slds-modal__title slds-hyphenate slds-text-heading--medium']")));
            highlightElement(driver, ElementGetheader);
            String header2= ElementGetheader.getText();
            String actualString=header2.substring(5);
            Assert.assertEquals(actualString, expectedString, "The values should be equal");            
            WebElement ElementCancel=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Cancel')]")));
            highlightElement(driver, ElementCancel);
            ElementCancel.click();
            
        } catch (Exception e) {
            System.out.println("Element could not be clicked: " + e.getMessage());
        }
    }
    @Test
    public void fieldValidation() {
    	try{
    		System.out.println(driver.getTitle());
    		WebElement Elementsetup=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//lightning-icon[@class='slds-icon-utility-setup slds-button__icon slds-global-header__icon forceIcon slds-icon_container']")));
    		highlightElement(driver, Elementsetup);
    		Elementsetup.click();
    		 options.addArguments("--disable-notifications");
    		 options.addArguments("--disable-notifications");
    		WebElement ElementsetupClick=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@data-id,'related_setup_app_home')]")));
    		highlightElement(driver, ElementsetupClick);
    		ElementsetupClick.click();
    		Thread.sleep(9000);
    		Set<String> WindowIDs=driver.getWindowHandles();
    		List<String> WindowList=new ArrayList(WindowIDs);
    		String window0=WindowList.get(0);
    		String window1=WindowList.get(1);
    		driver.switchTo().window(window1);
    		System.out.println(driver.getTitle());
    	}catch(Exception e) {
    		System.out.println("Element could not be clicked: " + e.getMessage());	
    	}
    } 

    @AfterTest
    public void teardown() {
        driver.quit();
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
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // Characters to choose from
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        
        // Generate random string by selecting characters randomly
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());  // Random index for characters string
            randomString.append(characters.charAt(index));    // Append the character at the random index
        }
        
        return randomString.toString();  // Convert StringBuilder to string and return
    }


   
}
