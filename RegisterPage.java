package com.basarsoft.opencart.pages;

import com.basarsoft.opencart.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    private final By firstName = By.id("input-firstname");
    private final By lastName = By.id("input-lastname");
    private final By email = By.id("input-email");
    private final By telephone = By.id("input-telephone");
    private final By password = By.id("input-password");
    private final By confirmPassword = By.id("input-confirm");
    private final By newsletterNo = By.xpath("//input[@name='newsletter' and @value='0']");
    private final By privacyPolicy = By.name("agree");
    private final By continueBtn = By.xpath("//input[@value='Continue']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void fillPersonalDetails(String name, String surname, String emailStr, String phone, String pass) {
        sendKeys(driver.findElement(firstName), name);
        sendKeys(driver.findElement(lastName), surname);
        sendKeys(driver.findElement(email), emailStr);
        sendKeys(driver.findElement(telephone), phone);
        sendKeys(driver.findElement(password), pass);
        sendKeys(driver.findElement(confirmPassword), pass);
    }

    public void setConfirmPassword(String confirmPass) {
        driver.findElement(confirmPassword).clear();
        sendKeys(driver.findElement(confirmPassword), confirmPass);
    }

    public void selectNewsletterNo() {
        click(driver.findElement(newsletterNo));
    }

    public void acceptPrivacyPolicy() {
        click(driver.findElement(privacyPolicy));
    }

    public void clickContinue() {
        click(driver.findElement(continueBtn));
    }

    public boolean isStillOnRegisterPage() {
        return driver.getCurrentUrl().contains("route=account/register");
    }
} 
