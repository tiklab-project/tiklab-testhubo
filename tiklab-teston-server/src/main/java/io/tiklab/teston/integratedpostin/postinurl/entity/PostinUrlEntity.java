package io.tiklab.teston.integratedpostin.postinurl.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * postinUrl配置 实体
 */
@Entity
@Table(name="teston_postin_url")
public class PostinUrlEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 所属仓库
    @Column(name = "user_id",length = 32)
    private String userId;

    // 地址
    @Column(name = "url",length = 256)
    private String url;

    // 状态
    @Column(name = "status",length = 8)
    private Integer status;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
