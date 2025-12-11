package com.example.todobackend.tests;

import com.example.todobackend.pages.LoginPage;
import com.example.todobackend.utils.DriverManager;
import com.example.todobackend.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest{

    @BeforeClass
    public void setup() {
        driver = DriverManager.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() {
        return new Object[][] { { ConfigReader.get("username"), ConfigReader.get("password") } };
    }

    @DataProvider(name = "invalidPasswordData")
    public Object[][] invalidPasswordData() {
        return new Object[][] { { ConfigReader.get("username"), "wrongPassword" } };
    }

    @DataProvider(name = "invalidUsernameData")
    public Object[][] invalidUsernameData() {
        return new Object[][] { { "wrongUser", ConfigReader.get("password") } };
    }

    @DataProvider(name = "emptyUsernameData")
    public Object[][] emptyUsernameData() {
        return new Object[][] { { "", ConfigReader.get("password") } };
    }

    @DataProvider(name = "emptyPasswordData")
    public Object[][] emptyPasswordData() {
        return new Object[][] { { ConfigReader.get("username"), "" } };
    }

    @Test(dataProvider = "validLoginData")
    public void testValidLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        System.out.println("Current URL after login: " + driver.getCurrentUrl());
        System.out.println("Page snippet: " + driver.getPageSource().substring(0, 200));

        Assert.assertTrue(loginPage.isLoginSuccessful(), "Valid login failed for: " + username);
    }

    @Test(dataProvider = "invalidPasswordData")
    public void testInvalidPassword(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.getErrorMessage().contains("Authentication Failed"),
                "Error message not displayed for wrong password");
    }

    @Test(dataProvider = "invalidUsernameData")
    public void testInvalidUsername(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.getErrorMessage().contains("Authentication Failed"),
                "Error message not displayed for wrong username");
    }

    @Test(dataProvider = "emptyUsernameData")
    public void testEmptyUsername(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.getErrorMessage().contains("Authentication Failed"),
                "Error message not displayed for empty username");
    }

    @Test(dataProvider = "emptyPasswordData")
    public void testEmptyPassword(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.getErrorMessage().contains("Authentication Failed"),
                "Error message not displayed for empty password");
    }

    @AfterClass
    public void teardown() {
        DriverManager.quitDriver();
    }
}
