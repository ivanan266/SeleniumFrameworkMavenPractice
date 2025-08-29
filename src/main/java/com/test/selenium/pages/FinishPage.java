package com.test.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class FinishPage {
    private WebDriver driver;

    //Lokatori
    private By orderText = By.className("checkout_complete_container");
    private By backHomeButton = By.xpath("button[@id='back-to-products']");

    // Konstruktor
    public FinishPage(WebDriver driver) {
        this.driver = driver;
    }
    public String isOrderMessageVisible() {
        return driver.findElement(orderText).getText();
    }
    public void backHomeBtn() {
        driver.findElement(backHomeButton).click();
    }
}