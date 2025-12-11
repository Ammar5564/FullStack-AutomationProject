package com.example.todobackend.tests;

import com.example.todobackend.pages.AddTodoPage;
import com.example.todobackend.pages.ListTodosPage;
import com.example.todobackend.pages.LoginPage;
import com.example.todobackend.pages.WelcomePage;
import com.example.todobackend.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddNewTodoButtonTest extends BaseTest{

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
                {ConfigReader.get("username"), ConfigReader.get("password")}
        };
    }

    @Test(dataProvider = "loginData")
    public void testAddNewTodoNavigation(String username, String password) {


        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);


        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.goToTodos();


        ListTodosPage listTodosPage = new ListTodosPage(driver);
        listTodosPage.waitForPageToLoad();


        listTodosPage.clickAddNewTodo();


        AddTodoPage addTodoPage = new AddTodoPage(driver);
        Assert.assertTrue(addTodoPage.isAddTodoPageDisplayed(),
                "Add Todo Page did not open!");


        Assert.assertTrue(driver.getCurrentUrl().contains("/list-todos/-1"),
                "URL is incorrect after clicking Add New Todo!");
    }

//    @Test
//    public void testAddNewTodoNavigation() {
//
//        // 1) Login
//        LoginPage loginPage = new LoginPage(driver);
//        loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
//
//        // 2) Welcome page → click "here"
//        WelcomePage welcomePage = new WelcomePage(driver);
//        welcomePage.goToTodos();
//
//        // 3) الآن في list-todos
//        ListTodosPage listTodosPage = new ListTodosPage(driver);
//        listTodosPage.waitForPageToLoad();
//
//        // 4) اضغط Add New Todo
//        listTodosPage.clickAddNewTodo();
//
//        // 5) الآن نصل لصفحة /list-todos/-1
//        AddTodoPage addTodoPage = new AddTodoPage(driver);
//        Assert.assertTrue(addTodoPage.isAddTodoPageDisplayed());
//
//        // 6) Verify URL
//        Assert.assertTrue(driver.getCurrentUrl().contains("/list-todos/-1"));
//    }



}
