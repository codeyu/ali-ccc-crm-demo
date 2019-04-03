package com.aliyun.cloudcallcenter.crm.api.test;

import com.alibaba.fastjson.JSONObject;

import com.aliyun.cloudcallcenter.crm.CrmDemoApplicationTests;
import com.aliyun.cloudcallcenter.crm.aliyun.manager.UserManager;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.BearerTokenCredentials;
import com.aliyuncs.ccc.model.v20170705.ListContactFlowsRequest;
import com.aliyuncs.ccc.model.v20170705.ListContactFlowsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lingyi on 17/11/22.
 */
public class OpenContactFlowServiceTest extends CrmDemoApplicationTests {
    @Test
    public void testListContactFlowWithInstanceNotExist() throws ClientException {

        DefaultProfile profileBear = DefaultProfile.getProfile("cn-hangzhou");
        profileBear.addEndpoint("ccc","cn-hangzhou","ccc", "ccc.cn-hangzhou.aliyuncs.com");

        ListContactFlowsRequest request = new ListContactFlowsRequest();
        request.setInstanceId("18b4f6bd-12f7-4ab6-91d1-bf43b854f35c");

        ListContactFlowsResponse response = null;
        try {
            response = getClient().getAcsResponse(request);
            System.out.println(JSONObject.toJSONString(response));
            Assert.assertEquals("OK", response.getCode());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
