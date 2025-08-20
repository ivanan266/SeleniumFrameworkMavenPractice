package com.test.selenium.tests;

import com.test.selenium.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.test.selenium.pages.LoginPage;

public class ProductPageTest extends BaseTest {

    @Test
    public void verifyNumberOfProductsOnPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs(username, password);
        ProductPage productPage = new ProductPage(driver);
        // Provera da li je stranica učitana
        Assert.assertTrue(productPage.isProductPageLoaded(), "The article page did not load!");
        // Očekivani broj artikala (primer: 10)
        int expectedNumber = 6;
        int actualNumber = productPage.getProductCount();
        System.out.println("It is on the page " + actualNumber + " articles.");
        Assert.assertEquals(actualNumber, expectedNumber, "The number of items is incorrect!");
    }

    @Test
    public void verifyItemExistOnPage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs(username, password);
        driver.get("https://www.saucedemo.com/inventory.html");
        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isProductPresent("Sauce Labs Fleece Jacket"),
                "Article not found!"
        );
    }

    @Test
    public void printAllProductNamesAndPrices() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs(username, password);
        ProductPage productPage = new ProductPage(driver);
        productPage.printAllProductNamesAndPrices(); // Poziv metode koja ispisuje sve artikle
        // 4. Verifikuj URL
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        System.out.println("The current URL is: " + actualUrl);
        Assert.assertEquals(actualUrl, expectedUrl, "You are not on the Cart page.");
    }
}
