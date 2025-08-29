package com.test.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;


public class CheckoutPage {
    private WebDriver driver;

    //Lokatori
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueButton = By.cssSelector(".submit-button");
    private By errorMessage = By.cssSelector("h3[data-test='error']");
    private By descInfo = By.cssSelector(".summary_info");
    private By totalInfo = By.cssSelector(".summary_total_label");
    private By finishButton = By.xpath("//button[@id='finish']");


    // Konstruktor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void firstName() {
        driver.findElement(firstNameField).sendKeys("Ivana");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void lastName() {
        driver.findElement(lastNameField).sendKeys("Marecic");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void postalCode() {
        driver.findElement(postalCode).sendKeys("34000");
    }

    public void continueBtn() {
        driver.findElement(continueButton).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public boolean isErrorMessageVisible() {
        return driver.findElement(errorMessage).isDisplayed();
    }

    public String overviewInfo() {
        return driver.findElement(descInfo).getText();
    }

    public String totalPrice() {
        return driver.findElement(totalInfo).getText();
    }

    public void finishBtn() {
        driver.findElement(finishButton).click();
    }
}


