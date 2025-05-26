package com.basarsoft.opencart.tests;

import com.basarsoft.opencart.pages.HomePage;
import com.basarsoft.opencart.pages.RegisterPage;
import com.basarsoft.opencart.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTestNegatives {

    WebDriver driver;

    public void goToRegisterPage() {
        driver = Driver.getDriver();
        driver.get("https://opencart.abstracta.us/");
        HomePage homePage = new HomePage(driver);
        homePage.goToRegisterPage();
    }

    @Test
    public void testEmptyFormSubmission() {
        goToRegisterPage();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickContinue();
        Assert.assertTrue(registerPage.isStillOnRegisterPage(),
                "Boş form gönderimi sonrası sayfa yönlenmemeli!");
        Driver.closeDriver();
    }

    @Test
    public void testMismatchedPasswords() {
        goToRegisterPage();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillPersonalDetails("Iris", "Eralp", "test1234@mail.com", "05001112233", "Test1234");
        registerPage.setConfirmPassword("Different123");
        registerPage.selectNewsletterNo();
        registerPage.acceptPrivacyPolicy();
        registerPage.clickContinue();
        Assert.assertTrue(registerPage.isStillOnRegisterPage(),
                "Farklı şifreler girildiğinde sayfa yönlenmemeli!");
        Driver.closeDriver();
    }

    @Test
    public void testInvalidEmailFormat() {
        goToRegisterPage();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillPersonalDetails("Iris", "Eralp", "invalid-email", "05001112233", "Test1234");
        registerPage.selectNewsletterNo();
        registerPage.acceptPrivacyPolicy();
        registerPage.clickContinue();
        Assert.assertTrue(registerPage.isStillOnRegisterPage(),
                "Geçersiz e-mail ile form gönderimi sonrası sayfa yönlenmemeli!");
        Driver.closeDriver();
    }

    @Test
    public void testUncheckedPrivacyPolicy() {
        goToRegisterPage();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillPersonalDetails("Iris", "Eralp", "test1234@mail.com", "05001112233", "Test1234");
        registerPage.selectNewsletterNo();
        registerPage.clickContinue();
        Assert.assertTrue(registerPage.isStillOnRegisterPage(),
                "Gizlilik politikası onaylanmadan form gönderildiğinde sayfa yönlenmemeli!");
        Driver.closeDriver();
    }
}
