package com.darshan.mytodobackend.todoappbackend.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ListTodosPage {

    private WebDriver driver;
    private WebDriverWait wait;


    private By addNewTodoButton = By.xpath("//div[contains(@class,'btn') and contains(text(),'Add New Todo')]");
    private By todosTable = By.xpath("//table[contains(@class,'table')]");
    private By todosRows = By.xpath("//table//tbody/tr");


    public ListTodosPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Wait until page loads
    public void waitForPageToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addNewTodoButton));
    }

    public boolean isAddNewTodoDisplayed() {
        return driver.findElement(addNewTodoButton).isDisplayed();
    }

    public boolean isTodosTableDisplayed() {
        // table may or may not exist (if user has no todos)
        return !driver.findElements(todosTable).isEmpty();
    }

    public int getTodosCount() {
        List<WebElement> rows = driver.findElements(todosRows);
        return rows.size();
    }

    public void clickAddNewTodo() {
        wait.until(ExpectedConditions.elementToBeClickable(addNewTodoButton)).click();
    }

    public boolean isAddButtonDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(addNewTodoButton))
                    .isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAddButtonClickable() {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(addNewTodoButton))
                    .isEnabled();
        } catch (Exception e) {
            return false;
        }
    }




}
