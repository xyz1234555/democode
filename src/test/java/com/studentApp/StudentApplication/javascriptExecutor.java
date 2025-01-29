package com.studentApp.StudentApplication;

import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class javascriptExecutor {
    String URL = "https://demowebshop.tricentis.com/login";

    ChromeOptions options = new ChromeOptions();
    WebDriver driver = new ChromeDriver(options);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @Test
    public void javascriptExecutorpractise() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get(URL);

        WebElement form1 = driver.findElement(By.xpath("//form[@action='/login']"));
        //driver.switchTo().frame(form1);

        WebElement Email = driver.findElement(By.xpath("//Label[contains(text(),'Email')]/following::input[@id='Email']"));
       
        js.executeScript("arguments[0].setAttribute('value','admin8778@gmail.com')", Email);

        WebElement pwd = driver.findElement(By.xpath("//Label[contains(text(),'Email')]/following::input[@id='Password']"));
    
        js.executeScript("arguments[0].setAttribute('value','Admin8778')", pwd);

        WebElement Login = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
      
        js.executeScript("arguments[0].click()", Login);
        driver.findElement(By.xpath("//img[@alt='Tricentis Demo Web Shop']")).isDisplayed();
        WebElement WebEle=driver.findElement(By.xpath("//div[contains(text(),'Copyright © 2024 Tricentis Demo Web Shop. All righ')]"));
        js.executeScript("arguments[0].scrollIntoView()", WebEle);
        js.executeScript("document.body.style.zoom='60'");
        driver.navigate().to("https://practice.expandtesting.com/upload");
        driver.findElement(By.xpath("//input[@id='fileInput']")).sendKeys("C:\\Users\\user\\Desktop\\iterview que.txt");
        driver.findElement(By.xpath("//button[@id='fileSubmit']")).click();
        driver.findElement(By.xpath("//div[@id='uploaded-files']")).isDisplayed();
    }
}
