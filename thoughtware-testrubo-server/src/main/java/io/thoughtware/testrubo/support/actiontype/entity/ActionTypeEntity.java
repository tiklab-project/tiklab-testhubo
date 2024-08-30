package io.thoughtware.testrubo.support.actiontype.entity;

import io.thoughtware.dal.jpa.annotation.Column;
import io.thoughtware.dal.jpa.annotation.GeneratorValue;
import io.thoughtware.dal.jpa.annotation.Id;
import io.thoughtware.dal.jpa.annotation.Table;
import io.thoughtware.dal.jpa.annotation.Entity;

/**
 *app、web中操作类型 实体
 */
@Entity @Table(name = "teston_action_type")
public class ActionTypeEntity {
    //编码
    @Id
     @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    //名称
    @Column(name = "name",length = 32)
    private String name;

    //类型  web http
    @Column(name = "type",length = 32)
    private String type;

    //描述
    @Column(name = "description",length = 32)
    private String description;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
