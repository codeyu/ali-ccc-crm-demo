package com.aliyun.cloudcallcenter.crm.api.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.aliyun.cloudcallcenter.crm.CrmDemoApplicationTests;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.exceptions.ClientException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @auther
 * @create
 */
public class OpenSkillGroupServiceTest extends CrmDemoApplicationTests {
//    String skillgroupname = UUID.randomUUID().toString().substring(0, 5);
//    Set<String> skillGroupId = new HashSet<String>();
//
//    @Test
//    public void testCreateSkillGroupWithNameAlreadyExist() throws ClientException {
//        CommonRequest commonRequest = new CommonRequest();
//        commonRequest.setDomain("ccc.aliyuncs.com");
//        commonRequest.setVersion("2017-07-05");
//        commonRequest.setAction("CreateSkillGroup");
//        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
//        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
//        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
//        commonRequest.putQueryParameter("Name", skillgroupname + "" + 1);
//        CommonResponse response = null;
//        response = getClient().getCommonResponse(commonRequest);
//        response = getClient().getCommonResponse(commonRequest);
//        String code = parseString(response);
//        Assert.assertEquals("Exists.SkillGroupName", code);
//        System.out.println(JSONObject.toJSONString(response));
//    }
//
//    @Test
//    public void testCreateSkillGroupWithInstanceNotExist() throws ClientException {
//        CommonRequest commonRequest = new CommonRequest();
//        commonRequest.setDomain("ccc.aliyuncs.com");
//        commonRequest.setVersion("2017-07-05");
//        commonRequest.setAction("CreateSkillGroup");
//        commonRequest.putQueryParameter("InstanceId", "de37-b966-48c1-b8d7-dd6574190db4");
//        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
//        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
//        commonRequest.putQueryParameter("Name", skillgroupname);
//        CommonResponse response = null;
//        response = getClient().getCommonResponse(commonRequest);
//        String code = parseString(response);
//        Assert.assertEquals("NotExist.Instance", code);
//        System.out.println(JSONObject.toJSONString(response));
//    }
//
//    @Ignore
//    @Test
//    public void testCreateSkillGroupWithUserPermission() throws ClientException {
//        CommonRequest commonRequest = new CommonRequest();
//        commonRequest.setDomain("ccc.aliyuncs.com");
//        commonRequest.setVersion("2017-07-05");
//        commonRequest.setAction("CreateSkillGroup");
//        commonRequest.putQueryParameter("InstanceId", "de37-b966-48c1-b8d7-dd6574190db4");
//        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
//        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
//        commonRequest.putQueryParameter("Name", skillgroupname + "1");
//        CommonResponse response = null;
//        response = getPermissionClient().getCommonResponse(commonRequest);
//        String code = parseString(response);
//        Assert.assertEquals("NoPermission.User", code);
//        System.out.println(JSONObject.toJSONString(response));
//    }
//
//    @Ignore
//    @Test
//    public void testCreateSkillGroupWithLimitExeced() throws ClientException {
//        for (int i = 0; i < 100; i++) {
//            CommonRequest commonRequest = new CommonRequest();
//            commonRequest.setDomain("ccc.aliyuncs.com");
//            commonRequest.setVersion("2017-07-05");
//            commonRequest.setAction("CreateSkillGroup");
//            commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
//            commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
//            commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
//            commonRequest.putQueryParameter("Name", skillgroupname + "" + i);
//            CommonResponse response = null;
//            response = getClient().getCommonResponse(commonRequest);
//            String code = parseString(response);
//            System.out.println(JSONObject.toJSONString(response));
//        }
//        CommonRequest commonRequest = new CommonRequest();
//        commonRequest.setDomain("ccc.aliyuncs.com");
//        commonRequest.setVersion("2017-07-05");
//        commonRequest.setAction("CreateSkillGroup");
//        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
//        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
//        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
//        commonRequest.putQueryParameter("Name", skillgroupname + "s");
//        CommonResponse response = null;
//        response = getClient().getCommonResponse(commonRequest);
//        String code = parseString(response);
//        Assert.assertEquals("LimitExceeded.SkillGroup", code);
//        System.out.println(JSONObject.toJSONString(response));
//        testdeleteSkillGroups();
//    }
//
//    @Test
//    public void testListSkillGroups() throws ClientException {
//        CommonRequest commonRequest = new CommonRequest();
//        commonRequest.setDomain("ccc.aliyuncs.com");
//        commonRequest.setVersion("2017-07-05");
//        commonRequest.setAction("ListSkillGroups");
//        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
//        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
//        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
//
//        CommonResponse response = null;
//        response = getClient().getCommonResponse(commonRequest);
//        getSkillGroupId(response.getData());
//        System.out.println(JSONObject.toJSONString(response));
//    }
//
//    @Test
//    public void testListSkillGroupsWithInstanceNotExists() throws ClientException {
//        CommonRequest commonRequest = new CommonRequest();
//        commonRequest.setDomain("ccc.aliyuncs.com");
//        commonRequest.setVersion("2017-07-05");
//        commonRequest.setAction("ListSkillGroups");
//        commonRequest.putQueryParameter("InstanceId", "e37-b966-48c1-b8d7-dd6574190db4");
//        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
//        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
//        CommonResponse response = null;
//        response = getClient().getCommonResponse(commonRequest);
//        String code = parseString(response);
//        Assert.assertEquals("NotExist.Instance", code);
//        System.out.println(JSONObject.toJSONString(response));
//    }
//
//    @Ignore
//    @Test
//    public void testListSkillGroupsWithUserPermission() throws ClientException {
//        CommonRequest commonRequest = new CommonRequest();
//        commonRequest.setDomain("ccc.aliyuncs.com");
//        commonRequest.setVersion("2017-07-05");
//        commonRequest.setAction("ListSkillGroups");
//        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
//        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
//        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
//        CommonResponse response = null;
//        response = getPermissionClient().getCommonResponse(commonRequest);
//        String code = parseString(response);
//        Assert.assertEquals("NoPermission.User", code);
//        System.out.println(JSONObject.toJSONString(response));
//    }
//
//    @Test
//    public void testModifySkillGroup() throws ClientException {
//        CommonRequest commonRequest = new CommonRequest();
//        commonRequest.setDomain("ccc.aliyuncs.com");
//        commonRequest.setVersion("2017-07-05");
//        commonRequest.setAction("ModifySkillGroup");
//        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
//        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
//        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
//        commonRequest.putQueryParameter("SkillGroupId", "41e4d4bb-341f-4cbe-9708-d2c43696c251");
//        commonRequest.putQueryParameter("Name", "Default");
//        CommonResponse response = null;
//        response = getClient().getCommonResponse(commonRequest);
//        String code = parseString(response);
//        System.out.println(JSONObject.toJSONString(response));
//    }
//
//    @Test
//    public void testModifySkillGroupWithSkillGroupNameAldreayExists() throws ClientException {
//        CommonRequest commonRequest = new CommonRequest();
//        commonRequest.setDomain("ccc.aliyuncs.com");
//        commonRequest.setVersion("2017-07-05");
//        commonRequest.setAction("ModifySkillGroup");
//        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
//        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
//        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
//        commonRequest.putQueryParameter("SkillGroupId", "41e4d4bb-341f-4cbe-9708-d2c43696c251");
//        commonRequest.putQueryParameter("Name", skillgroupname + "" + 1);
//        CommonResponse response = null;
//        response = getClient().getCommonResponse(commonRequest);
//        String code = parseString(response);
//        System.out.println(JSONObject.toJSONString(response));
//    }
//
//    @Ignore
//    @Test
//    public void testModifySkillGroupWithUserPermission() throws ClientException {
//        CommonRequest commonRequest = new CommonRequest();
//        commonRequest.setDomain("ccc.aliyuncs.com");
//        commonRequest.setVersion("2017-07-05");
//        commonRequest.setAction("ModifySkillGroup");
//        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
//        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
//        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
//        commonRequest.putQueryParameter("SkillGroupId", "41e4d4bb-341f-4cbe-9708-d2c43696c251");
//        commonRequest.putQueryParameter("Name", "Default");
//        CommonResponse response = null;
//        response = getPermissionClient().getCommonResponse(commonRequest);
//        String code = parseString(response);
//        Assert.assertEquals("NoPermission.User", code);
//        System.out.println(JSONObject.toJSONString(response));
//    }
//
//    @Test
//    public void testModifySkillGroupWithInstanceNotExisits() throws ClientException {
//        CommonRequest commonRequest = new CommonRequest();
//        commonRequest.setDomain("ccc.aliyuncs.com");
//        commonRequest.setVersion("2017-07-05");
//        commonRequest.setAction("ModifySkillGroup");
//        commonRequest.putQueryParameter("InstanceId", "dcbd7-b966-48c1-b8d7-dd6574190db4");
//        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
//        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
//        commonRequest.putQueryParameter("SkillGroupId", "41e4d4bb-341f-4cbe-9708-d2c43696c251");
//        commonRequest.putQueryParameter("Name", "Default");
//        CommonResponse response = null;
//        response = getClient().getCommonResponse(commonRequest);
//        String code = parseString(response);
//        Assert.assertEquals("NotExist.Instance", code);
//        System.out.println(JSONObject.toJSONString(response));
//    }
//
//    @Test
//    public void testModifySkillGroupWithSkillGroupNotExisits() throws ClientException {
//        CommonRequest commonRequest = new CommonRequest();
//        commonRequest.setDomain("ccc.aliyuncs.com");
//        commonRequest.setVersion("2017-07-05");
//        commonRequest.setAction("ModifySkillGroup");
//        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
//        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
//        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
//        commonRequest.putQueryParameter("SkillGroupId", "41e");
//        commonRequest.putQueryParameter("Name", "Default");
//        CommonResponse response = null;
//        response = getClient().getCommonResponse(commonRequest);
//        String code = parseString(response);
//        Assert.assertEquals("NotExist.SkillGroup", code);
//        System.out.println(JSONObject.toJSONString(response));
//    }
//
//    @Test
//    public void testdeleteSkillGroupWithSkillGroupNotExists() throws ClientException {
//        CommonRequest commonRequest = new CommonRequest();
//        commonRequest.setDomain("ccc.aliyuncs.com");
//        commonRequest.setVersion("2017-07-05");
//        commonRequest.setAction("DeleteSkillGroup");
//        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
//        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
//        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
//        commonRequest.putQueryParameter("SkillGroupId", "dddddd");
//        CommonResponse response = null;
//        response = getClient().getCommonResponse(commonRequest);
//        String code = parseString(response);
//        //            Assert.assertEquals("NotExist.SkillGroup", code);
//        System.out.println(JSONObject.toJSONString(response));
//    }
//
//    @Ignore
//    @Test
//    public void testdeleteSkillGroupWithUserPermission() throws ClientException {
//        CommonRequest commonRequest = new CommonRequest();
//        commonRequest.setDomain("ccc.aliyuncs.com");
//        commonRequest.setVersion("2017-07-05");
//        commonRequest.setAction("DeleteSkillGroup");
//        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
//        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
//        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
//        commonRequest.putQueryParameter("SkillGroupId", "dddddd");
//        CommonResponse response = null;
//        response = getPermissionClient().getCommonResponse(commonRequest);
//        String code = parseString(response);
//        Assert.assertEquals("NotExist.User", code);
//        System.out.println(JSONObject.toJSONString(response));
//    }
//
//    @Test
//    public void testCreateAndDeleteSkillGroup() throws ClientException {
//
//        CommonRequest commonRequest = new CommonRequest();
//        commonRequest.setDomain("ccc.aliyuncs.com");
//        commonRequest.setVersion("2017-07-05");
//        commonRequest.setAction("CreateSkillGroup");
//        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
//        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
//        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
//        commonRequest.putQueryParameter("Name", skillgroupname);
//        CommonResponse response1 = null;
//        response1 = getClient().getCommonResponse(commonRequest);
//        JSONObject jsonObject = JSONObject.parseObject(response1.getData().toString());
//        String skillGroupId = (String)jsonObject.get("SkillGroupId");
//        commonRequest = new CommonRequest();
//        commonRequest.setDomain("ccc.aliyuncs.com");
//        commonRequest.setVersion("2017-07-05");
//        commonRequest.setAction("DeleteSkillGroup");
//        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
//        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
//        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
//        commonRequest.putQueryParameter("SkillGroupId", skillGroupId);
//        CommonResponse response = null;
//        response = getClient().getCommonResponse(commonRequest);
//        String code = parseString(response);
//        System.out.println(JSONObject.toJSONString(response));
//
//    }
//
//    //批量删除 测试创建的技能组
//    public void testdeleteSkillGroups() throws ClientException {
//        Iterator<String> it = skillGroupId.iterator();
//        while (it.hasNext()) {
//            String str = it.next();
//            CommonRequest commonRequest = new CommonRequest();
//            commonRequest.setDomain("ccc.aliyuncs.com");
//            commonRequest.setVersion("2017-07-05");
//            commonRequest.setAction("DeleteSkillGroup");
//            commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
//            commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
//            commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
//            commonRequest.putQueryParameter("SkillGroupId", str);
//            CommonResponse response = null;
//            response = getClient().getCommonResponse(commonRequest);
//            String code = parseString(response);
//            System.out.println(JSONObject.toJSONString(response));
//        }
//    }
//
//    public String parseString(CommonResponse response) {
//        String data = response.getData();
//        return JSONObject.parseObject(data).getString("Code");
//    }
//
//    public void getSkillGroupId(String skillgroup) {
//        JSONObject jsonObject = JSONObject.parseObject(skillgroup);
//        JSONObject skillGroups = (JSONObject)jsonObject.get("SkillGroups");
//        JSONArray skillGroup = JSONObject.parseArray(skillGroups.getString("SkillGroup"));
//        System.out.println(skillGroup);
//        Object[] objects = skillGroup.toArray();
//        for (int i = 0; i < objects.length; i++) {
//            JSONObject json = (JSONObject)objects[i];
//            if (!"Default".equals((String)json.get("SkillGroupName"))) {
//                skillGroupId.add((String)json.get("SkillGroupId"));
//            }
//        }
//    }
}
