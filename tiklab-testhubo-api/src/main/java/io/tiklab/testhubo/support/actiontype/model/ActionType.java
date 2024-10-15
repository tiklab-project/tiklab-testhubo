package io.tiklab.testhubo.support.actiontype.model;

import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.core.BaseModel;

/**
 * app、web中操作类型 模型
 */
@ApiModel
@Mapper
public class ActionType extends BaseModel{

    @ApiProperty(name="id",desc="编码")
    private java.lang.String id;

    @ApiProperty(name="name",desc="名称")
    private java.lang.String name;

    @ApiProperty(name="type",desc="类型  WEB  APP ")
    private java.lang.String type;

    @ApiProperty(name="description",desc="说明")
    private java.lang.String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }
    public java.lang.String getType() {
        return type;
    }

    public void setType(java.lang.String type) {
        this.type = type;
    }
    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }
}
