package com.aliyun.cloudcallcenter.crm.api.test;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.cloudcallcenter.crm.CrmDemoApplicationTests;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.BearerTokenCredentials;
import com.aliyuncs.ccc.model.v20170705.ListContactFlowsRequest;
import com.aliyuncs.ccc.model.v20170705.ListContactFlowsResponse;
import com.aliyuncs.ccc.model.v20170705.ListPhoneNumbersRequest;
import com.aliyuncs.ccc.model.v20170705.ListPhoneNumbersResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lingyi on 18/4/8.
 */
public class OpenPhoneNumberServiceTest extends CrmDemoApplicationTests {
    @Test
    public void testListContactFlowWithInstanceNotExist() throws ClientException {

        DefaultProfile profileBear = DefaultProfile.getProfile("cn-hangzhou");
        profileBear.addEndpoint("ccc","cn-hangzhou","ccc", "ccc.cn-hangzhou.aliyuncs.com");

        ListPhoneNumbersRequest request = new ListPhoneNumbersRequest();
        request.setInstanceId("18b4f6bd-12f7-4ab6-91d1-bf43b854f35c");
        request.setOutboundOnly(false);

        ListPhoneNumbersResponse response = null;
        try {
            response = getClient().getAcsResponse(request);
            System.out.println(JSONObject.toJSONString(response));
            Assert.assertEquals("OK", response.getCode());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
