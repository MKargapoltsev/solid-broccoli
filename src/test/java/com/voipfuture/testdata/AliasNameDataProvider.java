package com.voipfuture.testdata;

import org.testng.annotations.DataProvider;

public class AliasNameDataProvider {

    @DataProvider(name = "allowedSpecialCharacterAliasNames")
    public static Object[][] allowedSpecialCharacterAliasNames() {
        return new Object[][]{
                {"192.168.100.11", "Alias-Test_Name.01", "Unknown Device"},
                {"192.168.100.12", "Alias@Device.com", "Unknown Device"},
                {"192.168.100.13", "Alias(Name)01", "Unknown Device"},
                {"192.168.100.14", "Alias!@^&*_+{}[]", "Unknown Device"},
                {"192.168.100.15", "Alias<>|~';:", "Unknown Device"}
        };
    }
}
