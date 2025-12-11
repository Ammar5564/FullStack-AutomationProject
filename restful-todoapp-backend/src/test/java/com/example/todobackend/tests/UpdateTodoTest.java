package com.example.todobackend.tests;


import com.example.todobackend.pages.ListTodosPage;
import com.example.todobackend.pages.LoginPage;
import com.example.todobackend.pages.UpdateTodoPage;
import com.example.todobackend.pages.WelcomePage;
import com.example.todobackend.utils.ConfigReader;
import com.example.todobackend.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UpdateTodoTest extends BaseTest{

    UpdateTodoPage updateTodoPage;

    @BeforeClass
    public void setup() {
        driver = DriverManager.getDriver();
    }

    @DataProvider(name = "updateTodoData")
    public Object[][] updateTodoData() {
        return new Object[][]{
                {
                        ConfigReader.get("username"),
                        ConfigReader.get("password")
                }
        };
    }


    @Test(dataProvider = "updateTodoData")
    public void testUpdateTodoEndToEnd(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get(ConfigReader.get("base.url"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed");


        WelcomePage welcomePage = new WelcomePage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("here")));
        welcomePage.goToTodos();

        ListTodosPage listTodosPage = new ListTodosPage(driver);
        listTodosPage.waitForPageToLoad();


        WebElement lastUpdateButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[text()='Update'])[last()]")
        ));
        lastUpdateButton.click();


        UpdateTodoPage updateTodoPage = new UpdateTodoPage(driver);
        Assert.assertTrue(updateTodoPage.isUpdatePageDisplayed(), "Update page not displayed");

        String newDescription = "Updated Todo " + System.currentTimeMillis();
        LocalDate newDate = LocalDate.now().plusDays(14);
        String formattedDate = newDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        updateTodoPage.updateTodo(newDescription, formattedDate);


        wait.until(ExpectedConditions.urlContains("/list-todos"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/list-todos"));


        WebElement updatedTodo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[contains(text(),'" + newDescription + "')]")
        ));
        Assert.assertTrue(updatedTodo.isDisplayed(), "Todo not updated successfully");
    }


    @Test(dataProvider = "updateTodoData")
    public void testEmptyDescription(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get(ConfigReader.get("base.url"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.isLoginSuccessful());

        WelcomePage welcomePage = new WelcomePage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("here")));
        welcomePage.goToTodos();

        ListTodosPage listTodosPage = new ListTodosPage(driver);
        listTodosPage.waitForPageToLoad();
        listTodosPage.clickAddNewTodo();

        UpdateTodoPage updateTodoPage = new UpdateTodoPage(driver);


        WebElement descField = driver.findElement(By.xpath("//input[@type='text']"));
        descField.clear();


        WebElement dateField = driver.findElement(By.name("targetDate"));
        dateField.clear();
        dateField.sendKeys(LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));


        updateTodoPage.updateTodo("", dateField.getAttribute("value"));


        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(updateTodoPage.getDescriptionError()));
        Assert.assertTrue(error.isDisplayed(), "Description error not displayed!");
    }

    @Test(dataProvider = "updateTodoData")
    public void testEmptyTargetDate(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get(ConfigReader.get("base.url"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        WelcomePage welcomePage = new WelcomePage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("here")));
        welcomePage.goToTodos();

        ListTodosPage listTodosPage = new ListTodosPage(driver);
        listTodosPage.waitForPageToLoad();
        listTodosPage.clickAddNewTodo();

        UpdateTodoPage updateTodoPage = new UpdateTodoPage(driver);


        WebElement descField = driver.findElement(By.xpath("//input[@type='text']"));
        descField.clear();
        descField.sendKeys("Valid Description");

        WebElement dateField = driver.findElement(By.name("targetDate"));
        dateField.clear();


        updateTodoPage.updateTodo(descField.getAttribute("value"), "");

        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(updateTodoPage.getdateError()));
        Assert.assertTrue(error.isDisplayed(), "TargetDate error not displayed!");
    }

    @AfterClass
    public void teardown() {
        DriverManager.quitDriver();
    }

}
