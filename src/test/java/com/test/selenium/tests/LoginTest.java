package com.test.selenium.tests;

import com.test.selenium.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("standard_user", "secret_sauce");

        Assert.assertTrue(loginPage.isInventoryVisible(), "Inventory list should be visible");
        Assert.assertTrue(loginPage.isOnInventoryPage(), "Should be on inventory page");
    }

    @Test
    public void testUnsuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("locked_out_user", "wrong_password");

        Assert.assertTrue(loginPage.isErrorMessageVisible(), "Error message should be visible");
    }
}
