package net.tiklab.teston.integration.actiontype.entity;

import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.GeneratorValue;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;import net.tiklab.dal.jpa.annotation.Entity;

@Entity @Table(name = "teston_action_type")
public class ActionTypeEntity {
    //编码
    @Id
    @GeneratorValue
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
