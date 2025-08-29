package com.test.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class CartPage {

    private WebDriver driver;

    // Konstruktor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCartIcon() {
        driver.findElement(By.cssSelector(".shopping_cart_container")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    public void clickContinueShopping() {
        driver.findElement(By.id("continue-shopping")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void removeItemBike() {
        driver.findElement(By.id("remove-sauce-labs-bike-light")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    public void clickCheckoutBtn() {
        driver.findElement(By.id("checkout")).click();

    }
}