package com.voipfuture.testdata;

import org.testng.annotations.DataProvider;

public class IpAddressDataProvider {

    @DataProvider(name = "invalidWrongOctetCountIpData")
    public static Object[][] invalidWrongOctetCountIpData() {
        return new Object[][]{
                {"10.10.10", "ValidAliasName_ForAddressTests_01", "Unknown Device"},
                {"10.10.10.10.10", "ValidAliasName_ForAddressTests_01", "Unknown Device"},
                {"192.168.1", "ValidAliasName_ForAddressTests_01", "Unknown Device"},
                {"172.16.5.1.9", "ValidAliasName_ForAddressTests_01", "Unknown Device"},
                {"1.2.3.4.5.6", "ValidAliasName_ForAddressTests_01", "Unknown Device"}
        };
    }
}
