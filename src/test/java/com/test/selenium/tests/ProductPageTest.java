package com.test.selenium.tests;

import com.test.selenium.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.test.selenium.pages.LoginPage;

public class ProductPageTest extends BaseTest {
    private LoginPage loginPage;
    private ProductPage productPage;

    @BeforeMethod
    public void initializePages() {
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        // Login pre svakog testa
        loginPage.loginAs(username, password);
    }

    @Test
    public void verifyNumberOfProductsOnPage() {
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
        driver.get("https://www.saucedemo.com/inventory.html");
        ProductPage productPage = new ProductPage(driver);
        Assert.assertTrue(productPage.isProductPresent("Sauce Labs Fleece Jacket"),
                "Article not found!"
        );
    }

    @Test
    public void printAllProductNamesAndPrices() {
        productPage.printAllProductNamesAndPrices(); // Poziv metode koja ispisuje sve artikle
        //  Verifikuj URL
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        System.out.println("The current URL is: " + actualUrl);
        Assert.assertEquals(actualUrl, expectedUrl, "You are not on the Cart page.");
        System.out.println(expectedUrl);
    }
}
