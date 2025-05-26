package com.basarsoft.opencart.pages;

import com.basarsoft.opencart.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage extends BasePage {

    private final By logoutMessage = By.xpath("//div[@id='content']/p[contains(text(),'logged off')]");

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoutMessageDisplayed() {
        return driver.findElement(logoutMessage).isDisplayed();
    }
}
