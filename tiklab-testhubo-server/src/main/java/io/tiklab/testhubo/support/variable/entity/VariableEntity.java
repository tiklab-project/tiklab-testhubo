package io.tiklab.testhubo.support.variable.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 场景变量 实体
 */
@Entity
@Table(name="teston_variable")
public class VariableEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 用例
    @Column(name = "belong_id",length = 32)
    private String belongId;

    // 名称
    @Column(name = "name",length = 64)
    private String name;

    // 值
    @Column(name = "value",length = 256)
    private String value;

    // 创建时间
    @Column(name = "create_time",length = 4)
    private Timestamp createTime;

    // 类型
    @Column(name = "type",length = 32)
    private String type;

    @Column(name = "description",length = 512)
    private String desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getBelongId() {
        return belongId;
    }

    public void setBelongId(String belongId) {
        this.belongId = belongId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
