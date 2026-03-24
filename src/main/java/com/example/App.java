package com.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class App {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Open website
            driver.get("https://automationexercise.com/");

            // Wait for page load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Scroll down
            js.executeScript("window.scrollBy(0,600)");

            // Locate Add to Cart button
            WebElement addToCart = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.xpath("(//a[@class='btn btn-default add-to-cart'])[1]")
                    )
            );

            // Scroll into view
            js.executeScript("arguments[0].scrollIntoView(true);", addToCart);

            // Wait until clickable
            wait.until(ExpectedConditions.elementToBeClickable(addToCart));

            // Click using JS (same as your logic)
            js.executeScript("arguments[0].click();", addToCart);

            // Wait for Continue Shopping button
            WebElement continueBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(text(),'Continue Shopping')]")
                    )
            );

            // Click continue shopping
            continueBtn.click();

            System.out.println("Product added and continued shopping ✅");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close browser
            driver.quit();
        }
    }
}
