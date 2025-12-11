package com.darshan.mytodobackend.todoappbackend.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddTodoPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By header = By.xpath("//h1[contains(text(),'Enter your Todo Details:')]");
    private By addNewTodoButton = By.xpath("//div[contains(@class,'btn') and contains(text(),'Add New Todo')]");

    public AddTodoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isAddTodoPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(header))
                .isDisplayed();
    }




}
