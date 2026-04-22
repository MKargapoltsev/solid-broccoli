package com.voipfuture.tests;

import com.voipfuture.pageobjects.AddEditAliasPage;
import com.voipfuture.pageobjects.AliasNameListPage;
import com.voipfuture.testdata.AliasNameDataProvider;
import com.voipfuture.testdata.IpAddressDataProvider;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AliasNameValidationTests {

    AliasNameListPage aliasList = new AliasNameListPage();
    AddEditAliasPage addEditAlias = new AddEditAliasPage();

    @BeforeMethod
    public void preConditions() {
        aliasList.openIpTab();
    }

    @Test(dataProvider = "invalidWrongOctetCountIpData", dataProviderClass = IpAddressDataProvider.class,
            groups = {"validation", "functional", "ip"},
            description = "4.2 Invalid IPv4: wrong number of octets")
    public void shouldRejectInvalidIpv4WithWrongNumberOfOctets(String address, String aliasName, String aliasType) {
        SoftAssert softAssert = new SoftAssert();

        aliasList.clickAdd();
        addEditAlias.saveAlias(address, aliasName, aliasType);

        // No explicit wait is used here intentionally.
        // The assignment does not define a reliable UI anchor for failed validation.
        // This wait should be added once the actual validation feedback is known.

        softAssert.assertFalse(aliasList.isRowPresentByAddress(address),
                "Alias should not be created for invalid IP address: " + address);
        softAssert.assertAll();
    }

    @Test(dataProvider = "allowedSpecialCharacterAliasNames", dataProviderClass = AliasNameDataProvider.class,
            groups = {"positive", "functional", "ip"},
            description = "4.12 Alias Name additional special-character boundary checks")
    public void shouldAcceptAllowedSpecialCharactersInAliasName(String address, String aliasName, String aliasType) {
        SoftAssert softAssert = new SoftAssert();

        aliasList.clickAdd();
        addEditAlias.saveAlias(address, aliasName, aliasType);

        aliasList.waitPresenceByLocator(
                By.xpath("//table//tbody/tr[td[2][normalize-space()='" + address + "']]"),
                5
        );

        softAssert.assertTrue(aliasList.isRowPresentByAddress(address),
                "Alias should be created for allowed special characters");
        softAssert.assertEquals(aliasList.getAliasNameByAddress(address),
                aliasName, "Alias Name should match saved value");
        softAssert.assertAll();
    }
}
