package com.aliyun.cloudcallcenter.crm.aliyun.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author edward
 * @date 2017/11/12
 */
@Entity
@Table(name = "aliyun_account")
public class AliyunAccount {
    /**
     * CRM用户和阿里云账户映射关系ID
     */
    private Long id;

    /**
     * CRM用户ID
     */
    private Long userId;

    /**
     * 阿里云账户OAuth2 Access Code
     */
    private String accessCode;

    /**
     * 该映射关系创建时间
     */
    private Timestamp gmtCreate;

    /**
     * 该映射关系最后修改时间
     */
    private Timestamp gmtModified;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(length = 2048)
    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }
}
