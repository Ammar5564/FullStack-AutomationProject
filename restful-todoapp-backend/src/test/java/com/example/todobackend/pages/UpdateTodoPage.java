package com.example.todobackend.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UpdateTodoPage {


    private WebDriver driver;

    private By descriptionField = By.xpath("//input[@type='text']");
    private By targetDateField = By.name("targetDate");
    private By saveButton = By.xpath("//button[text()='Save']");
    private By formContainer = By.cssSelector("div.container");

    private By descriptionError = By.xpath("//div[contains(@class,'alert') and contains(text(),'atlease 8 characters')]");
    private By dateError = By.xpath("//div[contains(@class,'alert') and contains(text(),'Please enter a date')]");

    public UpdateTodoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void updateTodo(String newDescription, String newTargetDate) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(formContainer));

        WebElement desc = wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionField));
        desc.clear();
        desc.sendKeys(newDescription);

        WebElement date = wait.until(ExpectedConditions.visibilityOfElementLocated(targetDateField));
        date.clear();
        date.sendKeys(newTargetDate);

        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        save.click();
    }

    public boolean isUpdatePageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(saveButton));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDescriptionErrorDisplayed() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        try {
            return wait.until(d -> d.findElement(descriptionError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isDateErrorDisplayed() {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        try {
            return wait.until(d -> d.findElement(dateError)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public By getDescriptionError() {
        return descriptionError;
    }

    public By getdateError() {
        return dateError;
    }
}

