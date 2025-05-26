package com.basarsoft.opencart.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;

    By emailInput = By.id("input-email");
    By passwordInput = By.id("input-password");
    By loginBtn = By.xpath("//input[@value='Login']");
    By warningMessage = By.xpath("//div[contains(@class,'alert-danger')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String email, String password) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginBtn).click();
    }

    public boolean isLoginErrorDisplayed() {
        return driver.findElement(warningMessage).isDisplayed();
    }
    public String getWarningMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement warning = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[contains(@class,'alert')]")));
            return warning.getText();
        } catch (Exception e) {
            System.out.println("UYARI MESAJI BULUNAMADI → " + e.getMessage());
            return "";
        }
    }


}