package com.basarsoft.opencart.pages;

import com.basarsoft.opencart.base.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LaptopPage extends BasePage {

    private final By laptopsMenu = By.linkText("Laptops & Notebooks");
    private final By macLink = By.partialLinkText("Mac");
    private final By windowsLink = By.partialLinkText("Windows");
    private final By showAllLink = By.linkText("Show All Laptops & Notebooks");
    private final By pageHeading = By.cssSelector("#content h2");
    private final By productBlocks = By.cssSelector(".product-layout");

    private final Actions actions;

    public LaptopPage(WebDriver driver) {
        super(driver);
        this.actions = new Actions(driver);
    }

    public void hoverLaptopsMenu() {
        WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(laptopsMenu));
        actions.moveToElement(menu).perform();
    }

    public boolean areSubLinksVisible() {
        hoverLaptopsMenu();
        boolean macVisible = driver.findElement(macLink).isDisplayed();
        boolean showAllVisible = driver.findElement(showAllLink).isDisplayed();
        boolean windowsVisible = !driver.findElements(windowsLink).isEmpty();

        return macVisible && showAllVisible && windowsVisible;
    }


    public void clickMacLink() {
        hoverLaptopsMenu();
        click(driver.findElement(macLink));
    }

    public void clickWindowsLink() {
        hoverLaptopsMenu();
        click(driver.findElement(windowsLink));
    }

    public void clickShowAllLink() {
        hoverLaptopsMenu();

        wait.until(ExpectedConditions.visibilityOfElementLocated(showAllLink));

        click(driver.findElement(showAllLink));
    }
    public String getPageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeading)).getText();
    }
    
    public boolean areProductActionButtonsVisible() {
        List<WebElement> products = driver.findElements(productBlocks);

        for (WebElement product : products) {
            boolean cart = product.findElement(By.cssSelector("button[onclick*='cart.add']")).isDisplayed();
            boolean wish = product.findElement(By.cssSelector("button[data-original-title='Add to Wish List']")).isDisplayed();
            boolean compare = product.findElement(By.cssSelector("button[data-original-title='Compare this Product']")).isDisplayed();

            if (!cart || !wish || !compare) {
                return false;
            }
        }
        return true;
    }


    public boolean areAllProductDetailsVisible() {
        List<WebElement> products = driver.findElements(productBlocks);
        for (WebElement product : products) {
            boolean hasImage = product.findElement(By.cssSelector("img")).isDisplayed();
            boolean hasTitle = product.findElement(By.cssSelector("h4 a")).isDisplayed();
            boolean hasDescription = product.findElement(By.cssSelector(".caption p")).isDisplayed();
            boolean hasPrice = product.findElements(By.cssSelector(".price")).size() > 0;

            if (!hasImage || !hasTitle || !hasDescription || !hasPrice) {
                return false;
            }
        }
        return true;
    }
}
