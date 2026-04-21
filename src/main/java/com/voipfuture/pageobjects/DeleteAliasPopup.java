package com.voipfuture.pageobjects;

import com.voipfuture.base.BasePage;
import org.openqa.selenium.By;

public class DeleteAliasPopup extends BasePage {

    By deletePopup_selector = By.cssSelector("div.modal");
    By popupDeleteButton_selector = By.xpath("//button[normalize-space()='Delete']");
    By popupCancelButton_selector = By.xpath("//button[normalize-space()='Cancel']");
    By popupText_selector = By.xpath("//*[contains(text(),'Delete Alias')]");

    public boolean isPopupDisplayed() {
        return findElements(deletePopup_selector).size() > 0;
    }

    public String getPopupText() {
        return getText(findElement(popupText_selector));
    }

    public void clickDelete() {
        click(findElement(popupDeleteButton_selector));
    }

    public void clickCancel() {
        click(findElement(popupCancelButton_selector));
    }
}
