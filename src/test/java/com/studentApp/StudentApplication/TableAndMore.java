package com.studentApp.StudentApplication;

import java.time.Duration;
import java.util.Arrays;  // Import Arrays class for sorting
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class TableAndMore {
    String URL = "https://blazedemo.com/";
    double[] price;  // Array to store prices
    ChromeOptions options = new ChromeOptions();
    WebDriver driver = new ChromeDriver(options);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    @Test
    public void statictable() {
        driver.get(URL);
        driver.manage().window().maximize();

        // Selecting departure city
        WebElement Elementdropdown = driver.findElement(By.xpath("//select[@name='fromPort']"));
        Select dropDown = new Select(Elementdropdown);
        dropDown.selectByIndex(4);

        // Clicking on 'Find Flights' button
        driver.findElement(By.xpath("//input[@value='Find Flights']")).click();

        // Get the total number of rows in the table
        int row = driver.findElements(By.xpath("//table[@class='table']//tr")).size();
        int col = driver.findElements(By.xpath("//table[@class='table']//th")).size();

        // Initialize the price array based on the number of rows (excluding the header row)
        price = new double[row - 1];  // Subtracting 1 for the header row

        // Loop through each row to extract the prices
        for (int i = 1; i < row; i++) {
            // Get the price string (which includes the $ symbol)
            String pricestring = driver.findElement(By.xpath("//table[@class='table']//tr[" + i + "]//td[6]")).getText();
            // Remove the "$" symbol and any extra spaces
            String newP = pricestring.replace("$", "").trim(); // Fixed here: removed "$" and trimmed the string

            System.out.println("Price string: " + newP);

            // Parse the string to a double
            price[i - 1] = Double.parseDouble(newP); // Storing in price[i - 1] (0-indexed array)
            System.out.println("Price: " + price[i - 1]);
        }

        // Sort the array to find the minimum value
        Arrays.sort(price);  // Sort the array in ascending order

        // The minimum price will be the first element after sorting
        Double minimum = price[0];
        String minimumS = "$" + Double.toString(minimum);  // Adding the "$" symbol back to the minimum price
        System.out.println("The minimum price is: " + minimumS); // Printing minimum price with $

        // Loop through the rows again to find the corresponding flight and click on it
        for (int i = 1; i < row; i++) {
            String pricestringActual = driver.findElement(By.xpath("//table[@class='table']//tr[" + i + "]//td[6]")).getText();
            // Compare the actual price with the minimum price string
            if (pricestringActual.equals(minimumS)) {
                // Click on the first column (flight) of the row with the minimum price
                driver.findElement(By.xpath("//table[@class='table']//tr[" + i + "]//td[1]")).click();
                break;  // Stop the loop once the flight is found
            }
        }

        // Close the browser at the end
        driver.close();
        driver.quit();
    }
}
