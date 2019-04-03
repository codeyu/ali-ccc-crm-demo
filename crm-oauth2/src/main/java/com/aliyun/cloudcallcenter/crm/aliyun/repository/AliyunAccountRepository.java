package com.aliyun.cloudcallcenter.crm.aliyun.repository;

import com.aliyun.cloudcallcenter.crm.aliyun.model.AliyunAccount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author edward
 * @date 2017/11/12
 */
public interface AliyunAccountRepository extends JpaRepository<AliyunAccount, Long> {
    /**
     * 通过用户ID获取绑定的阿里云账户信息
     *
     * @param userId
     * @return
     */
    AliyunAccount findByUserId(Long userId);
}
