package io.thoughtware.testrubo.support.variable.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 接口环境 模型
 */
@ApiModel
@Mapper
public class Variable extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @NotNull
    @ApiProperty(name="name",desc="变量名",required = true)
    private String name;

    @ApiProperty(name="value",desc="值")
    private String value;

    @ApiProperty(name="desc",desc="描述")
    private String desc;

    @ApiProperty(name = "belongId", desc = "所属ID,接口放在项目下，ui放在场景下")
    private String belongId;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private java.sql.Timestamp createTime;

    @ApiProperty(name="type",desc="类型")
    private String type;

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

    public String getBelongId() {
        return belongId;
    }

    public void setBelongId(String belongId) {
        this.belongId = belongId;
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
