package com.aliyun.cloudcallcenter.crm.aliyun.controller;

import com.aliyun.cloudcallcenter.crm.aliyun.CallCenterServiceException;
import com.aliyun.cloudcallcenter.crm.aliyun.HttpRequester;
import com.aliyun.cloudcallcenter.crm.aliyun.RandomString;
import com.aliyun.cloudcallcenter.crm.aliyun.manager.UserManager;
import com.aliyun.cloudcallcenter.crm.model.User;
import com.nimbusds.oauth2.sdk.AuthorizationCode;
import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.openid.connect.sdk.AuthenticationErrorResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationResponseParser;
import com.nimbusds.openid.connect.sdk.AuthenticationSuccessResponse;
import org.apache.commons.httpclient.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author edward
 * @date 2017/11/12
 */
@Controller
public class AliyunController {

    public static final long SESSION_ATTRIBUTE_STATES_TTL_SECONDS = 300;
    public static final String SESSION_ATTRIBUTE_STATES = "states";
    public static final String CALLBACK_REQUEST_PARAMETER_STATE = "state";

    private static final Logger logger = LoggerFactory.getLogger(AliyunController.class);

    /**
     * 用来生成nonce字符串，长度必须是45位
     */
    private final RandomString randomString = new RandomString(45);

    @Value("${aliyun.authority.endpoint}")
    private String aliyunAuthorityEndpoint;

    @Value("${aliyun.token.endpoint}")
    private String aliyunTokenEndpoint;

    @Value("${aliyun.revoke.endpoint}")
    private String  aliyunRevokeEndpoint;
    /**
     * TODO 需要替换成自己的ClientId
     */
   @Value("${aliyun.client.id}")
    private String aliyunClientId;


//     TODO 需要替换成自己的ClientSecret

     @Value("${aliyun.client.secret}")
     private String aliyunClientSecret;

    /**
     * TODO 需要替换成自己的Callback地址，注意要和下面authCallback函数指定的地址一致
     */
    @Value("${aliyun.client.callback}")
    private String callbackUrl;

    @Autowired
    private UserManager userManager;

    private AuthenticationResponse parse(URI redirectURI, Map<String, String> params) throws ParseException {
        return params.containsKey("error") ? AuthenticationErrorResponse.parse(redirectURI,
                params) : AuthenticationSuccessResponse.parse(redirectURI, params);
    }

    private void validateAuthenticationResponse(AuthenticationSuccessResponse successResponse) throws Exception {
        if (successResponse.getIDToken() != null || successResponse.getAccessToken() != null ||
                successResponse.getAuthorizationCode() == null) {
            throw new Exception(
                    "Failed to validate data received from Authorization service: unexpected set of artifacts received");
        }
    }

    @RequestMapping("/aliyun/auth")
    public RedirectView auth(ModelMap model, Principal principal, HttpServletRequest httpServletRequest)
            throws UnsupportedEncodingException {
        User user = userManager.findByUserName(principal.getName());
        if (!user.isAliyunBound()) {
            String redirectUrl = getAliyunAuthorityUrl(httpServletRequest);
            logger.debug("redirect to: " + redirectUrl);
            return new RedirectView(redirectUrl);
        }
        return new RedirectView("/home");
    }

    @RequestMapping("/aliyun/refresh")
    public RedirectView refresh(ModelMap model, Principal principal, HttpServletRequest httpServletRequest)
            throws Exception {
        User user = userManager.findByUserName(principal.getName());
        if (user.getTokePackage() == null) {
            String redirectUrl = getAliyunAuthorityUrl(httpServletRequest);
            logger.debug("redirect to: " + redirectUrl);
            return new RedirectView(redirectUrl);
        }
        String refreshToken = user.getTokePackage().getRefreshToken();
        System.out.println("***refresh token " + refreshToken);

        String accessToken = refreshAccessToken(user.getTokePackage().getRefreshToken(),
                httpServletRequest.getRequestURI());

        System.out.println("***access token " + accessToken);
        userManager.updateAccessTokenByUserName(principal.getName(), accessToken, true);
        return new RedirectView("/home");
    }

    @RequestMapping("/aliyun/revoke")
    public Object revoke(ModelMap model, Principal principal, HttpServletRequest httpServletRequest)
            throws Exception {
        User user = userManager.findByUserName(principal.getName());
//      如果没有阿里云授权,则该API无法调用,则重定向至阿里云授权页面
        if (user.isAliyunBound()) {     // 如果有绑定, 需要清除
            // revoke access token
            String response = revokeAccessToken(user.getTokePackage().getAccessToken(), httpServletRequest.getRequestURI());
            // clear local access token info
            userManager.revokeAccessTokenByUserName(principal.getName(), false);
            model.addAttribute("userName", user.getUserName());
            model.addAttribute("isAliyunBound", user.isAliyunBound());
            return new ModelAndView("/home", model);

            /*
            String redirectUrl = getAliyunAuthorityUrl(httpServletRequest);
            logger.debug("redirect to: " + redirectUrl);
            return new ModelAndView("redirect:" + redirectUrl, model);

            如果有阿里云授权,则取消授权
            第一次使用授权码code获取AccessToken的时候就携带了refreshToken
            所以只需要把token传过去就可以了
            解绑阿里云账号和User的关系
            把没有isAliyunBond=fase的user序列化
            */
        } else {
            return new RedirectView("/home");
        }

    }

    @RequestMapping("/aliyun/auth/callback")
    public RedirectView authCallback(ModelMap model, Principal principal, HttpServletRequest httpServletRequest)
            throws Exception {
        String requestUrl = httpServletRequest.getRequestURL().toString();
        String queryString = httpServletRequest.getQueryString();
        String currentUrl = requestUrl + (queryString != null ? "?" + queryString : "");

        Map<String, String> params = httpServletRequest.getParameterMap().entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                         e -> e.getValue()[0]));

        AuthenticationResponse authenticationResponse = AuthenticationResponseParser.parse(new URI(currentUrl), params);

        if (authenticationResponse instanceof AuthenticationSuccessResponse) {
            AuthenticationSuccessResponse successResponse = (AuthenticationSuccessResponse) authenticationResponse;
            validateAuthenticationResponse(successResponse);
            State state = loadStateFromSession(httpServletRequest.getSession(),
                    params.get(CALLBACK_REQUEST_PARAMETER_STATE));
            if (state == null) {
                throw new CallCenterServiceException(null, "InvalidState",
                        "Failed to validate state data received from authentication service");
            }
            String accessToken = getAccessToken(successResponse.getAuthorizationCode(), requestUrl,
                    state.getNonce());

            logger.info("access token is {}", accessToken);
            System.out.println("access token is " + accessToken);
            userManager.updateAccessTokenByUserName(principal.getName(), accessToken, false);
        } else {
            AuthenticationErrorResponse errorResponse = (AuthenticationErrorResponse) authenticationResponse;
            throw new CallCenterServiceException(null, errorResponse.getErrorObject().getCode(),
                    ("Request for authentication code failed: " +
                            errorResponse.getErrorObject().getDescription()));
        }
        return new RedirectView("/home");
    }

    private String getAliyunAuthorityUrl(HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
        String state = UUID.randomUUID().toString();
        String nonce = randomString.nextString();
        storeStateInSession(httpServletRequest.getSession(), state, nonce);
        return aliyunAuthorityEndpoint
                + "?response_type=code&response_mode=form_post"
                + "&redirect_uri=" + URLEncoder.encode(callbackUrl, "UTF-8")
                + "&client_id=" + aliyunClientId
                + "&state=" + state
                + "&codeChallenge=" + nonce
                + "&access_type=offline";
    }

    private String getAccessToken(AuthorizationCode authorizationCode, String requestUrl, String nonce)
            throws Exception {

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new NameValuePair("client_id", aliyunClientId));
        params.add(new NameValuePair("grant_type", "authorization_code"));
        params.add(new NameValuePair("client_secret", aliyunClientSecret));
        params.add(new NameValuePair("code", authorizationCode.getValue()));
        params.add(new NameValuePair("redirect_uri", requestUrl));
        params.add(new NameValuePair("code_verifier", nonce));
        NameValuePair[] pairs = new NameValuePair[params.size()];
        return HttpRequester.post(this.aliyunTokenEndpoint, params.toArray(pairs));
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
    private String revokeAccessToken(String AccessToken, String requestUrl) throws Exception {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new NameValuePair("client_id", aliyunClientId));
        params.add(new NameValuePair("client_secret", aliyunClientSecret));
        params.add(new NameValuePair("token", AccessToken));
        params.add(new NameValuePair("redirect_uri", requestUrl));

        NameValuePair[] pairs = new NameValuePair[params.size()];
        return HttpRequester.post(this.aliyunRevokeEndpoint, params.toArray(pairs));
    }
    private State loadStateFromSession(HttpSession session, String state) throws Exception {
        Map<String, State> states = getStatesFromSessionAttributes(session, false);
        if (states != null) {
            eliminateExpiredStates(states);
            State stateValue = states.get(state);
            if (stateValue != null) {
                return stateValue;
            }
        }
        return null;
    }

    private void storeStateInSession(HttpSession session, String state, String nonce) {
        Map<String, State> statesObject = getStatesFromSessionAttributes(session, true);
        statesObject.put(state,
                new State(nonce, new Date()));
    }

    private Map<String, State> getStatesFromSessionAttributes(HttpSession session, boolean createIfAbsence) {
        Object statesObject = session.getAttribute(SESSION_ATTRIBUTE_STATES);
        if (statesObject == null && createIfAbsence) {
            statesObject = new HashMap<String, State>(1);
            session.setAttribute(SESSION_ATTRIBUTE_STATES, statesObject);
        }
        return (Map<String, State>) statesObject;
    }

    private void eliminateExpiredStates(Map<String, State> states) {
        Iterator<Entry<String, State>> iterator = states.entrySet().iterator();
        Date now = new Date();
        while (iterator.hasNext()) {
            Map.Entry<String, State> entry = iterator.next();
            long timeElapsedSeconds = TimeUnit.MILLISECONDS.
                    toSeconds(now.getTime() - entry.getValue().getExpirationDate().getTime());
            if (timeElapsedSeconds > SESSION_ATTRIBUTE_STATES_TTL_SECONDS) {
                iterator.remove();
            }
        }
    }

    private class State {
        private String nonce;
        private Date expirationDate;

        public State(String nonce, Date expirationDate) {
            this.nonce = nonce;
            this.expirationDate = expirationDate;
        }

        public String getNonce() {
            return nonce;
        }

        public Date getExpirationDate() {
            return expirationDate;
        }
    }







}
