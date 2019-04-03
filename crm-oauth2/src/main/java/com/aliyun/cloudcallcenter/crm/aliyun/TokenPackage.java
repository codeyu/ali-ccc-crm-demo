package com.aliyun.cloudcallcenter.crm.aliyun;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nimbusds.jose.util.Base64;
import com.nimbusds.jwt.ReadOnlyJWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * @author edward
 * @date 2017/11/12
 */
public class TokenPackage implements Serializable {

    private String accessToken;

    private int expiresIn;

    private String tokenType;

    private String refreshToken;

    private String idToken;

    private Map<String, Object> additionalInformation;

    private Date createDate;

    private Credentials credentials;

    private UserInfo userInfo;

    public TokenPackage() {
        createDate = new Date();
    }

    public static TokenPackage parse(String token) throws ParseException, IOException {
        TokenPackage tokenPackage = JSONObject.parseObject(token, TokenPackage.class);
        if (tokenPackage.getIdToken() != null) {
            SignedJWT signedJWT = SignedJWT.parse(tokenPackage.getIdToken());
            ReadOnlyJWTClaimsSet jwtClaimsSet = signedJWT.getJWTClaimsSet();
            UserInfo userInfo = new UserInfo();
            userInfo.setPrincipalName(jwtClaimsSet.getStringClaim("upn"));
            userInfo.setName(jwtClaimsSet.getStringClaim("name"));
            userInfo.setAccountId(jwtClaimsSet.getStringClaim("aid"));
            tokenPackage.setUserInfo(userInfo);
        }

        return tokenPackage;
    }

    public static TokenPackage unpack(String token) {
        return JSONObject.parseObject(token, TokenPackage.class);
    }

    public static String mergeAndPack(TokenPackage destination, TokenPackage source) {
        destination.setIdToken(source.idToken);
        destination.setRefreshToken(source.refreshToken);
        return JSONObject.toJSONString(destination);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpiredDate() {
        return new Date(createDate.getTime() + expiresIn * 1000);
    }

    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public boolean isExpire() {
        return new Date(createDate.getTime() + expiresIn * 1000).before(new Date());
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfo {
        private String accountId;
        private String principalName;
        private String name;

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getPrincipalName() {
            return principalName;
        }

        public void setPrincipalName(String principalName) {
            this.principalName = principalName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Credentials implements Serializable {

        @JsonProperty("AccessKeyId")
        private String accessKeyId;

        @JsonProperty("AccessKeySecret")
        private String accessKeySecret;

        @JsonProperty("Expiration")
        private String expiration;

        @JsonProperty("SecurityToken")
        private String securityToken;

        public String getAccessKeyId() {
            return accessKeyId;
        }

        public void setAccessKeyId(String accessKeyId) {
            this.accessKeyId = accessKeyId;
        }

        public String getAccessKeySecret() {
            return accessKeySecret;
        }

        public void setAccessKeySecret(String accessKeySecret) {
            this.accessKeySecret = accessKeySecret;
        }

        public String getExpiration() {
            return expiration;
        }

        public void setExpiration(String expiration) {
            this.expiration = expiration;
        }

        public String getSecurityToken() {
            return securityToken;
        }

        public void setSecurityToken(String securityToken) {
            this.securityToken = securityToken;
        }
    }
}
