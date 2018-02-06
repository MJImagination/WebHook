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
     * 状态(0-失败 1-成功 )
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
    @Column(name = "has_file")
    private Integer hasFile;

    /**
     * 是否有urls(0 否  1是)
     */
    @Column(name = "has_urls")
    private Integer hasUrls;

    /**
     * 附件id
     */
    @Column(name = "file_id")
    private String fileId;

    /**
     * url id
     */
    @Column(name = "urls_id")
    private String urlsId;

    /**
     * 创建时间
     */
    @Column(name="create_time")
    private Date createTime;

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

    public Integer getHasFile() {
        return hasFile;
    }

    public void setHasFile(Integer hasFile) {
        this.hasFile = hasFile;
    }

    public Integer getHasUrls() {
        return hasUrls;
    }

    public void setHasUrls(Integer hasUrls) {
        this.hasUrls = hasUrls;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getUrlsId() {
        return urlsId;
    }

    public void setUrlsId(String urlsId) {
        this.urlsId = urlsId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
                ", hasFile=" + hasFile +
                ", hasUrls=" + hasUrls +
                ", fileId='" + fileId + '\'' +
                ", urlsId='" + urlsId + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}