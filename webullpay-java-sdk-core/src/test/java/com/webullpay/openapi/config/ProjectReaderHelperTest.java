package com.webullpay.openapi.config;

import com.webullpay.openapi.config.ProjectReaderHelper;
import org.junit.Assert;
import org.junit.Test;

public class ProjectReaderHelperTest {

    @Test
    public void whenGetSDKInfoThenNotEmpty() {
        String info = ProjectReaderHelper.getClientSDKInfo();
        System.out.println("SDK info: " + info);
        Assert.assertTrue(info != null && info.length() > 0);
    }
}
