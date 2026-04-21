package com.voipfuture.tests;

import com.voipfuture.pageobjects.AliasNameListPage;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AliasNameSearchTests {

    AliasNameListPage aliasList = new AliasNameListPage();

    private final By searchedAliasRow_selector =
            By.xpath("//table//tbody/tr[td[3][normalize-space()='TestAliasName_5']]");

    @BeforeMethod
    public void preConditions() {
        aliasList.openIpTab();
    }

    @Test(groups = {"positive", "functional", "ip"}, priority = 1,
            description = "2.7 Search by existing Alias Name, case-insensitive")
    public void shouldSearchByExistingAliasNameCaseInsensitive() {
        SoftAssert softAssert = new SoftAssert();

        aliasList.search("TESTALIASNAME_5");
        aliasList.waitPresenceByLocator(searchedAliasRow_selector, 5);

        softAssert.assertTrue(aliasList.isRowPresentByAliasName("TestAliasName_5"),
                "Search should find Alias Name regardless of case");
        softAssert.assertEquals(aliasList.getAddressByAliasName("TestAliasName_5"),
                "10.10.10.5", "The expected record should be displayed");
        softAssert.assertAll();
    }
}
