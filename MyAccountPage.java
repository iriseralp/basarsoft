package com.basarsoft.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {
    WebDriver driver;

    By myAccountMenu = By.xpath("//span[text()='My Account']");
    By logoutBtn = By.linkText("Logout");

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLogout() {
        driver.findElement(logoutBtn).click();
    }

    public void openMyAccountMenu() {
        driver.findElement(myAccountMenu).click();
    }
}