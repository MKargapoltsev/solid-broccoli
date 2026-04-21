package com.voipfuture.pageobjects;

import com.voipfuture.base.BasePage;
import org.openqa.selenium.By;

public class AddEditAliasPage extends BasePage {

    By addressInput_selector = By.cssSelector("input[name='address']");
    By aliasNameInput_selector = By.cssSelector("input[name='aliasName']");
    By aliasTypeDropdown_selector = By.cssSelector("select[name='aliasType']");
    By saveButton_selector = By.xpath("//button[normalize-space()='Save']");
    By backButton_selector = By.xpath("//button[normalize-space()='Back']");

    public void enterAddress(String address) {
        sendKeys(findElement(addressInput_selector), address);
    }

    public void enterAliasName(String aliasName) {
        sendKeys(findElement(aliasNameInput_selector), aliasName);
    }

    public void selectAliasType(String aliasType) {
        select(findElement(aliasTypeDropdown_selector), aliasType);
    }

    public void clickSave() {
        click(findElement(saveButton_selector));
    }

    public void clickBack() {
        click(findElement(backButton_selector));
    }

    public void fillAliasForm(String address, String aliasName, String aliasType) {
        enterAddress(address);
        enterAliasName(aliasName);
        selectAliasType(aliasType);
    }

    public void saveAlias(String address, String aliasName, String aliasType) {
        fillAliasForm(address, aliasName, aliasType);
        clickSave();
    }

    public String getAddressValue() {
        return findElement(addressInput_selector).getAttribute("value");
    }

    public String getAliasNameValue() {
        return findElement(aliasNameInput_selector).getAttribute("value");
    }

    public String getSelectedAliasType() {
        return findElement(aliasTypeDropdown_selector).getAttribute("value");
    }
}
