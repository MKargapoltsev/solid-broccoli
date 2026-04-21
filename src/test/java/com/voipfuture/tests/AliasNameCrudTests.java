package com.voipfuture.tests;

import com.voipfuture.pageobjects.AddEditAliasPage;
import com.voipfuture.pageobjects.AliasNameListPage;
import com.voipfuture.pageobjects.DeleteAliasPopup;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AliasNameCrudTests {

    AliasNameListPage aliasList = new AliasNameListPage();
    AddEditAliasPage addEditAlias = new AddEditAliasPage();
    DeleteAliasPopup deletePopup = new DeleteAliasPopup();

    private final By createdIpRow_selector =
            By.xpath("//table//tbody/tr[td[2][normalize-space()='192.168.10.11']]");

    private final By updatedIpRow_selector =
            By.xpath("//table//tbody/tr[td[2][normalize-space()='192.168.10.21'] and td[3][normalize-space()='TestAliasName_Updated_01'] and td[4][normalize-space()='IP-Softswitch']]");

    private final By createdMacRow_selector =
            By.xpath("//table//tbody/tr[td[3][normalize-space()='MacAlias_Valid_01']]");

    private final By deletePopup_selector =
            By.cssSelector("div.modal, div.popup");

    @BeforeMethod
    public void preConditions() {
        aliasList.openIpTab();
    }

    @Test(groups = {"positive", "functional", "smoke", "ip"}, priority = 1,
            description = "1.1 Add first valid IP alias")
    public void shouldCreateFirstValidIpAlias() {
        SoftAssert softAssert = new SoftAssert();

        aliasList.clickAdd();
        addEditAlias.saveAlias("192.168.10.11", "TestAliasName_Initial_01", "Unknown Device");

        // Pauses.microsleep(500);
        // Can be enabled temporarily for debugging or additional UI stabilization if needed.

        aliasList.waitPresenceByLocator(createdIpRow_selector, 5);

        softAssert.assertTrue(aliasList.isRowPresentByAddress("192.168.10.11"),
                "IP alias row should be present after save");
        softAssert.assertEquals(aliasList.getAliasNameByAddress("192.168.10.11"),
                "TestAliasName_Initial_01", "Alias Name should match saved value");
        softAssert.assertEquals(aliasList.getAliasTypeByAddress("192.168.10.11"),
                "Unknown Device", "Alias Type should match saved value");
        softAssert.assertAll();
    }

    @Test(groups = {"positive", "functional", "smoke", "ip"}, priority = 2,
            description = "1.2 Edit the created IP alias")
    public void shouldEditCreatedIpAlias() {
        SoftAssert softAssert = new SoftAssert();

        aliasList.clickEditForAddress("192.168.10.11");

        softAssert.assertEquals(addEditAlias.getAddressValue(),
                "192.168.10.11", "Address should be prefilled");
        softAssert.assertEquals(addEditAlias.getAliasNameValue(),
                "TestAliasName_Initial_01", "Alias Name should be prefilled");
        softAssert.assertEquals(addEditAlias.getSelectedAliasType(),
                "Unknown Device", "Alias Type should be prefilled");

        addEditAlias.saveAlias("192.168.10.21", "TestAliasName_Updated_01", "IP-Softswitch");

        // Pauses.microsleep(500);
        // Can be enabled temporarily for debugging or additional UI stabilization if needed.

        aliasList.waitPresenceByLocator(updatedIpRow_selector, 5);

        softAssert.assertTrue(aliasList.isRowPresentByAddress("192.168.10.21"),
                "Updated row should be present");
        softAssert.assertEquals(aliasList.getAliasNameByAddress("192.168.10.21"),
                "TestAliasName_Updated_01", "Alias Name should be updated");
        softAssert.assertEquals(aliasList.getAliasTypeByAddress("192.168.10.21"),
                "IP-Softswitch", "Alias Type should be updated");
        softAssert.assertAll();
    }

    @Test(groups = {"positive", "functional", "smoke", "ip"}, priority = 3,
            description = "1.4 Delete a single existing record")
    public void shouldDeleteSingleExistingIpRecord() {
        SoftAssert softAssert = new SoftAssert();

        aliasList.clickDeleteForAddress("192.168.10.21");

        deletePopup.waitPresenceByLocator(deletePopup_selector, 5);
        softAssert.assertTrue(deletePopup.isPopupDisplayed(),
                "Delete popup should appear after clicking Delete");

        deletePopup.clickDelete();

        deletePopup.waitElementVanish(deletePopup_selector, 5);
        aliasList.waitElementVanish(updatedIpRow_selector, 5);

        softAssert.assertFalse(aliasList.isRowPresentByAddress("192.168.10.21"),
                "Deleted row should no longer be present");
        softAssert.assertAll();
    }

    @Test(groups = {"positive", "functional", "smoke", "mac"}, priority = 4,
            description = "5.5 Add valid MAC alias and MAC format normalization check")
    public void shouldCreateValidMacAliasAndCheckNormalization() {
        SoftAssert softAssert = new SoftAssert();

        aliasList.openMacTab();
        aliasList.clickAdd();
        addEditAlias.saveAlias("aa:bb:cc:11:22:33", "MacAlias_Valid_01", "Unknown Device");

        // Pauses.microsleep(500);
        // Can be enabled temporarily for debugging or additional UI stabilization if needed.

        aliasList.waitPresenceByLocator(createdMacRow_selector, 5);

        softAssert.assertTrue(aliasList.isRowPresentByAliasName("MacAlias_Valid_01"),
                "MAC alias row should be present after save");

        String storedAddress = aliasList.getAddressByAliasName("MacAlias_Valid_01");
        softAssert.assertTrue(
                storedAddress.equals("aa:bb:cc:11:22:33") || storedAddress.equals("AA:BB:CC:11:22:33"),
                "MAC address should be stored either as entered or in normalized uppercase format"
        );
        softAssert.assertAll();
    }
}
