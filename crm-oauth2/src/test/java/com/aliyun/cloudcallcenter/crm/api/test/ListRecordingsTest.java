package com.aliyun.cloudcallcenter.crm.api.test;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.ccc.model.v20170705.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wb-zt354936 on 2017/12/27.
 */
public class ListRecordingsTest {
    public static final int INT = 95;
    public static final int INT1 = 100;
    Logger logger = LoggerFactory.getLogger(ListRecordingsTest.class);

    String instanceId = "e0fb2d0a-a8c3-4c2f-a8c2-07cd7219c0b4";
    String key = "STS.GiD4mRxoVKT7yH7jAtjT2VMCs";
    String secret = "6mkUuNAgJc9cQnQcVaioaRCVphCewAFCJc1CtY6JvLGa";
    String token = "CAISiwJ1q6Ft5B2yfSjIpqrxf9fmlbB3/Jbce26GjkEhZtseuYjokTz3IX5EdXFhCOAev/" +
            "8zmmxU6PwfhYMSGsUdHBOdNZAptiWyeoFAJ9ivgde8yJBZopDHcDHhA3yW9cvWZPqDP7G5U/yxalfCuzZ" +
            "uyL/hD1uLVECkNpv74vwOLK5FPG+CYCFBGc1dKyZ7tcYeLgGxD/u2NQPwiWeieigcswFn22h04vb9kJTBsE" +
            "OO1AKqm7JE+NWqcsb2LesUZcolCoftg7wnK/uegXUAtSInrvkm0PZ2gRbCptyHGF5X6gmLP9fP/8dnRA0CPP" +
            "ZqSvYV/aikxKYk5LCPyd6qmgwgLOhTVynPkVMnG1ZhkIkagAE1Lxc5LWGeyaE+8RVvH/DTHkUugFWx4ZCyWON" +
            "BscwbwE15gA2a8pgKDp7UGzK71DCtcv0WG4Gyk0tkYG6BdKc79WMQiK18TD6og29aabGOG" +
            "IQntgP3WGkSJmcQtfS1XlPaTMh9gymFOQyXIo/1tqQzjjs94LD6Sq4laKCme35/ng==";

    @Test
    public void testListRecording() throws Exception {
        ListRecordingsRequest request = new ListRecordingsRequest();
        request.setSecurityToken(token);
        request.setInstanceId("e0fb2d0a-a8c3-4c2f-a8c2-07cd7219c0b4");
        request.setPageNumber(1);
        request.setPageSize(8);
        request.setStopTime(new Date().getTime());
        request.setStartTime(1l);
        // request.setCriteria("80324715");
        ListRecordingsResponse response = null;
        response = getClient().getAcsResponse(request);
        assert response != null;
        assert response.getSuccess();
        assert response.getRecordings().getList().size() > 0;
        assert response.getRecordings().getList().get(0).getInstanceId().equals("e0fb2d0a-a8c3-4c2f-a8c2-07cd7219c0b4");
    }

//    @Test
//    public void testListRecordingsByContactId() throws ClientException {
//        ListRecordingsByContactIdRequest request = new ListRecordingsByContactIdRequest();
//        request.setInstanceId("e0fb2d0a-a8c3-4c2f-a8c2-07cd7219c0b4");
//        request.setContactId("2003900775");
//        ListRecordingsByContactIdResponse response = null;
//        response = getClientForDemo().getAcsResponse(request);
//        assert response.getRecordings().size()>0;
//
//    }

    @Test
    public void testListCallDetails___ContactId() throws Exception {
        ListCallDetailRecordsRequest request = new ListCallDetailRecordsRequest();
        ListCallDetailRecordsResponse response = null;
        request.setInstanceId("e0fb2d0a-a8c3-4c2f-a8c2-07cd7219c0b4");
        request.setPageSize(5);
        request.setPageNumber(1);
        // request.setSecurityToken(token);
        request.setStartTime(1l);
        //request.setCriteria("80324715");
        Long time = new Date().getTime();
        request.setStopTime(time);
        request.setCriteria("13381268154");
       response = getClientForListRecordingsByContactId().getAcsResponse(request);
        assert response != null;
        assert response.getSuccess();
        assert response.getCallDetailRecords().getList().size() >= 0;
        assert response.getCallDetailRecords().getList().get(0) != null;
    }

    public DefaultAcsClient getClientForListRecordingsByContactId() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("CCC", "ccc.cn-hangzhou.aliyuncs.com");
        IClientProfile stsProfile = DefaultProfile.getProfile("cn-hangzhou", map, "STS.GQTW3NyZ56wtSRNXH16dYK9hb",
                "BP2PTUBYMYrWRjE8XnWqCJukTzi4QZWeusnhq6pAUXms", "CAISiwJ1q6Ft5B2yfSjIppLhHIn6lIUUgbWfUXT/vEhkOut1pPzDgDz3IX5EdXFhCOAev/8zmmxU6PwfhYMSGsUdHBOdNZAptnf9S71UJ9ivgde8yJBZopDHcDHhA3yW9cvWZPqDP7G5U/yxalfCuzZuyL/hD1uLVECkNpv74vwOLK5FPG+CYCFBGc1dKyZ7tcYeLgGxD/u2NQPwiWeieigcswFn22h04vb9kJTBsEOO1AKqm7JE+NWqcsb2LesUZcolCoftg7wnK/uegXUAtSInrvkm0PZ2gRbCptyHGF5X6gmLP9fP/8dnRA0CPPZqSvYV/aikxKYk5LCPyd6qmgwgLOhTVynPkVMnG1ZhkIkagAEwAquQzUDWxR77Z0E1i2RXqYmEDX7+ZZ6jL8lGd6K6czgdyaUIFnwaUqASOQ7G5JadW3iJbvj2oRHxxPESzRl2O4uJ4dcYn90aqvmfeoJWoVld2kWRBng3WPutM8d/HsNo17EKki9edCstqDOujv7NG90mbME7X3NIyihkn53swQ==");
        DefaultAcsClient stsClient = new DefaultAcsClient(stsProfile);
        stsClient.setAutoRetry(false);
        return stsClient;
    }

    //请求的参数
    String fileName = "151437377372000080322609.wav";
    private static final String charset = "utf-8";
    private static final String url = "https://pre-ccc.aliyun.com/open/DownloadRecording.do";
    String json = "{\"instanceId\":\"" + instanceId + "\",\"fileName\":\"" + fileName + "\"}";

    @Test
    public void testDownLoading() throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        Header header = new BasicHeader("Content-Type", "application/x-www-form-urlencoded");
        post.setHeader(header);
        List<BasicNameValuePair> formparams = new ArrayList<>();
        formparams.add(new BasicNameValuePair("accessKeySecret", secret));
        formparams.add(new BasicNameValuePair("accessKeyId", key));
        formparams.add(new BasicNameValuePair("securityToken", token));
        formparams.add(new BasicNameValuePair("request", json));
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formparams, charset);
        post.setEntity(urlEncodedFormEntity);
        CloseableHttpResponse response = httpclient.execute(post);
        HttpEntity entity = response.getEntity();
        InputStream content = entity.getContent();
        FileOutputStream out = new FileOutputStream(new File("D:\\ut", "test"));
        int i = -1;
        while ((i = content.read()) != -1) {
            out.write(i);
        }
        content.close();
        out.close();
    }


    public DefaultAcsClient getClient() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("CCC", "ccc.cn-hangzhou.aliyuncs.com");
        IClientProfile stsProfile = DefaultProfile.getProfile("cn-hangzhou", map, key,
                secret, token);
        DefaultAcsClient stsClient = new DefaultAcsClient(stsProfile);
        stsClient.setAutoRetry(false);
        return stsClient;
    }

    @Test
    public void testListCallDetails() throws Exception {
        ListCallDetailRecordsRequest request = new ListCallDetailRecordsRequest();
        ListCallDetailRecordsResponse response = null;
        request.setInstanceId("43036aaf-ad5b-4880-b0c8-2b4864a379f8");
        request.setPageSize(5);
        request.setPageNumber(1);
        // request.setSecurityToken(token);
        request.setStartTime(1l);
        //request.setCriteria("80324715");
        Long time = new Date().getTime();
        request.setStopTime(time);
//       response = getClientForDemo().getAcsResponse(request);
        assert response != null;
        assert response.getSuccess();
        assert response.getCallDetailRecords().getList().size() >= 0;
        assert response.getCallDetailRecords().getList().get(0) != null;
    }

    public DefaultAcsClient getClientForDemo() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("CCC", "ccc.cn-shanghai.aliyuncs.com");
        IClientProfile stsProfile = DefaultProfile.getProfile("cn-shanghai", map, "STS.HwJc7gqgWDdekXjkTE2sGapW5",
                "71SDg4YwKN1pKs5PRws4a99s2QFgkkTCP9WLtLaNdkRf", "CAISigJ1q6Ft5B2yfSjIqbT/KI3TnLh286aOaX7bj" +
                        "1QQPvxrjrX81zz2IXBNe3lhBukZsvkxmWhR6fYTlqAiEcccGhWcMZUoSBqWOv7lMeT7oMWQweEuhf/MQ" +
                        "BqbaXPS2MvVfJ+KLrf0ceusbFbpjzJ6xaCAGxypQ12iN+/i6/clFIZ1ODO1dj1bHtxbCxJ/ocsBTxvrO" +
                        "O2qLwThjximbj5hsREugmh0u+btm53DtUWH1QKrkrJK/dqgf8rmTbE1bMggDI7sg70uK/Wbjn8N0XUQqvc" +
                        "q1p482DLfs8uGBF9Y/xiKDvHZ6NUHLnUjNvFlS/8b9aegyKMl4rWMx9XtpRVEPO9cCDQKlwd7XW5MchqAASyxrIxXt" +
                        "rVlK8S+w0h93vmpR8pYq/KFS33C+HMAdfLtTGeRNY3bjGi0vAR5y4oWXmy/TgzCHuemAj0vHeVkjDJsFf+Zwg1HsDMIxoplbYoj" +
                        "ERCxKdp4FDlzeEfMYgzfvOU2F8kkbvCSlpDO7lCmxI1NB3sEM5TEFqHWRiRvhpFA");
        DefaultAcsClient stsClient = new DefaultAcsClient(stsProfile);
        stsClient.setAutoRetry(false);
        return stsClient;
    }

}








