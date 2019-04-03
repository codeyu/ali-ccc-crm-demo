package com.aliyun.cloudcallcenter.crm.api.test;

import com.alibaba.fastjson.JSONObject;

import com.aliyun.cloudcallcenter.crm.CrmDemoApplicationTests;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lingyi on 17/11/22.
 */
public class OpenPermissionServiceTest extends CrmDemoApplicationTests {
//    @Test
//    public void testListRoleWithInstanceNotExist() {
//
//        CommonRequest commonRequest = new CommonRequest();
//        commonRequest.setDomain("ccc.aliyuncs.com");
//        commonRequest.setVersion("2017-07-05");
//        commonRequest.setAction("listroles");
//        commonRequest.putQueryParameter("InstanceId", "instance1");
//        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
//        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
//
//        CommonResponse response = null;
//        try {
//            response = getClient().getCommonResponse(commonRequest);
//            JSONObject resultData = JSONObject.parseObject(response.getData());
//            System.out.println("验证错误码");
//            System.out.println(JSONObject.toJSONString(response));
//            Assert.assertEquals("NotExist.Instance", resultData.getString("Code"));
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//    }
}
