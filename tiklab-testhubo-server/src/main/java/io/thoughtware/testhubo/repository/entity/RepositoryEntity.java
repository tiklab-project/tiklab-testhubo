package io.thoughtware.testhubo.repository.entity;


import io.thoughtware.dal.jpa.annotation.Column;
import io.thoughtware.dal.jpa.annotation.GeneratorValue;
import io.thoughtware.dal.jpa.annotation.Id;
import io.thoughtware.dal.jpa.annotation.Table;
import io.thoughtware.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * 仓库 实体
 */
@Entity
@Table(name="teston_repository")
public class RepositoryEntity implements Serializable {

    @Id
     @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 名称
    @Column(name = "name",length = 64)
    private String name;

    // 所属用户
    @Column(name = "user_id",length = 32)
    private String userId;

    // 描述
    @Column(name = "description",length = 64)
    private String desc;

    // 可见范围
    @Column(name = "visibility")
    private Integer visibility;

    // 图标地址
    @Column(name = "icon_url",length = 256)
    private String iconUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
