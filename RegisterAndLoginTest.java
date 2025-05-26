package com.basarsoft.opencart.tests;

import com.basarsoft.opencart.pages.*;
import com.basarsoft.opencart.utils.Driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterAndLoginTest {
    WebDriver driver;

    public String generateEmail() {
        return "user" + System.currentTimeMillis() + "@mail.com";
    }

    @Test
    public void testRegisterLoginLogoutFlow() {
        driver = Driver.getDriver();
        driver.get("https://opencart.abstracta.us/");

        // 1-4. Kayıt işlemleri
        HomePage home = new HomePage(driver);
        home.goToRegisterPage();

        RegisterPage register = new RegisterPage(driver);
        String email = generateEmail();
        register.fillPersonalDetails("test", "testie", email, "05531112255", "1234");
        register.selectNewsletterNo();
        register.acceptPrivacyPolicy();
        register.clickContinue();

        // 7. Başarılı kayıt kontrolü
        AccountSuccessPage successPage = new AccountSuccessPage(driver);
        Assert.assertTrue(successPage.isSuccessMessageDisplayed(), "Kayıt başarılı mesajı görünmedi.");
        successPage.clickContinue();

        // 8. My Account sayfası açıldı mı
        MyAccountPage accountPage = new MyAccountPage(driver);
        accountPage.openMyAccountMenu();

        // 10. Logout
        accountPage.clickLogout();

        // 11. Çıkış mesajı kontrolü
        LogoutPage logoutPage = new LogoutPage(driver);
        Assert.assertTrue(logoutPage.isLogoutMessageDisplayed(), "Çıkış yapıldığı mesajı görünmedi.");

        // Login sayfasına dön
        home.goToRegisterPage(); // tekrar MyAccount > Login için
        driver.findElement(By.linkText("Login")).click();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, "1234");

        Assert.assertTrue(driver.getPageSource().contains("My Account"),
                "Login sonrası 'My Account' sayfası açılmadı.");

        Driver.closeDriver();
    }
}
