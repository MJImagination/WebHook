package com.ancun.webhook.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * @Description: webhook http请求和mq接受记录表
 * @Author: MJ
 * @Date: Created in 2018/2/4
 */
@Entity
@Table(name="web_hook_record")
public class WebHookRecord implements Serializable {
    private static final long serialVersionUID = -8719385968966266463L;
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
     * 请求次数
     */
    @Column(name="call_num")
    private Integer callNum;

    /**
     * 回调返回内容
     */
    @Column(name="call_back_content")
    private String callBackContent;

    /**
     * http请求状态状态(3:未请求 0:失败 1:成功 )
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 保全号 32位
     */
    @Column(name = "record_no")
    private String recordNo;

    /**
     * 是否有附件(0 否  1是)
     */
    @Column(name = "has_attach")
    private Integer hasAttach;

    /**
     * 是否有urls(0 否  1是)
     */
    @Column(name = "has_url")
    private Integer hasUrl;

    /**
     * 创建时间
     */
    @Column(name="create_time")
    private Date createTime;

    /**
     * 过期时间
     */
    @Column(name = "out_time")
    private Date outTime;

    /**
     * 用于判断保全是否完成（1:是   0：否）
     */
    @Column(name = "is_complete")
    private Integer isComplete;

    /**
     * 用于判断mq清单是否完成（1:是   0：否）
     */
    @Column(name = "is_list_complete")
    private Integer isListComplete;

    /**
     * 附件id清单
     */
    @Column(name = "attach_id_list")
    private String attachIdList;

    /**
     * url id
     */
    @Column(name = "url_id_list")
    private String urlIdList;

    /**
     * 附件id接受字符串用"，"号分隔
     */
    @Column(name = "receive_attach_id")
    private String receiveAttachId;

    /**
     * url id接受字符串用"，"号分隔
     */
    @Column(name = "receive_url_id")
    private String receiveUrlId;

    public Integer getIsListComplete() {
        return isListComplete;
    }

    public void setIsListComplete(Integer isListComplete) {
        this.isListComplete = isListComplete;
    }

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

    public Integer getCallNum() {
        return callNum;
    }

    public void setCallNum(Integer callNum) {
        this.callNum = callNum;
    }

    public String getCallBackContent() {
        return callBackContent;
    }

    public void setCallBackContent(String callBackContent) {
        this.callBackContent = callBackContent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    public Integer getHasAttach() {
        return hasAttach;
    }

    public void setHasAttach(Integer hasAttach) {
        this.hasAttach = hasAttach;
    }

    public Integer getHasUrl() {
        return hasUrl;
    }

    public void setHasUrl(Integer hasUrl) {
        this.hasUrl = hasUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }

    public String getAttachIdList() {
        return attachIdList;
    }

    public void setAttachIdList(String attachIdList) {
        this.attachIdList = attachIdList;
    }

    public String getUrlIdList() {
        return urlIdList;
    }

    public void setUrlIdList(String urlIdList) {
        this.urlIdList = urlIdList;
    }

    public String getReceiveAttachId() {
        return receiveAttachId;
    }

    public void setReceiveAttachId(String receiveAttachId) {
        this.receiveAttachId = receiveAttachId;
    }

    public String getReceiveUrlId() {
        return receiveUrlId;
    }

    public void setReceiveUrlId(String receiveUrlId) {
        this.receiveUrlId = receiveUrlId;
    }

    @Override
    public String toString() {
        return "WebHookRecord{" +
                "id=" + id +
                ", partnerId=" + partnerId +
                ", userId=" + userId +
                ", callBackUrl='" + callBackUrl + '\'' +
                ", callNum=" + callNum +
                ", callBackContent='" + callBackContent + '\'' +
                ", status=" + status +
                ", recordNo='" + recordNo + '\'' +
                ", hasAttach=" + hasAttach +
                ", hasUrl=" + hasUrl +
                ", createTime=" + createTime +
                ", outTime=" + outTime +
                ", isComplete=" + isComplete +
                ", attachIdList='" + attachIdList + '\'' +
                ", urlIdList='" + urlIdList + '\'' +
                ", receiveAttachId='" + receiveAttachId + '\'' +
                ", receiveUrlId='" + receiveUrlId + '\'' +
                '}';
    }
}