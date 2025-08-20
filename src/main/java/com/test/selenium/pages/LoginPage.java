package com.test.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    // Lokatori
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("h3[data-test='error']");
    private By inventoryList = By.className("inventory_list");
    // Konstruktor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    // Akcije
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
    public void loginAs(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
    public boolean isErrorMessageVisible() {
        return driver.findElement(errorMessage).isDisplayed();
    }
    public boolean isInventoryVisible() {
        return driver.findElement(inventoryList).isDisplayed();
    }
    public boolean isOnInventoryPage() {
        return driver.getCurrentUrl().contains("inventory.html"); //verify URL
    }
}
