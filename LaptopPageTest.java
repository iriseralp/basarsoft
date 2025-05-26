package com.basarsoft.opencart.tests;

import com.basarsoft.opencart.pages.LaptopPage;
import com.basarsoft.opencart.utils.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LaptopPageTest {

    private WebDriver driver;
    private LaptopPage laptopPage;

    @BeforeMethod
    public void setUp() {
        driver = Driver.getDriver();
        driver.get("https://opencart.abstracta.us/");
        laptopPage = new LaptopPage(driver);
    }

    @Test(priority = 1)
    public void testHoverLaptopsMenu() {
        Assert.assertTrue(laptopPage.areSubLinksVisible(), "Alt menü bağlantıları görünmüyor!");
        System.out.println("✅ Hover testi: Mac, Windows ve Show All linkleri görüntülendi.");
    }

    @Test(priority = 2)
    public void testNavigateToMacs() {
        laptopPage.clickMacLink();
        Assert.assertEquals(laptopPage.getPageTitle(), "Macs", "Macs sayfası açılmadı!");
        System.out.println("✅ Macs sayfasına başarıyla gidildi.");
    }

    @Test(priority = 3)
    public void testNavigateToWindows() {
        laptopPage.clickWindowsLink();
        Assert.assertEquals(laptopPage.getPageTitle(), "Windows", "Windows sayfası açılmadı!");
        System.out.println("✅ Windows sayfasına başarıyla gidildi.");
    }

    @Test(priority = 4)
    public void testShowAllLaptops() {
        laptopPage.clickShowAllLink();
        Assert.assertTrue(driver.getCurrentUrl().contains("path=18"), "Tüm Laptops & Notebooks sayfası açılmadı.");
        System.out.println("✅ Tüm Laptops & Notebooks sayfası başarıyla açıldı.");
    }

    @Test(priority = 5)
    public void testProductDetailsDisplayed() {
        laptopPage.clickShowAllLink();
        Assert.assertTrue(laptopPage.areAllProductDetailsVisible(), "Ürün detayları veya aksiyon butonları eksik!");
        System.out.println("✅ Ürün detayları ve aksiyon butonları başarıyla kontrol edildi.");
    }
    /* @Test(priority= 6)
    public void testSellerContactButtonVisibilityForExpensiveProducts() {
        LaptopPage laptopPage = new LaptopPage(driver);
        laptopPage.clickShowAllLink();

        List<WebElement> productElements = driver.findElements(By.cssSelector(".product-layout"));
        for (WebElement product : productElements) {
            String priceText = product.findElement(By.cssSelector(".price")).getText().replaceAll("[^\\d.]", "");
            double price = Double.parseDouble(priceText);

            if (price >= 1000) {
                List<WebElement> contactButtons = product.findElements(By.xpath(".//button[contains(text(),'Satıcı İle Konuş')]"));
                Assert.assertTrue(contactButtons.size() > 0, "1000$ üzeri üründe 'Satıcı İle Konuş' butonu bulunamadı!");
            }
        }
    }
*/
    
    @Test(priority = 7)
    public void testProductActionButtons() {
        laptopPage.clickShowAllLink();
        Assert.assertTrue(laptopPage.areProductActionButtonsVisible(), "Aksiyon butonlarından biri eksik!");
    }

    @AfterMethod
    public void tearDown() {
        Driver.closeDriver();
    }
} 
