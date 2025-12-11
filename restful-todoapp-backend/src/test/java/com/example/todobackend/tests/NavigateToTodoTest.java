package com.example.todobackend.tests;

import com.example.todobackend.pages.LoginPage;
import com.example.todobackend.pages.WelcomePage;
import com.example.todobackend.utils.ConfigReader;
import com.example.todobackend.utils.DriverManager;
import org.testng.Assert;
import org.testng.annotations.*;


public class NavigateToTodoTest extends BaseTest {

    //LoginPage loginPage;
   // WelcomePage welcomePage;

    @BeforeClass
    public void setup() {
        driver = DriverManager.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                {ConfigReader.get("username"), ConfigReader.get("password")}
        };
    }

    @Test(dataProvider = "loginData")
    public void navigateToTodoTest(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.goToTodos();

        Assert.assertTrue(driver.getCurrentUrl().contains("/list-todos"));
    }

    @Test(dataProvider = "loginData")
    public void testHereLinkVisibility(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        WelcomePage welcomePage = new WelcomePage(driver);
        Assert.assertTrue(welcomePage.isHereLinkVisible(), "'Here' link should be visible");
    }

    @Test(dataProvider = "loginData")
    public void testHereLinkEnabled(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        WelcomePage welcomePage = new WelcomePage(driver);
        Assert.assertTrue(welcomePage.isHereLinkEnabled(), "'Here' link should be enabled");
    }

    @Test(dataProvider = "loginData")
    public void testHereLinkText(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        WelcomePage welcomePage = new WelcomePage(driver);
        Assert.assertEquals(welcomePage.getHereLinkText(), "here", "Link text should be 'here'");
    }

    @Test(dataProvider = "loginData")
    public void testHereLinkClick(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        WelcomePage welcomePage = new WelcomePage(driver);
        welcomePage.clickHereLink();
        Assert.assertTrue(driver.getCurrentUrl().contains("/list-todos"), "URL should contain /list-todos");
    }


}
