package com.darshan.mytodobackend.todoappbackend.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DeleteTodoPage {

    private WebDriver driver;

    public DeleteTodoPage(WebDriver driver) {
        this.driver = driver;
    }


    public void clickDeleteLastTodo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement lastDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[text()='Delete'])[last()]")
        ));
        lastDeleteButton.click();
    }

    public boolean isTodoPresent(String description) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement todo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//td[contains(text(),'" + description + "')]")
            ));
            return todo.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void waitUntilTodoDisappears(String description) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//td[contains(text(),'" + description + "')]")
        ));
    }
}
