package com.aliyun.cloudcallcenter.crm.aliyun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.cloudcallcenter.crm.aliyun.CallCenterServiceException;
import com.aliyun.cloudcallcenter.crm.aliyun.HttpRequester;
import com.aliyun.cloudcallcenter.crm.aliyun.TokenPackage;
import com.aliyun.cloudcallcenter.crm.aliyun.manager.UserManager;
import com.aliyun.cloudcallcenter.crm.model.User;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.BearerTokenCredentials;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.apache.commons.httpclient.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author edward
 * @date 2017/11/15
 */
@RestController
public class CccController {
    /**
     * 生产环境的Region是cn-shanghai
     */
    private static final String REGION = "cn-shanghai";
    private static final String PRODUCT = "CCC";
    private static final String ENDPOINT = "CCC";
    /**
     * 生产环境的Region是ccc.cn-shanghai.aliyuncs.com
     */
    private static final String DOMAIN = "ccc.cn-shanghai.aliyuncs.com";
    private static final String VERSION = "2017-07-05";

    /**
     * TODO 需要替换成自己的ClientId
     */
   @Value("${aliyun.client.id}")
    private String aliyunClientId;

   /*
     * TODO 需要替换成自己的ClientSecret
   */
    @Value("${aliyun.client.secret}")
    private String aliyunClientSecret;

    @Value("${aliyun.token.endpoint}")
    private String aliyunTokenEndpoint;

    private static final Logger logger = LoggerFactory.getLogger(AliyunController.class);

    @Autowired
    private UserManager userManager;

    public static void main(String[] args) {
        String jsonString = "{\"HttpStatusCode\":200,\"RequestId\":\"4322BD50-ACF5-4527-877B-0FA34CBBF331\","
                + "\"Success\":true,\"Code\":\"OK\",\"SkillLevels\":{\"SkillLevel\":[{\"SkillLevelId\":\"15831\","
                + "\"Skill\":{\"OutboundPhoneNumbers\":{\"PhoneNumber\":[]},"
                + "\"InstanceId\":\"dcbdbe37-b966-48c1-b8d7-dd6574190db4\",\"SkillGroupName\":\"e69041\","
                + "\"SkillGroupId\":\"1cf05ff4-1ce6-4d71-919e-d56a48bb2059\"},\"Level\":5}]}}";

        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        JSONObject newObject = new JSONObject();

        copyObject(newObject, jsonObject);

        String result = JSONObject.toJSONString(newObject);

        System.out.println(result);
    }

    private static void copyArray(JSONArray destination, JSONArray source) {
        Iterator<Object> iterator = source.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object instanceof JSONObject) {
                JSONObject newObject = new JSONObject();
                destination.add(newObject);
                copyObject(newObject, (JSONObject) object);
            } else if (object instanceof JSONArray) {
                JSONArray newArray = new JSONArray();
                destination.add(newArray);
                copyArray(newArray, (JSONArray) object);
            } else {
                destination.add(object);
            }
        }
    }

    private static void copyObject(JSONObject destination, JSONObject source) {
        for (Entry<String, Object> entry : source.entrySet()) {
            String key = entry.getKey().trim();
            if (key.length() > 1) {
                key = key.substring(0, 1).toLowerCase() + key.substring(1);
            } else if (key.length() == 1) {
                key = key.toUpperCase();
            } else {
                continue;
            }

            Object object = entry.getValue();

            if (object instanceof JSONObject) {
                JSONObject tempObject = (JSONObject) object;
                if (tempObject.entrySet().size() == 1) {
                    Object theOne = tempObject.entrySet().iterator().next().getValue();
                    if (theOne instanceof JSONArray) {
                        JSONArray newArray = new JSONArray();
                        destination.put(key, newArray);
                        copyArray(newArray, (JSONArray) theOne);
                        continue;
                    }
                }
                JSONObject newObject = new JSONObject();
                destination.put(key, newObject);
                copyObject(newObject, (JSONObject) object);
            } else if (object instanceof JSONArray) {
                JSONArray newArray = new JSONArray();
                destination.put(key, newArray);
                copyArray(newArray, (JSONArray) object);
            } else {
                destination.put(key, object);
            }
        }
    }

    @RequestMapping(value = "/aliyun/ccc/api", method = RequestMethod.POST)
    public String call(Principal principal, HttpServletRequest httpServletRequest) throws ClientException {
        User user = userManager.findByUserName(principal.getName());

        if (!user.isAliyunBound()) {
            throw new CallCenterServiceException(null, "NotAuthenticated",
                    "Not authenticated to access Aliyun Cloud Call Center resources.");
        } else {
            logger.info("userName=[{}], accessToken=[{}], ",
                    user.getUserName(), user.getTokePackage().getAccessToken());

            try {
                if (user.getTokePackage() != null && user.getTokePackage().isExpire()) {
                    String accessToken = refreshAccessToken(user.getTokePackage().getRefreshToken(),
                            httpServletRequest.getRequestURI());
                    user = userManager.updateAccessTokenByUserName(principal.getName(), accessToken, true);
                }
            } catch (Exception e) {
                logger.error("Failed to refresh token, request=" + JSON.toJSONString(httpServletRequest), e);
            }
            String action = httpServletRequest.getParameter("action");
            String request = httpServletRequest.getParameter("request");

            return invokeApiByBearerToken(user.getTokePackage().getAccessToken(), action, request);
        }
    }

    private String refreshAccessToken(String refreshToken, String requestUrl) throws Exception {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new NameValuePair("client_id", aliyunClientId));
        params.add(new NameValuePair("grant_type", "refresh_token"));
        params.add(new NameValuePair("client_secret", aliyunClientSecret));
        params.add(new NameValuePair("refresh_token", refreshToken));
        params.add(new NameValuePair("redirect_uri", requestUrl));

        NameValuePair[] pairs = new NameValuePair[params.size()];

        return HttpRequester.post(this.aliyunTokenEndpoint, params.toArray(pairs));
    }

    /**
     * 用以方便调试
     *
     * @param principal
     * @param httpServletRequest
     * @param action
     * @param request
     * @return
     */
    @RequestMapping(value = "/aliyun/ccc/api/debug", method = RequestMethod.GET)
    public String get(Principal principal, HttpServletRequest httpServletRequest,
                      @RequestParam String action, @RequestParam String request) throws ClientException {
        User user = userManager.findByUserName(principal.getName());

        if (!user.isAliyunBound()) {
            throw new CallCenterServiceException(null, "NotAuthenticated",
                    "Not authenticated to access Aliyun Cloud Call Center resources.");
        } else {
            logger.info("userName=[{}], accessToken=[{}], ",
                    user.getUserName(), user.getTokePackage().getAccessToken());
            return invokeApiByBearerToken(user.getTokePackage().getAccessToken(), action, request);
        }
    }

    private String invokeApiByBearerToken(String accessToken, String action, String request) throws ClientException {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(PRODUCT, DOMAIN);

        DefaultProfile profileBear = DefaultProfile.getProfile(REGION);
        profileBear.addEndpoint(ENDPOINT, REGION, PRODUCT, DOMAIN);

        BearerTokenCredentials bearerTokenCredentials =
                new BearerTokenCredentials(accessToken);
        DefaultAcsClient accessTokenClient = new DefaultAcsClient(profileBear, bearerTokenCredentials);
        accessTokenClient.setAutoRetry(false);

        /**
         * 使用CommonAPI调用POP API时，和使用传统产品SDK相比，请求和返回参数的格式都有所不同，因此需要进行一定的格式转换。
         */
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain(DOMAIN);
        commonRequest.setVersion(VERSION);
        commonRequest.setAction(action);
        JSONObject jsonObject = JSONObject.parseObject(request);

        for (Entry<String, Object> entry : jsonObject.entrySet()) {
            String key = entry.getKey().trim();
            if (key.length() > 1) {
                key = key.substring(0, 1).toUpperCase() + key.substring(1);
            } else if (key.length() == 1) {
                key = key.toUpperCase();
            } else {
                continue;
            }
            commonRequest.putQueryParameter(key, entry.getValue().toString());
        }

        commonRequest.putQueryParameter("accessToken", accessToken);
        CommonResponse response = null;
        try {
            response = accessTokenClient.getCommonResponse(commonRequest);
            System.out.println(JSONObject.toJSONString(response.getData()));
        } catch (ClientException e) {
            logger.error("Failed to invoke open API, request=" + JSON.toJSONString(commonRequest), e);
            throw new CallCenterServiceException(e.getRequestId(), e.getErrCode(), e.getMessage());
        }

        JSONObject jsonResult = JSONObject.parseObject(response.getData());
        JSONObject newJsonResult = new JSONObject();
        copyObject(newJsonResult, jsonResult);
        return JSONObject.toJSONString(newJsonResult);
    }

    @RequestMapping(value = "/aliyun/sts/ccc/api/", method = RequestMethod.POST)
    public String callBySts(Principal principal, HttpServletRequest httpServletRequest) throws ClientException {
        User user = userManager.findByUserName(principal.getName());

        if (!user.isAliyunBound()) {
            throw new CallCenterServiceException(null, "NotAuthenticated",
                    "Not authenticated to access Aliyun Cloud Call Center resources.");
        } else {
            logger.info("userName=[{}], tokePackage=[{}], ",
                    user.getUserName(), JSONObject.toJSONString(user.getTokePackage()));

            try {
                if (user.getTokePackage() != null && user.getTokePackage().isExpire()) {
                    String accessToken = refreshAccessToken(user.getTokePackage().getRefreshToken(),
                            httpServletRequest.getRequestURI());
                    user = userManager.updateAccessTokenByUserName(principal.getName(), accessToken, true);
                }
            } catch (Exception e) {
                logger.error("Failed to refresh token, request=" + JSON.toJSONString(httpServletRequest), e);
            }
            String action = httpServletRequest.getParameter("action");
            String request = httpServletRequest.getParameter("request");

            return invokeApi(user.getTokePackage().getCredentials(), action, request);
        }
    }

    private String invokeApi(TokenPackage.Credentials credentials, String action, String request) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(PRODUCT, DOMAIN);

        IClientProfile stsProfile = DefaultProfile.getProfile(REGION, map, credentials.getAccessKeyId(),
                credentials.getAccessKeySecret(), credentials.getSecurityToken());
        DefaultAcsClient stsClient = new DefaultAcsClient(stsProfile);

        stsClient.setAutoRetry(false);

        /**
         * 使用CommonAPI调用POP API时，和使用传统产品SDK相比，请求和返回参数的格式都有所不同，因此需要进行一定的格式转换。
         */
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setDomain(DOMAIN);
        commonRequest.setVersion(VERSION);
        commonRequest.setAction(action);

        JSONObject jsonObject = JSONObject.parseObject(request);

        for (Entry<String, Object> entry : jsonObject.entrySet()) {
            String key = entry.getKey().trim();
            if (key.length() > 1) {
                key = key.substring(0, 1).toUpperCase() + key.substring(1);
            } else if (key.length() == 1) {
                key = key.toUpperCase();
            } else {
                continue;
            }
            commonRequest.putQueryParameter(key, entry.getValue().toString());
        }

        commonRequest.putQueryParameter("SecurityToken", credentials.getSecurityToken());
        commonRequest.putQueryParameter("AccessKeyId", credentials.getAccessKeyId());

        logger.info("SecurityToken: " + credentials.getSecurityToken());
        logger.info("AccessKeyId: " + credentials.getAccessKeyId());

        CommonResponse response = null;
        try {
            response = stsClient.getCommonResponse(commonRequest);
            System.out.println(response.getData());
        } catch (ClientException e) {
            logger.error("Failed to invoke open API, request=" + JSON.toJSONString(commonRequest), e);
            throw new CallCenterServiceException(e.getRequestId(), e.getErrCode(), e.getMessage());
        }

        JSONObject jsonResult = JSONObject.parseObject(response.getData());
        JSONObject newJsonResult = new JSONObject();
        copyObject(newJsonResult, jsonResult);
        return JSONObject.toJSONString(newJsonResult);
    }
}
