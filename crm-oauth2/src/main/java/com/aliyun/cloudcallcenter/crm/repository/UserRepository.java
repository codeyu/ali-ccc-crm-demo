package com.aliyun.cloudcallcenter.crm.repository;

import com.aliyun.cloudcallcenter.crm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author edward
 * @date 2017/11/12
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 通过用户名查找用户信息
     *
     * @param userName
     * @return
     */
    User findByUserName(String userName);
}
