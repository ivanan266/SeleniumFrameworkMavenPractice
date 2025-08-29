package com.test.selenium.tests;

import com.test.selenium.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FinishPageTest extends BaseTest {

    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private FinishPage finishPage;

    @BeforeMethod
    public void initializePages() {
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        finishPage = new FinishPage(driver);

        // Login pre svakog testa
        loginPage.loginAs(username, password);
    }

    @Test
    public void testOrderCompletionAndUrlRedirection() {
        productPage.addBackpackToCart();
        cartPage.clickCartIcon();
        cartPage.clickCheckoutBtn();
        checkoutPage.firstName();
        checkoutPage.lastName();
        checkoutPage.postalCode();
        checkoutPage.continueBtn();
        checkoutPage.finishBtn();
        String expectedUrl = "https://www.saucedemo.com/checkout-complete.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "You are not on the Checkout-complete page.");
        System.out.println(expectedUrl);
        System.out.println("✅ Order success message: " + finishPage.isOrderMessageVisible());

    }
    public void testBackHomeButtonNavigatesToHomePage() {
        productPage.addBackpackToCart();
        cartPage.clickCartIcon();
        cartPage.clickCheckoutBtn();
        checkoutPage.firstName();
        checkoutPage.lastName();
        checkoutPage.postalCode();
        checkoutPage.continueBtn();
        checkoutPage.finishBtn();
        finishPage.backHomeBtn();
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        System.out.println("The current URL is: " + actualUrl);
        Assert.assertEquals(actualUrl, expectedUrl, "You are not on the Inventory page.");
        System.out.println(expectedUrl);
    }
}