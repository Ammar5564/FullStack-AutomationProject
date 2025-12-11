package com.example.todobackend.tests;

import com.example.todobackend.pages.ListTodosPage;
import com.example.todobackend.pages.LoginPage;
import com.example.todobackend.pages.TodoDetailsPage;
import com.example.todobackend.pages.WelcomePage;
import com.example.todobackend.utils.ConfigReader;
import com.example.todobackend.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TodoDetailsTest extends BaseTest{

    TodoDetailsPage todoDetailsPage;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {
        driver = DriverManager.getDriver();
    }


    @DataProvider(name = "todoData")
    public Object[][] todoData() {
        LocalDate date = LocalDate.now().plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = date.format(formatter);

        return new Object[][] {
                {"Selenium Test Todo 1", formattedDate}

        };
    }

    @Test(dataProvider = "todoData")
    public void testAddTodoEndToEndWithDataProvider(String description, String targetDate) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));


        driver.get(ConfigReader.get("base.url")); // صفحة login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed");


        WelcomePage welcomePage = new WelcomePage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("here")));
        welcomePage.goToTodos();


        ListTodosPage listTodosPage = new ListTodosPage(driver);
        listTodosPage.waitForPageToLoad();
        listTodosPage.clickAddNewTodo();


        todoDetailsPage = new TodoDetailsPage(driver);
        By formContainer = By.cssSelector("div.container");
        wait.until(ExpectedConditions.visibilityOfElementLocated(formContainer));


        todoDetailsPage.addTodo(description, targetDate);


        wait.until(ExpectedConditions.urlContains("/list-todos"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/list-todos"),"After saving , not turn back to the page");


        WebElement newTodo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[contains(text(),'" + description + "')]")
        ));
        Assert.assertTrue(newTodo.isDisplayed(), "new todo not existing in the list");
    }




    @DataProvider(name = "emptyDescriptionData")
    public Object[][] emptyDescriptionData() {
        LocalDate date = LocalDate.now().plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = date.format(formatter);

        return new Object[][] {
                {"", formattedDate}
        };
    }

    @Test(dataProvider = "emptyDescriptionData")
    public void testEmptyDescriptionField(String description, String targetDate) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));


        driver.get(ConfigReader.get("base.url"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed");


        WelcomePage welcomePage = new WelcomePage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("here")));
        welcomePage.goToTodos();


        ListTodosPage listTodosPage = new ListTodosPage(driver);
        listTodosPage.waitForPageToLoad();
        listTodosPage.clickAddNewTodo();


        todoDetailsPage = new TodoDetailsPage(driver);
        By formContainer = By.cssSelector("div.container");
        wait.until(ExpectedConditions.visibilityOfElementLocated(formContainer));


        todoDetailsPage.addTodo(description, targetDate);


        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//fieldset[label[text()='Description']]//div[contains(@class,'alert')]")
        ));


        Assert.assertTrue(errorElement.isDisplayed(), "Error message for empty description is not displayed!");
    }


    @AfterMethod
    public void teardown() {
        DriverManager.quitDriver();
    }
}
