package com.voipfuture.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage extends DriverInitialization {

    protected WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    protected List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    protected void click(WebElement element) {
        element.click();
    }

    protected void sendKeys(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    protected String getText(WebElement element) {
        return element.getText();
    }

    protected void select(WebElement element, String visibleText) {
        new Select(element).selectByVisibleText(visibleText);
    }

    protected boolean isElementPresent(By locator) {
        return !findElements(locator).isEmpty();
    }

    public WebElement waitPresenceByLocator(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitElementVanish(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
