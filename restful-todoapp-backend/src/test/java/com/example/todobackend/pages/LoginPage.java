package com.example.todobackend.pages; // Adjust package name if necessary

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TimeoutException; // Import TimeoutException
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List; // Import List for findElements check

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;


    By usernameField = By.xpath("//input[@type='text']");
    By passwordField = By.xpath("//input[@type='password']");
    By loginButton = By.xpath("//button[text()='Login']");
    By errorMessage = By.xpath("//div[contains(@class,'alert') and text()='Authentication Failed']");
    By welcomeHeader = By.xpath("//h1[contains(text(),'Welcome')]"); // عنصر يظهر بعد login ناجح

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).clear();
        driver.findElement(usernameField).sendKeys(username);

        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).clear();
        driver.findElement(passwordField).sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }


    public String getErrorMessage() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return error.getText();
        } catch (Exception e) {
            return "";
        }
    }


    public boolean isLoginSuccessful() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeHeader));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
