package com.studentApp.StudentApplication;

import java.io.File;
import java.time.Duration;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScreenshotsAndChromeOption {
    String URL = "https://ddl00000ef19puab-dev-ed.develop.my.salesforce.com/";
    ChromeOptions options = new ChromeOptions();

    @Test
    public void screenshotsAndchromeOption() {
        // Specify the path to your ChromeDriver executable if necessary
        // System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Add extension to ChromeOptions
        File extensionFile = new File("C:\\Users\\user\\Documents\\Automation\\Salesforce-inspector-Chrome-Web-Store.crx");
        options.addExtensions(extensionFile);

        // Additional Chrome options
        options.addArguments("--disable-notifications");
        options.setAcceptInsecureCerts(true);

        // Initialize WebDriver with specified options
        WebDriver driver = new ChromeDriver(options);

        // Implicit wait setup
        driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(20));
        driver.manage().window().maximize();
        
        // Explicit wait setup
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        TakesScreenshot ts = (TakesScreenshot) driver;

        try {
            driver.get(URL);
            // Perform login
            js.executeScript("document.getElementById('username').value='automation@aut.com';");
            driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Vishisth8778!");
            driver.findElement(By.xpath("//input[@id='Login']")).click();

            // Wait and perform actions after login
            String validation1 = "New Lead";
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

            // Further operations on the Lead form...
            WebElement elementLeadInformation = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Lead Information')]")));
            highlightElement(driver, elementLeadInformation);
            elementLeadInformation.isDisplayed();

            WebElement elementLeadName = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='lastName']")));
            highlightElement(driver, elementLeadName);
            String expectedString = generateRandomString(9);
            elementLeadName.sendKeys(expectedString);
            System.out.println("expectedString: " + expectedString);

            WebElement elementCompany = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='Company']")));
            highlightElement(driver, elementCompany);
            elementCompany.sendKeys(generateRandomString(9));

            WebElement elementSave = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@name='SaveEdit']")));
            highlightElement(driver, elementSave);
            elementSave.click();
            
            // Wait for the page to load and get the Record ID
            Thread.sleep(5000);  // Adjust sleep based on page load time
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL: " + currentUrl);
            String recordId = currentUrl.substring(77, 95);  // Extracting Record ID from the URL
            System.out.println("Record ID: " + recordId);

            // Interact with the Salesforce inspector (extension icon)
            WebElement sfInspector = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='insext-btn']//img")));
            highlightElement(driver, sfInspector);
            sfInspector.click();
           // driver.switchTo().frame((WebElement) By.xpath("//iframe[@class='insext-popup']"));
            //driver.findElement(By.xpath("//li[@data-aspect='users']")).click();
            String iframeUrl = (String) ((JavascriptExecutor) driver).executeScript("return window.location.href;");
            ((JavascriptExecutor) driver).executeScript("window.open(arguments[0], '_blank');", iframeUrl);
            
            // Switch to the new tab
            Set<String> handles = driver.getWindowHandles();
            for (String handle : handles) {
                driver.switchTo().window(handle);
            // More actions if needed...
        }}catch (Exception e) {
            System.out.println("Element could not be clicked: " + e.getMessage());
        } finally {
            // Close the browser after the test execution
            // driver.quit();
        }
    }

    // Method to highlight elements for better visibility during automation
    public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String originalStyle = element.getAttribute("style");
        js.executeScript("arguments[0].setAttribute('style', 'border: 4px solid #4682B4; box-shadow: 0 0 10px #4682B4;');", element);
        try {
            Thread.sleep(500); // Highlight stays for a short period
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, originalStyle);
    }

    // Generate a random string of a given length
    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder randomString = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        return randomString.toString();
    }
}
