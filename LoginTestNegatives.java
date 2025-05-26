package com.basarsoft.opencart.tests;

import com.basarsoft.opencart.pages.HomePage;
import com.basarsoft.opencart.pages.LoginPage;
import com.basarsoft.opencart.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTestNegatives {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = Driver.getDriver();
        driver.get("https://opencart.abstracta.us/");

        HomePage homePage = new HomePage(driver);
        homePage.goToLoginPage();

        loginPage = new LoginPage(driver);
    }

    @Test
    public void testInvalidEmailLogin() {
        loginPage.login("haha@@mail.com", "1234");

        String warningMessage = loginPage.getWarningMessage();
        System.out.println("Login sonrası uyarı mesajı: " + warningMessage);

        if (warningMessage.toLowerCase().contains("no match") ||
            warningMessage.toLowerCase().contains("email")) {
            Assert.assertTrue(true);
        } else {
            Assert.fail("Beklenen uyarı mesajı görünmedi. Gelen mesaj: " + warningMessage);
        }
    }

    @Test
    public void testInvalidPasswordLogin() {
        loginPage.login("ietest@test.com", "yanlisSifre123");
        String warning = loginPage.getWarningMessage();
        System.out.println("Görülen uyarı mesajı (Şifre): " + warning);

        Assert.assertTrue(
            warning.contains("No match for E-Mail Address and/or Password.") ||
            warning.contains("Your account has exceeded allowed number of login attempts"),
            "Hatalı şifre uyarısı görünmedi!"
        );
    }

    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
    }
}
