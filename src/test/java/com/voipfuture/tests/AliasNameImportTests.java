package com.voipfuture.tests;

import com.voipfuture.pageobjects.AliasNameListPage;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AliasNameImportTests {

    AliasNameListPage aliasList = new AliasNameListPage();

    private final By importedRow_selector =
            By.xpath("//table//tbody/tr[td[2][normalize-space()='10.10.10.1']]");

    @BeforeMethod
    public void preConditions() {
        aliasList.openIpTab();
    }

    @Test(groups = {"positive", "functional", "ip"}, priority = 1,
            description = "2.5 Import the CSV file obtained from export and verify replacement of existing data")
    public void shouldImportCsvAndReplaceExistingData() {
        SoftAssert softAssert = new SoftAssert();

        aliasList.importCsv("src/test/resources/testdata/import_aliases_from_export.csv");
        aliasList.waitPresenceByLocator(importedRow_selector, 5);

        softAssert.assertEquals(aliasList.getRowCount(), 9,
                "The table should contain the 9 imported records");
        softAssert.assertFalse(aliasList.isRowPresentByAddress("172.16.50.50"),
                "The pre-import manual record should no longer be present after replacement");
        softAssert.assertTrue(aliasList.isRowPresentByAddress("10.10.10.1"),
                "Imported record 10.10.10.1 should be present");
        softAssert.assertTrue(aliasList.isRowPresentByAddress("10.10.10.9"),
                "Imported record 10.10.10.9 should be present");
        softAssert.assertAll();
    }
}
