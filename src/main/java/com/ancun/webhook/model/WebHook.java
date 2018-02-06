package com.ancun.webhook.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/2/4
 */
@Entity
@Table(name="web_hook")
public class WebHook implements Serializable {
    private static final long serialVersionUID = 1665027674223026301L;
    /**
     * 唯一Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    /**
     * 接入者id
     */
    @Column(name="partner_id")
    private Integer partnerId;

    /**
     * 用户id
     */
    @Column(name="user_id")
    private Integer userId;

    /**
     * 回调地址
     */
    @Column(name="call_back_url")
    private String callBackUrl;

    /**
     * 状态(1启用,0关闭)
     */
    @Column(name="status")
    private Integer status;

    /**
     * 创建时间
     */
    @Column(name="create_time")
    private Date createTime;

    /**
     * 备注
     */
    @Column(name="remarks")
    private String remarks;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    @Override
    public String toString() {
        return "WebHook{" +
                "id=" + id +
                ", partnerId=" + partnerId +
                ", userId=" + userId +
                ", callBackUrl='" + callBackUrl + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}