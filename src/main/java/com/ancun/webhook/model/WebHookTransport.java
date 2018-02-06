package com.ancun.webhook.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author
 */
@Entity
@Table(name = "web_hook_transport")
public class WebHookTransport implements Serializable {
    private static final long serialVersionUID = -8153955144861148041L;
    /**
     * 唯一Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    /**
     * 保全号 32位
     */
    @Column(name = "record_no")
    private String recordNo;

    /**
     * 类型(1-file 2-urls )
     */
    @Column(name = "type")
    private Integer type;

    /**
     * file 或 urls id
     */
    @Column(name = "attach_id")
    private Integer attachId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
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

    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAttachId() {
        return attachId;
    }

    public void setAttachId(Integer attachId) {
        this.attachId = attachId;
    }

    @Override
    public String
    toString() {
        return "WebHookTransport{" +
                "id=" + id +
                ", recordNo='" + recordNo + '\'' +
                ", type=" + type +
                ", attachId=" + attachId +
                ", createTime=" + createTime +
                '}';
    }
}