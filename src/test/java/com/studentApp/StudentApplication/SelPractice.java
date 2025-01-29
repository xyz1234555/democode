package com.studentApp.StudentApplication;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SelPractice {

    public static void main(String[] args) {
        // Setting up ChromeDriver with WebDriverManager
        WebDriverManager.chromedriver().setup();

        // ChromeOptions to disable notifications
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        // Initialize WebDriver with ChromeOptions
        WebDriver driver = new ChromeDriver(options);

        // Set implicit wait for the driver
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Explicit wait for specific elements
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Maximize browser window
        driver.manage().window().maximize();

        // Salesforce login URL
        String URL = "https://ddl00000ef19puab-dev-ed.develop.my.salesforce.com/";
        driver.get(URL);

        try {
            // Logging into Salesforce
            driver.findElement(By.id("username")).sendKeys("automation@aut.com");
            driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Vishisth8778!");
            driver.findElement(By.xpath("//input[@id='Login']")).click();

            // Wait for and click on the Leads link
            WebElement appLauncher = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='App Launcher']")));
            appLauncher.click();
            System.out.println("appLauncher");
            WebElement searchBox=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Search apps and items...']")));
            searchBox.sendKeys("Leads");
            WebElement getLeads=wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//b[contains(text(),'Leads')]")));
            getLeads.click();
            WebElement newButton=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='New']")));
            newButton.click();
            
            WebElement getGlobalAction = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class,'globalCreateTrigger slds-button slds-button_icon slds-button_icon slds-button_icon-container slds-button_icon-small slds-global-actions__task slds-global-actions__item-action')]")));
            getGlobalAction.click();

            System.out.println("global action");
            
            
        } catch (Exception e) {
            System.out.println("Element could not be clicked: " + e.getMessage());
        } finally {
            // Close and quit the browser after operation
           // driver.quit();
        }
    }
}
