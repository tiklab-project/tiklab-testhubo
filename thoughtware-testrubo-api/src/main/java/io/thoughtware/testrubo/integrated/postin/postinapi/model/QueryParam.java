package io.thoughtware.testrubo.integrated.postin.postinapi.model;

import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

/**
 * query 模型
 */
@ApiModel
@Join
@Mapper
public class QueryParam extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private String id;

    @ApiProperty(name="apiId",desc="所属接口")
    private String apiId;

    @NotNull
    @ApiProperty(name="paramName",desc="参数名称",required = true)
    private String paramName;

    @NotNull
    @ApiProperty(name="required",desc="是否必须,0:非必须;1:必须",required = true)
    private Integer required = 0;

    @ApiProperty(name="desc",desc="描述",eg="@text32")
    private String desc;

    @ApiProperty(name="value",desc="示例值",eg="@text32")
    private String value;

    @ApiProperty(name="sort",desc="排序",eg="@int16")
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Integer getRequired() {
        return required;
    }

    public void setRequired(Integer required) {
        this.required = required;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

}
