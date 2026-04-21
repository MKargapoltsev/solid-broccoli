package com.voipfuture.pageobjects;

import com.voipfuture.base.BasePage;
import org.openqa.selenium.By;

public class AliasNameListPage extends BasePage {

    By ipTab_selector = By.xpath("//a[normalize-space()='IP']");
    By macTab_selector = By.xpath("//a[normalize-space()='MAC']");
    By searchInput_selector = By.cssSelector("input[placeholder*='Enter search term']");
    By searchButton_selector = By.xpath("//button[normalize-space()='Search']");
    By addButton_selector = By.xpath("//button[normalize-space()='Add']");
    By deleteAllButton_selector = By.xpath("//button[normalize-space()='Delete All']");
    By exportButton_selector = By.xpath("//button[normalize-space()='Export']");
    By aliasTable_selector = By.cssSelector("table");
    By tableRows_selector = By.cssSelector("table tbody tr");
    By browseButton_selector = By.cssSelector("input[type='file']");
    By importButton_selector = By.xpath("//button[normalize-space()='Import']");

    public void openIpTab() {
        click(findElement(ipTab_selector));
    }

    public void openMacTab() {
        click(findElement(macTab_selector));
    }

    public void enterSearchCriteria(String searchText) {
        sendKeys(findElement(searchInput_selector), searchText);
    }

    public void clickSearch() {
        click(findElement(searchButton_selector));
    }

    public void search(String searchText) {
        enterSearchCriteria(searchText);
        clickSearch();
    }

    public void clickAdd() {
        click(findElement(addButton_selector));
    }

    public void clickDeleteAll() {
        click(findElement(deleteAllButton_selector));
    }

    public void clickExport() {
        click(findElement(exportButton_selector));
    }

    public void chooseImportFile(String filePath) {
        sendKeys(findElement(browseButton_selector), filePath);
    }

    public void clickImport() {
        click(findElement(importButton_selector));
    }

    public void importCsv(String filePath) {
        chooseImportFile(filePath);
        clickImport();
    }

    public int getRowCount() {
        return findElements(tableRows_selector).size();
    }

    public boolean isRowPresentByAddress(String address) {
        return findElements(By.xpath("//table//tbody/tr[td[2][normalize-space()='" + address + "']]")).size() > 0;
    }

    public boolean isRowPresentByAliasName(String aliasName) {
        return findElements(By.xpath("//table//tbody/tr[td[3][normalize-space()='" + aliasName + "']]")).size() > 0;
    }

    public void clickEditForAddress(String address) {
        click(findElement(By.xpath("//table//tbody/tr[td[2][normalize-space()='" + address + "']]//a[normalize-space()='Edit']")));
    }

    public void clickDeleteForAddress(String address) {
        click(findElement(By.xpath("//table//tbody/tr[td[2][normalize-space()='" + address + "']]//a[normalize-space()='Delete']")));
    }

    public String getAddressByAliasName(String aliasName) {
        return getText(findElement(By.xpath("//table//tbody/tr[td[3][normalize-space()='" + aliasName + "']]/td[2]")));
    }

    public String getAliasNameByAddress(String address) {
        return getText(findElement(By.xpath("//table//tbody/tr[td[2][normalize-space()='" + address + "']]/td[3]")));
    }

    public String getAliasTypeByAddress(String address) {
        return getText(findElement(By.xpath("//table//tbody/tr[td[2][normalize-space()='" + address + "']]/td[4]")));
    }
}
