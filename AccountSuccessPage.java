package com.basarsoft.opencart.pages;

import com.basarsoft.opencart.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountSuccessPage extends BasePage {

    private final By successMessage = By.xpath("//div[@id='content']/p[contains(text(),'Congratulations')]");
    private final By continueBtn = By.xpath("//a[contains(text(),'Continue')]");

    public AccountSuccessPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSuccessMessageDisplayed() {
        return driver.findElement(successMessage).isDisplayed();
    }

    public void clickContinue() {
        click(driver.findElement(continueBtn));
    }
} 
