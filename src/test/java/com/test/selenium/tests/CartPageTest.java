package com.test.selenium.tests;

import com.test.selenium.pages.CartPage;
import com.test.selenium.pages.LoginPage;
import com.test.selenium.pages.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class CartPageTest extends BaseTest {

    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;

    @BeforeMethod
    public void initializePages() {
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);

        // Login pre svakog testa
        loginPage.loginAs(username, password);
    }

    @Test
    public void testAddBikeToCartChangesButtonText() {
        productPage.addBikeToCart();
        String buttonText = productPage.getBikeCartButtonText();
        Assert.assertEquals(buttonText, "Remove", "The button has not changed to 'Remove'");
    }

    @Test
    public void testGoToCartPageAndVerifyUrl() {
        productPage.addBikeToCart();
        cartPage.clickCartIcon();
        String expectedUrl = "https://www.saucedemo.com/cart.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "You are not on the Cart page.");
    }

    @Test
    public void testAddSecondProductAfterContinueShopping() {
        productPage.addBikeToCart();
        cartPage.clickCartIcon();
        cartPage.clickContinueShopping();
        productPage.addBackpackToCart();
        cartPage.clickCartIcon();

        // Verifikuj da je Backpack dodat
        String btnText = productPage.getBackpackCartButtonText();
        Assert.assertEquals(btnText, "Remove", "Backpack not added to cart");

    }

    @Test
    public void testRemoveItemAndVerifyItIsGone() {
        productPage.addBikeToCart();
        productPage.addBackpackToCart();
        cartPage.clickCartIcon();
        cartPage.removeItemBike();

        List<WebElement> removedItem = driver.findElements(By.id("remove-sauce-labs-bike-light"));
        Assert.assertTrue(removedItem.isEmpty(), "Item was not successfully removed from the cart");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void testClickOnCheckoutBtn() {
        productPage.addBackpackToCart();
        cartPage.clickCartIcon();
        cartPage.clickCheckoutBtn();
        String expectedUrl = "https://www.saucedemo.com/checkout-step-one.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "You are not on the Checkout page.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}