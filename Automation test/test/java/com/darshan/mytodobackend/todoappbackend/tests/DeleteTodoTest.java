package com.darshan.mytodobackend.todoappbackend.tests;


import com.darshan.mytodobackend.todoappbackend.pages.*;
import com.darshan.mytodobackend.todoappbackend.utils.ConfigReader;
import com.darshan.mytodobackend.todoappbackend.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DeleteTodoTest extends BaseTest {

    DeleteTodoPage deleteTodoPage;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = DriverManager.getDriver();
    }



    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                {ConfigReader.get("username"), ConfigReader.get("password")}
        };
    }

    @Test(dataProvider = "loginData")
    public void testDeleteLastTodo(String username, String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));


        driver.get(ConfigReader.get("base.url"));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login failed");


        WelcomePage welcomePage = new WelcomePage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("here")));
        welcomePage.goToTodos();

        deleteTodoPage = new DeleteTodoPage(driver);


        String lastTodoDescription = driver.findElement(
                By.xpath("(//table/tbody/tr[last()]/td[1])")
        ).getText();


        deleteTodoPage.clickDeleteLastTodo();


        deleteTodoPage.waitUntilTodoDisappears(lastTodoDescription);


        Assert.assertFalse(deleteTodoPage.isTodoPresent(lastTodoDescription), "cannot be deleted");
    }

    @AfterClass
    public void teardown() {
        DriverManager.quitDriver();
    }

}
