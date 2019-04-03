package com.aliyun.cloudcallcenter.crm;

import java.util.HashMap;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.BearerTokenCredentials;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CrmDemoApplicationTests {

    @Value("${accessToken}")
    private String accessToken;

    @Test
    public void contextLoads() {
    }

    public DefaultAcsClient getClient() throws ClientException {
        DefaultProfile profileBear = DefaultProfile.getProfile("cn-hangzhou");
        profileBear.addEndpoint("ccc","cn-hangzhou","ccc", "ccc.cn-hangzhou.aliyuncs.com");

        BearerTokenCredentials bearerTokenCredentials =
                new BearerTokenCredentials(accessToken);
        DefaultAcsClient accessTokenClient = new DefaultAcsClient(profileBear, bearerTokenCredentials);
        accessTokenClient.setAutoRetry(false);

        return accessTokenClient;
    }

//    public DefaultAcsClient getPermissionClient() {
//        HashMap<String, String> map = new HashMap<String, String>();
//        map.put("CCC", "ccc.aliyuncs.com");
//
//        IClientProfile stsProfile = DefaultProfile.getProfile("cn-hangzhou", map, accessKeyId,
//            accessKeySecret, "");
//        DefaultAcsClient stsClient = new DefaultAcsClient(stsProfile);
//
//        stsClient.setAutoRetry(false);
//
//        return stsClient;
//    }
//
//    public String getSecurityToken() {
//        return this.securityToken;
//    }
//
//    public String getAccessKeyId() {
//        return this.accessKeyId;
//    }
//
//    public String getAccessKeySecret() {
//        return this.accessKeySecret;
//    }
}
