package com.aliyun.cloudcallcenter.crm.repository;

import com.aliyun.cloudcallcenter.crm.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author edward
 * @date 2017/11/12
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     * 通过电话号码查找客户
     *
     * @param phoneNumber
     * @return
     */
    Customer findByPhoneNumber(String phoneNumber);

    /**
     * 通过名字查找客户
     *
     * @param userName
     * @return
     */
    Customer findByUserName(String userName);
}
