package com.ancun.webhook.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @Author: MJ
 * @Date: Created in 2018/2/4
 */
@Entity
@Table(name = "publicity_data")
public class PublicityData implements Serializable {
    private static final long serialVersionUID = 2522611910274318356L;
    /**
     * 唯一Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    /**
     * 内容类型标识，对应Main_data里的type_flag（不同类型保持一致）
     */
    @Column(name = "flag")
    private Integer flag;

    /**
     * 接入者ID
     */
    @Column(name = "partner_id")
    private Long partnerId;

    /**
     * 主体hash
     */
    @Column(name = "body_hash")
    private String bodyHash;

    /**
     * 保全号
     */
    @Column(name = "recored_no")
    private String recoredNo;

    /**
     * 保全时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "recored_time")
    private Date recoredTime;

    /**
     * 公示时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "publicity_time")
    private Date publicityTime;

    /**
     * 传输类型（1：传输 2：接受）
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 唯一Id
     *
     * @return id 唯一Id
     */
    public Long getId() {
        return id;
    }

    /**
     * 唯一Id
     *
     * @param id 唯一Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 内容类型标识，对应Main_data里的type_flag（不同类型保持一致）
     *
     * @return flag 内容类型标识，对应Main_data里的type_flag（不同类型保持一致）
     */
    public Integer getFlag() {
        return flag;
    }

    /**
     * 内容类型标识，对应Main_data里的type_flag（不同类型保持一致）
     *
     * @param flag 内容类型标识，对应Main_data里的type_flag（不同类型保持一致）
     */
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    /**
     * 接入者ID
     *
     * @return partner_id 接入者ID
     */
    public Long getPartnerId() {
        return partnerId;
    }

    /**
     * 接入者ID
     *
     * @param partnerId 接入者ID
     */
    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    /**
     * 主体hash
     *
     * @return body_hash 主体hash
     */
    public String getBodyHash() {
        return bodyHash;
    }

    /**
     * 主体hash
     *
     * @param bodyHash 主体hash
     */
    public void setBodyHash(String bodyHash) {
        this.bodyHash = bodyHash == null ? null : bodyHash.trim();
    }

    /**
     * 保全号
     *
     * @return recored_no 保全号
     */
    public String getRecoredNo() {
        return recoredNo;
    }

    /**
     * 保全号
     *
     * @param recoredNo 保全号
     */
    public void setRecoredNo(String recoredNo) {
        this.recoredNo = recoredNo == null ? null : recoredNo.trim();
    }

    /**
     * 保全时间
     *
     * @return recored_time 保全时间
     */
    public Date getRecoredTime() {
        return recoredTime;
    }

    /**
     * 保全时间
     *
     * @param recoredTime 保全时间
     */
    public void setRecoredTime(Date recoredTime) {
        this.recoredTime = recoredTime;
    }

    /**
     * 公示时间
     *
     * @return publicity_time 公示时间
     */
    public Date getPublicityTime() {
        return publicityTime;
    }

    /**
     * 公示时间
     *
     * @param publicityTime 公示时间
     */
    public void setPublicityTime(Date publicityTime) {
        this.publicityTime = publicityTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PublicityData{" +
                "id=" + id +
                ", flag=" + flag +
                ", partnerId=" + partnerId +
                ", bodyHash='" + bodyHash + '\'' +
                ", recoredNo='" + recoredNo + '\'' +
                ", recoredTime=" + recoredTime +
                ", publicityTime=" + publicityTime +
                ", type=" + type +
                '}';
    }
}