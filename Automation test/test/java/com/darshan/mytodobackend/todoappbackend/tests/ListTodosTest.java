package com.darshan.mytodobackend.todoappbackend.tests;

import com.darshan.mytodobackend.todoappbackend.pages.WelcomePage;
import com.darshan.mytodobackend.todoappbackend.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.darshan.mytodobackend.todoappbackend.pages.ListTodosPage;
import com.darshan.mytodobackend.todoappbackend.pages.LoginPage;

import java.time.Duration;


public class ListTodosTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                {ConfigReader.get("username"), ConfigReader.get("password")}
        };
    }

    @Test(dataProvider = "loginData")
    public void testListTodosPageLoadsCorrectlyWhenEmpty(String username, String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        wait.until(ExpectedConditions.urlContains("welcome/test"));
        Assert.assertTrue(driver.getCurrentUrl().contains("welcome/test"), "Did not land on welcome page after login");


        WelcomePage welcomePage = new WelcomePage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("here")));
        welcomePage.goToTodos();


        wait.until(ExpectedConditions.urlContains("/list-todos"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/list-todos"), "Did not navigate to list-todos");


        ListTodosPage listPage = new ListTodosPage(driver);
        listPage.waitForPageToLoad();
        Assert.assertTrue(listPage.isAddNewTodoDisplayed(), "Add New Todo button not displayed");
    }

    @Test(dataProvider = "loginData")
    public void testAddButtonIsDisplayed(String username, String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        wait.until(ExpectedConditions.urlContains("welcome/test"));
        Assert.assertTrue(driver.getCurrentUrl().contains("welcome/test"), "Did not land on welcome page after login");

        WelcomePage welcomePage = new WelcomePage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("here")));
        welcomePage.goToTodos();

        wait.until(ExpectedConditions.urlContains("/list-todos"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/list-todos"), "Did not navigate to list-todos");

        ListTodosPage listTodosPage = new ListTodosPage(driver);
        Assert.assertTrue(listTodosPage.isAddButtonDisplayed(), "Add button is not displayed!");
    }

    @Test(dataProvider = "loginData")
    public void testAddButtonIsClickable(String username, String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        wait.until(ExpectedConditions.urlContains("welcome/test"));
        Assert.assertTrue(driver.getCurrentUrl().contains("welcome/test"), "Did not land on welcome page after login");

        WelcomePage welcomePage = new WelcomePage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("here")));
        welcomePage.goToTodos();

        wait.until(ExpectedConditions.urlContains("/list-todos"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/list-todos"), "Did not navigate to list-todos");

        ListTodosPage listTodosPage = new ListTodosPage(driver);
        Assert.assertTrue(listTodosPage.isAddButtonClickable(), "Add button is not clickable!");
    }

}
