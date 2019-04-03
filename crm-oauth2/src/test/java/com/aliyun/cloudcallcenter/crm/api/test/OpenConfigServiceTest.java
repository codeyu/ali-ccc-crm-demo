package com.aliyun.cloudcallcenter.crm.api.test;

import com.alibaba.fastjson.JSONObject;

import com.aliyun.cloudcallcenter.crm.CrmDemoApplicationTests;
import com.aliyun.cloudcallcenter.crm.aliyun.manager.UserManager;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lingyi on 17/11/22.
 */
public class OpenConfigServiceTest extends CrmDemoApplicationTests {
    /*
    @Autowired
    private UserManager userManager;

    @Test
    public void testRequestLoginInfo() {

        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("requestLoginInfo");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());

        CommonResponse response = null;
        try {
            response = getClient().getCommonResponse(commonRequest);
    System.out.println(JSONObject.toJSONString(response));
} catch (ClientException e) {
        e.printStackTrace();
        }

        }

@Test
    public void testRequestLoginInfo1() {

        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("listContactFlows");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());

        CommonResponse response = null;
        try {
            response = getClient().getCommonResponse(commonRequest);
            System.out.println(JSONObject.toJSONString(response));
            System.out.println("************************");
            //(JSONObject.toJSONString(response.getData()));
            //Assert.assertEquals("NotExist.instance", response.getData());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
    */
}
