package com.basarsoft.opencart.pages;

import com.basarsoft.opencart.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final By myAccount = By.xpath("//span[text()='My Account']");
    private final By register = By.linkText("Register");
    private final By logoutLink = By.linkText("Logout");
    private final By loginLink = By.linkText("Login");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goToRegisterPage() {
        click(driver.findElement(myAccount));
        click(driver.findElement(register));
    }

    public void logout() {
        click(driver.findElement(myAccount));
        click(driver.findElement(logoutLink));
    }

    public void goToLoginPage() {
        click(driver.findElement(myAccount));
        click(driver.findElement(loginLink));
    }
}
