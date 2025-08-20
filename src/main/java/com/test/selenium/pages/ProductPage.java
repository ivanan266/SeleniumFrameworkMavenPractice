package com.test.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class ProductPage {
    private WebDriver driver;

    // Konstruktor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Lokator za artikle
    private By productItems = By.cssSelector(".inventory_item"); // <-- promeni ako je drugačiji selektor
    private By productTitles = By.className("inventory_item_name");

    // Metoda koja vraća sve artikle na stranici
    public List<WebElement> getAllProducts() {
        return driver.findElements(productItems);
    }

    // Metoda koja vraća broj artikala
    public int getProductCount() {
        return getAllProducts().size();
    }

    // Opcionalno: proveri da li je stranica učitana (npr. preko nekog elementa)
    public boolean isProductPageLoaded() {
        return driver.findElements(productItems).size() > 0;
    }

    public boolean isProductPresent(String productName) {
        List<WebElement> products = driver.findElements(productTitles);
        for (WebElement product : products) {

            if (product.getText().trim().equalsIgnoreCase(productName.trim())) {
                System.out.println("Article: " + product.getText());
                return true;
            }
        }
        return false;
    }

    // Ispisuje sve artikle sa nazivom i cenom
    public void printAllProductNamesAndPrices() {
        List<WebElement> items = driver.findElements(productItems);
        System.out.println("🔽 Article with prices:");

        for (WebElement item : items) {
            String name = item.findElement(By.className("inventory_item_name")).getText();
            String price = item.findElement(By.className("inventory_item_price")).getText();
            System.out.println("✔ " + name + " | " + price);
        }
    }

    public void addBikeToCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public String getBikeCartButtonText() {
        // Nakon klika, ID se menja — sad je dugme "Remove"
        return driver.findElement(By.id("remove-sauce-labs-bike-light")).getText();
    }

    public void addBackpackToCart() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public String getBackpackCartButtonText() {
        return driver.findElement(By.id("remove-sauce-labs-backpack")).getText();
    }
}




