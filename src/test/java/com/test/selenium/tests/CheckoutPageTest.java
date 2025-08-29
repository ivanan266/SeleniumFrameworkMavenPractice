package com.test.selenium.tests;

import com.test.selenium.pages.CartPage;
import com.test.selenium.pages.LoginPage;
import com.test.selenium.pages.ProductPage;
import com.test.selenium.pages.CheckoutPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckoutPageTest extends BaseTest {

    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeMethod
    public void initializePages() {
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        // Login pre svakog testa
        loginPage.loginAs(username, password);
    }

    @Test
    public void testClickOnCheckoutBtn() {
        productPage.addBackpackToCart();
        cartPage.clickCartIcon();
        cartPage.clickCheckoutBtn();
        String expectedUrl = "https://www.saucedemo.com/checkout-step-one.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "You are not on the Checkout-step-one page.");
        System.out.println(expectedUrl);
    }

    @Test
    public void testFillFormSuccessfulAndVerifyUrl() {
        productPage.addBackpackToCart();
        cartPage.clickCartIcon();
        cartPage.clickCheckoutBtn();
        checkoutPage.firstName();
        checkoutPage.lastName();
        checkoutPage.postalCode();
        checkoutPage.continueBtn();
        String expectedUrl = "https://www.saucedemo.com/checkout-step-two.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "You are not on the Checkout-step-two page.");
        System.out.println(expectedUrl);

    }

    @Test
    public void testFillFormUnsuccessful() {
        productPage.addBackpackToCart();
        cartPage.clickCartIcon();
        cartPage.clickCheckoutBtn();
        checkoutPage.firstName();
        checkoutPage.postalCode();
        checkoutPage.continueBtn();
        Assert.assertTrue(checkoutPage.isErrorMessageVisible(), "Error: Last Name is required");

    }

    @Test
    public void testCheckoutOverviewPriceTotal() {
        productPage.addBackpackToCart();
        cartPage.clickCartIcon();
        cartPage.clickCheckoutBtn();
        checkoutPage.firstName();
        checkoutPage.lastName();
        checkoutPage.postalCode();
        checkoutPage.continueBtn();
        String price = checkoutPage.overviewInfo();
        System.out.println("Checkout Overview: " + price);
        String price1 = checkoutPage.totalPrice();
        System.out.println(price1);
        String price2 = "Total: $32.39";
        Assert.assertEquals(price1, price2, "They don't match.");
        checkoutPage.finishBtn();

    }
}
