package com.aliyun.cloudcallcenter.crm.api.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

public class OpenUserServicetest extends CrmDemoApplicationTests {
/*
    public static final String url = "jdbc:mysql://rm-tatcloudcallcenter.mysql.rdstest.tbsite.net:3306/cloudcallcenter";
    public static final String name = "com.mysql.jdbc.Driver";
    public static final String user = "cccdbuser";
    public static final String password = "cccdbuser";
    public Connection conn = null;
    public PreparedStatement pst = null;
    String userName = UUID.randomUUID().toString().substring(0, 5);
    Set<String> userId = new HashSet<>();

    @Ignore
    @Test
    public void testCreateUserWithUserPermission() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("CreateUser");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("LoginName", userName);
        commonRequest.putQueryParameter("DisplayName", userName);
        commonRequest.putQueryParameter("Email", "wasd@wasd.com");
        commonRequest.putQueryParameter("RoleId.1", "2b385fe4-c5a6-4138-9fa9-86e94f997e0a");
        CommonResponse response = null;
        response = getPermissionClient().getCommonResponse(commonRequest);
        String code = this.parseString(response);
        Assert.assertEquals("NoPermission.User", code);
        System.out.println(JSONObject.toJSONString(response));
    }

    @Ignore
    @Test
    public void testCreateUserWithLimitExceed() throws SQLException, ClassNotFoundException {
        //执行插入User
        try {
            Class.forName(name);//指定连接类型
            conn = DriverManager.getConnection(url, user, password);//获取连接
            for (int i = 0; i < 101; i++) {
                pst = conn.prepareStatement(
                    "INSERT INTO user (instance,ram_id,user_id) VALUES ('dcbdbe37-b966-48c1-b8d7-dd6574190db4','"
                        + (int)((Math.random() * 9 + 1) * 10000000) + "','" + UUID.randomUUID() + "') ");
                pst.execute();
            }
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("CreateUser");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("LoginName", "1234567489");
        commonRequest.putQueryParameter("DisplayName", "1234567489");
        commonRequest.putQueryParameter("Email", "wasd@wasd.com");
        commonRequest.putQueryParameter("RoleId.1", "2b385fe4-c5a6-4138-9fa9-86e94f997e0a");
        CommonResponse response = null;
        try {
            response = getClient().getCommonResponse(commonRequest);
            System.out.println("99999999" + JSONObject.toJSONString(response));
            String code = this.parseString(response);
            Assert.assertEquals("LimitExceeded.CccUser", code);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateUserWith() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("CreateUser");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("LoginName", "123456789");
        commonRequest.putQueryParameter("DisplayName", "123456789");
        commonRequest.putQueryParameter("Email", "wasd@wasd.com");
        commonRequest.putQueryParameter("RoleId.1", "2b385fe4-c5a6-4138-9fa9-86e94f997e0a");
        CommonResponse response = null;
        response = getClient().getCommonResponse(commonRequest);
        String code = this.parseString(response);
        System.out.println(JSONObject.toJSONString(response));
    }

    @Test
    public void testCreateUserWithUserNameAlreadyExists() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("CreateUser");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("LoginName", "123456789");
        commonRequest.putQueryParameter("DisplayName", "123456789");
        commonRequest.putQueryParameter("Email", "wasd@wasd.com");
        commonRequest.putQueryParameter("RoleId.1", "2b385fe4-c5a6-4138-9fa9-86e94f997e0a");
        CommonResponse response = null;

        response = getClient().getCommonResponse(commonRequest);
        response = getClient().getCommonResponse(commonRequest);
        String code = this.parseString(response);
        Assert.assertEquals("Exists.UserName", code);
        System.out.println(JSONObject.toJSONString(response));

    }

    @Ignore
    @Test
    public void testCreateUserWithImsService() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("CreateUser");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("LoginName", userName);
        commonRequest.putQueryParameter("DisplayName", userName);
        commonRequest.putQueryParameter("Email", "wasd@wasd.com");
        commonRequest.putQueryParameter("RoleId.1", "2b385fe4-c5a6-4138-9fa9-86e94f997e0a");
        CommonResponse response = null;
        response = getClient().getCommonResponse(commonRequest);
        String code = this.parseString(response);
        Assert.assertEquals("ImsService", code);
        System.out.println(JSONObject.toJSONString(response));
    }

    @Test
    public void testCreateUserWithInstanceNotExists() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("CreateUser");
        commonRequest.putQueryParameter("InstanceId", "be37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("LoginName", userName + "s");
        commonRequest.putQueryParameter("DisplayName", userName + "s");
        commonRequest.putQueryParameter("Email", "wasd@wasd.com");
        commonRequest.putQueryParameter("RoleId.1", "2b385fe4-c5a6-4138-9fa9-86e94f997e0a");
        CommonResponse response = null;
        response = getClient().getCommonResponse(commonRequest);
        String code = this.parseString(response);
        Assert.assertEquals("NotExist.Instance", code);
        System.out.println(JSONObject.toJSONString(response));
    }

    @Test
    public void testListUser() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("ListUsers");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("PageNumber", 1 + "");
        commonRequest.putQueryParameter("PageSize", 1000 + "");

        CommonResponse response = null;
        response = getClient().getCommonResponse(commonRequest);
        getUserId(response.getData());
        System.out.println(JSONObject.toJSONString(response));
    }

    @Ignore
    @Test
    public void testListUserWithUserPermission() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("ListUsers");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("PageNumber", 1 + "");
        commonRequest.putQueryParameter("PageSize", 1000 + "");

        CommonResponse response = null;
        response = getPermissionClient().getCommonResponse(commonRequest);
        String code = this.parseString(response);
        Assert.assertEquals("NoPermission.User", code);
        System.out.println(JSONObject.toJSONString(response));
    }

    @Test
    public void testListUserWithInstanceNotExists() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("ListUsers");
        commonRequest.putQueryParameter("InstanceId", "dbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("PageNumber", 1 + "");
        commonRequest.putQueryParameter("PageSize", 1000 + "");

        CommonResponse response = null;
        response = getClient().getCommonResponse(commonRequest);
        Assert.assertEquals("NotExist.Instance", this.parseString(response));
        System.out.println(JSONObject.toJSONString(response));
    }

    @Test
    public void testGetUser() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("GetUser");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("UserId", "f4d594dd-5411-4fbd-8183-94ff4a849090");

        CommonResponse response = null;
        response = getClient().getCommonResponse(commonRequest);
        System.out.println(JSONObject.toJSONString(response));
    }

    @Test
    public void testGetUserWithUserNotExists() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("GetUser");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("UserId", "dc2c4a-6c12-4e74-bde5-093e3dbcf49b");

        CommonResponse response = null;
        response = getClient().getCommonResponse(commonRequest);
        String code = this.parseString(response);
        Assert.assertEquals("NotExist.User", code);
        System.out.println(JSONObject.toJSONString(response));

    }

    @Ignore
    @Test
    public void testGetUserWithInstanceNorExists() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("GetUser");
        commonRequest.putQueryParameter("InstanceId", "dbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("UserId", "dca42c4a-6c12-4e74-bde5-093e3dbcf49b");

        CommonResponse response = null;
        response = getClient().getCommonResponse(commonRequest);
        String code = this.parseString(response);
        Assert.assertEquals("NotExist.Instance", code);
        System.out.println(JSONObject.toJSONString(response));
    }

    @Test
    public void testModifyUser() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("ModifyUser");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("UserId", "dca42c4a-6c12-4e74-bde5-093e3dbcf49b");
        commonRequest.putQueryParameter("DisplayName", "dca42c4a-6c12-4e74-bde5-093e3dbcf49b");
        commonRequest.putQueryParameter("Phone", "03116362675");
        commonRequest.putQueryParameter("Email", "wasd@wasd.com");
        commonRequest.putQueryParameter("RoleId.1", "2b385fe4-c5a6-4138-9fa9-86e94f997e0a");

        CommonResponse response = null;
        response = getClient().getCommonResponse(commonRequest);
        System.out.println(JSONObject.toJSONString(response));
    }

    @Ignore
    @Test
    public void testModifyUserWithUserPermission() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("ModifyUser");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("UserId", "dca42c4a-6c12-4e74-bde5-093e3dbcf49b");
        commonRequest.putQueryParameter("DisplayName", "dca42c4a-6c12-4e74-bde5-093e3dbcf49b");
        commonRequest.putQueryParameter("Phone", "03116362675");
        commonRequest.putQueryParameter("Email", "wasd@wasd.com");
        commonRequest.putQueryParameter("RoleId.1", "2b385fe4-c5a6-4138-9fa9-86e94f997e0a");

        CommonResponse response = null;
        response = getPermissionClient().getCommonResponse(commonRequest);
        System.out.println(JSONObject.toJSONString(response));
    }

    @Ignore
    @Test
    public void testModifyUserWithPrimaryUserNotModify() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("ModifyUser");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("UserId", "f4d594dd-5411-4fbd-8183-94ff4a849090");
        commonRequest.putQueryParameter("DisplayName", userName);
        commonRequest.putQueryParameter("Phone", "03116362675");
        commonRequest.putQueryParameter("Email", "wasd@wasd.com");
        commonRequest.putQueryParameter("RoleId.1", "2b385fe4-c5a6-4138-9fa9-86e94f997e0a");

        CommonResponse response = null;
        response = getClient().getCommonResponse(commonRequest);
        System.out.println(JSONObject.toJSONString(response));
        String code = this.parseString(response);
        Assert.assertEquals("NotModified.PrimaryUser", code);
    }

    @Test
    public void testModifyUserWithImsService() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("ModifyUser");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("UserId", "dca42c4a-6c12-4e74-bde5-093e3dbcf49b");
        commonRequest.putQueryParameter("DisplayName", "f4d594dd-5411-4fbd-8183-94ff4a849090");
        commonRequest.putQueryParameter("Phone", "03116362675");
        commonRequest.putQueryParameter("Email", "wasd@wasd.com");
        commonRequest.putQueryParameter("RoleId.1", "2b385fe4-c5a6-4138-9fa9-86e94f997e0a");

        CommonResponse response = null;
        response = getClient().getCommonResponse(commonRequest);
        String code = this.parseString(response);
        Assert.assertEquals("ImsService", code);
        System.out.println(JSONObject.toJSONString(response));
    }

    @Test
    public void testModifyUserWithSkillGroupNotExists() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("ModifyUser");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("UserId", "dca42c4a-6c12-4e74-bde5-093e3dbcf49b");
        commonRequest.putQueryParameter("DisplayName", "dca42c4a-6c12-4e74-bde5-093e3dbcf49b");
        commonRequest.putQueryParameter("Phone", "03116362675");
        commonRequest.putQueryParameter("Email", "wasd@wasd.com");
        commonRequest.putQueryParameter("RoleId.1", "2b385fe4-c5a6-4138-9fa9-86e94f997e0a");
        commonRequest.putQueryParameter("SkillGroupId.1", "2b385fe4-c5a6-4138-9fa9-86e94f997e0a");

        CommonResponse response = null;
        response = getClient().getCommonResponse(commonRequest);
        String code = this.parseString(response);
        Assert.assertEquals("NotExist.SkillGroup", code);
        System.out.println(JSONObject.toJSONString(response));
    }

    @Test
    public void testModifyUserWithRoleNotExist() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("ModifyUser");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("UserId", "dca42c4a-6c12-4e74-bde5-093e3dbcf49b");
        commonRequest.putQueryParameter("DisplayName", userName);
        commonRequest.putQueryParameter("Phone", "03116362675");
        commonRequest.putQueryParameter("Email", "wasd@wasd.com");
        commonRequest.putQueryParameter("RoleId.1", "2b385fe4-c5a6-38-9fa9-86e94f997e0a");

        CommonResponse response = null;
        response = getClient().getCommonResponse(commonRequest);
        String code = this.parseString(response);
        System.out.println(JSONObject.toJSONString(response));
        Assert.assertEquals("NotExist.Role", code);
    }

    @Test
    public void testModifyUserWithInstanceNotExists() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("ModifyUser");
        commonRequest.putQueryParameter("InstanceId", "d37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("UserId", "dca42c4a-6c12-4e74-bde5-093e3dbcf49b");
        commonRequest.putQueryParameter("DisplayName", "dca42c4a-6c12-4e74-bde5-093e3dbcf49b");
        commonRequest.putQueryParameter("Phone", "03116362675");
        commonRequest.putQueryParameter("Email", "wasd@wasd.com");
        commonRequest.putQueryParameter("RoleId.1", "2b385fe4-c5a6-4138-9fa9-86e94f997e0a");

        CommonResponse response = null;
        response = getClient().getCommonResponse(commonRequest);
        System.out.println(JSONObject.toJSONString(response));
    }

    @Test
    public void testAssignUsers() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("AssignUsers");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("UserRamId.1", "2646574203480671861");

        CommonResponse response = null;
        response = getClient().getCommonResponse(commonRequest);
        System.out.println(JSONObject.toJSONString(response));
        String code = parseString(response);
        Assert.assertEquals("OK", code);
    }

    @Test
    public void testAssignUsersWithInstanceNotExist() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("AssignUsers");
        commonRequest.putQueryParameter("InstanceId", "dcbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("UserRamId.1", "2646574203480671861");

        CommonResponse response = null;
        response = getClient().getCommonResponse(commonRequest);
        System.out.println(JSONObject.toJSONString(response));
        String code = parseString(response);
        Assert.assertEquals("NotExist.Instance", code);
    }

    @Ignore
    @Test
    public void testAssignUsersWithUserNotExist() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("AssignUsers");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("UserRamId.1", "264098480671861");

        CommonResponse response = null;
        response = getClient().getCommonResponse(commonRequest);
        System.out.println(JSONObject.toJSONString(response));
        String code = parseString(response);
        Assert.assertEquals("NotExist.User", code);
    }

    @Test
    public void testRemoveUsersWithInstanceNotExists() throws ClientException {

        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("RemoveUsers");
        commonRequest.putQueryParameter("InstanceId", "bd37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("UserId.1", "dca42c4a-6c12-4e74-bde5-093e3dbcf49b");
        CommonResponse response = null;
        response = getClient().getCommonResponse(commonRequest);
        System.out.println(JSONObject.toJSONString(response));
        String code = this.parseString(response);
        Assert.assertEquals("NotExist.Instance", code);

    }

    @Ignore
    @Test
    public void testRemoveUsersWithUserNotExists() throws ClientException {

        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("RemoveUsers");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("UserId.1", "dcbdbe37-b966-4");
        CommonResponse response = null;
        response = getClient().getCommonResponse(commonRequest);
        String code = this.parseString(response);
        System.out.println(JSONObject.toJSONString(response));
        Assert.assertEquals("NotExist.User", code);
    }

    @Ignore
    @Test
    public void testRemoveUsersWithUserPermission() throws ClientException {

        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("RemoveUsers");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("UserId.1", "dca42c4a-6c12-4e74-bde5-093e3dbcf49b");
        CommonResponse response = null;
        response = getPermissionClient().getCommonResponse(commonRequest);
        String code = this.parseString(response);
        System.out.println(JSONObject.toJSONString(response));
        Assert.assertEquals("NoPermission.User", code);

    }

    @Test
    public void testCreateAndDeleteUser() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("CreateUser");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("LoginName", userName);
        commonRequest.putQueryParameter("DisplayName", userName);
        commonRequest.putQueryParameter("Email", "wasd@wasd.com");
        commonRequest.putQueryParameter("RoleId.1", "2b385fe4-c5a6-4138-9fa9-86e94f997e0a");
        CommonResponse response1 = null;
        response1 = getClient().getCommonResponse(commonRequest);
        System.out.println(JSONObject.toJSONString(response1));
        String data = response1.getData();
        JSONObject jsonObject = JSONObject.parseObject(response1.getData().toString());
        String userid = (String)jsonObject.get("UserId");
        commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("RemoveUsers");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("UserId.1", userid);
        CommonResponse response = null;
        response = getClient().getCommonResponse(commonRequest);
        String code = this.parseString(response);
        System.out.println(JSONObject.toJSONString(response));
    }

    @Test
    public void testRemoveUsers() throws ClientException {
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain("ccc.aliyuncs.com");
        commonRequest.setVersion("2017-07-05");
        commonRequest.setAction("ListUsers");
        commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
        commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
        commonRequest.putQueryParameter("PageNumber", 1 + "");
        commonRequest.putQueryParameter("PageSize", 1000 + "");

        CommonResponse response1 = null;
        response1 = getClient().getCommonResponse(commonRequest);
        getUserId(response1.getData());
        System.out.println(JSONObject.toJSONString(response1));
        Iterator<String> it = userId.iterator();
        int i = 0;
        while (it.hasNext()) {
            i++;
            String str = it.next();
            commonRequest = new CommonRequest();
            commonRequest.setDomain("ccc.aliyuncs.com");
            commonRequest.setVersion("2017-07-05");
            commonRequest.setAction("RemoveUsers");
            commonRequest.putQueryParameter("InstanceId", "dcbdbe37-b966-48c1-b8d7-dd6574190db4");
            commonRequest.putQueryParameter("SecurityToken", getSecurityToken());
            commonRequest.putQueryParameter("AccessKeyId", getAccessKeyId());
            commonRequest.putQueryParameter("UserId." + i, str);
            CommonResponse response = null;
            response = getClient().getCommonResponse(commonRequest);
            String code = this.parseString(response);
            System.out.println(JSONObject.toJSONString(response));
        }
    }

    public String parseString(CommonResponse response) {
        String data = response.getData();
        return JSONObject.parseObject(data).getString("Code");
    }

    public void getUserId(String user) {
        JSONObject jsonObject = JSONObject.parseObject(user);
        JSONObject Users = (JSONObject)jsonObject.get("Users");
        JSONObject list = (JSONObject)Users.get("List");
        JSONArray userArray = JSONObject.parseArray(list.getString("User"));
        System.out.println(userArray);
        Object[] objects = userArray.toArray();
        for (int i = 0; i < objects.length; i++) {
            JSONObject json = (JSONObject)objects[i];
            if (!"f4d594dd-5411-4fbd-8183-94ff4a849090".equals((String)json.get("UserId"))
                && !"dca42c4a-6c12-4e74-bde5-093e3dbcf49b".equals((String)json.get("UserId"))) {
                userId.add((String)json.get("UserId"));
            }
        }
    }
*/
}
