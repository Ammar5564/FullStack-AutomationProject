package com.example.todobackend.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WelcomePage {

    private WebDriver driver;

    private By todoslink = By.linkText("here");

    public WelcomePage(WebDriver driver)
    {
        this.driver=driver;
    }

    public void goToTodos()
    {
        driver.findElement(todoslink).click();
    }

    public boolean isHereLinkVisible() {
        return driver.findElement(todoslink).isDisplayed();
    }

    public boolean isHereLinkEnabled() {
        return driver.findElement(todoslink).isEnabled();
    }

    public String getHereLinkText() {
        return driver.findElement(todoslink).getText();
    }

    public void clickHereLink() {
        driver.findElement(todoslink).click();
    }

}
