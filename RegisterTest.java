package com.basarsoft.opencart.tests;

import com.basarsoft.opencart.base.BasePage;
import com.basarsoft.opencart.pages.HomePage;
import com.basarsoft.opencart.pages.RegisterPage;
import com.basarsoft.opencart.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest {

    public String generateEmail() {
        return "user" + System.currentTimeMillis() + "@mail.com";
    }

    @Test
    public void testSuccessfulRegistration() {
        WebDriver driver = Driver.getDriver();
        driver.get("https://opencart.abstracta.us/");

        HomePage homePage = new HomePage(driver);
        homePage.goToRegisterPage();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.fillPersonalDetails("Iris", "Eralp", generateEmail(), "05001112233", "Test1234");
        registerPage.selectNewsletterNo();
        registerPage.acceptPrivacyPolicy();
        registerPage.clickContinue();

        Assert.assertTrue(driver.getPageSource().contains("Your Account Has Been Created!"),
                "Kayıt başarısız oldu!");

        Driver.closeDriver();
    }
}
