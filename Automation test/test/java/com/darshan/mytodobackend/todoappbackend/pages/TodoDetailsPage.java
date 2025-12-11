package com.darshan.mytodobackend.todoappbackend.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TodoDetailsPage {

    private WebDriver driver;
    WebDriverWait wait;

    private By descriptionField = By.xpath("//input[@type='text']");
    private By targetDateField = By.name("targetDate");
    private By saveButton = By.xpath("//button[text()='Save']");
    private By descriptionError = By.xpath("//fieldset[label[text()='Description']]//div[contains(@class,'alert')]");
    private By targetDateError = By.xpath("//fieldset[label[text()='TargetDate']]//div[contains(@class,'alert')]");



    By targetDateErrorMessage = By.xpath("//div[contains(text(),'Please enter a date')]");


    public TodoDetailsPage(WebDriver driver) {
        this.driver = driver;
    }


    public void enterDescription(String description) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionField)).clear();
        driver.findElement(descriptionField).sendKeys(description);
    }

    public void enterTargetDate(String date) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement target = wait.until(ExpectedConditions.visibilityOfElementLocated(targetDateField));
        target.clear();
        target.sendKeys(date);
    }



    public void clickSave() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        save.click();
    }


    public void addTodo(String description, String date) {
        enterDescription(description);
        enterTargetDate(date);
        clickSave();
    }

    public boolean isDescriptionTooShortErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionError))
                    .getText().contains("at least 8 characters");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDescriptionInvalidErrorDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionError))
                    .getText().contains("Description must contain letters");
        } catch (Exception e) {
            return false;
        }
    }


}
