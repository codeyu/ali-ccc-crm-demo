package com.aliyun.cloudcallcenter.crm.aliyun.manager.impl;

import com.aliyun.cloudcallcenter.crm.aliyun.TokenPackage;
import com.aliyun.cloudcallcenter.crm.aliyun.manager.UserManager;
import com.aliyun.cloudcallcenter.crm.aliyun.model.AliyunAccount;
import com.aliyun.cloudcallcenter.crm.aliyun.repository.AliyunAccountRepository;
import com.aliyun.cloudcallcenter.crm.model.User;
import com.aliyun.cloudcallcenter.crm.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

/**
 * @author edward
 * @date 2017/11/12
 */
@Service
public class UserManagerImpl implements UserManager {

    private static final Logger logger = LoggerFactory.getLogger(UserManagerImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AliyunAccountRepository aliyunAccountRepository;

    @Override
    public User findByUserName(String userName) {
        User user = userRepository.findByUserName(userName);

        if (user != null) {
            AliyunAccount account = aliyunAccountRepository.findByUserId(user.getId());
            TokenPackage tokenPackage = null;
            if (account != null) {
                try {
                    tokenPackage = TokenPackage.parse(account.getAccessCode());
                } catch (ParseException e) {
                    logger.error("Failed to parse access token: " + account.getAccessCode(), e);
                } catch (IOException ioe) {
                    logger.error("Failed to parse access token: " + account.getAccessCode(), ioe);
                }
                if (account.getGmtModified() != null) {
                    tokenPackage.setCreateDate(new Date(account.getGmtModified().getTime()));
                }
            }
            user.setTokePackage(tokenPackage);
            user.setAliyunBound(tokenPackage != null && !tokenPackage.isExpire());
        }
        return user;
    }

    @Override
    public User updateAccessTokenByUserName(String userName, String accessToken, boolean isRefreshed) {
        User user = userRepository.findByUserName(userName);
        if (user != null) {
            TokenPackage tokenPackage = null;
            try {
                tokenPackage = TokenPackage.parse(accessToken);
            } catch (ParseException e) {
                logger.error("Failed to parse access token: " + accessToken, e);
            } catch (IOException ioe) {
                logger.error("Failed to parse access token: " + accessToken, ioe);
            }
            if (tokenPackage != null) {
                AliyunAccount account = aliyunAccountRepository.findByUserId(user.getId());
                Timestamp now = new Timestamp(new Date().getTime());
                if (account == null) {
                    account = new AliyunAccount();
                    account.setUserId(user.getId());
                    account.setGmtCreate(now);
                }
                if (isRefreshed) {
                    TokenPackage refreshed = TokenPackage.unpack(accessToken);
                    TokenPackage original = TokenPackage.unpack(account.getAccessCode());
                    account.setAccessCode(TokenPackage.mergeAndPack(refreshed, original));
                } else {
                    account.setAccessCode(accessToken);
                }
                account.setGmtModified(now);
                aliyunAccountRepository.save(account);
                user.setAliyunBound(account.getId() != null);
            }
        }
        return user;
    }

    @Override
    public void revokeAccessTokenByUserName(String userName, boolean tokenPackageIsExist) {
        User user = userRepository.findByUserName(userName);
        if (user != null) {
            user.setAliyunBound(tokenPackageIsExist);
            user.setTokePackage(null);
            AliyunAccount account = aliyunAccountRepository.findByUserId(user.getId());
            aliyunAccountRepository.delete(account);
            userRepository.save(user);
        }
    }


}
