package net.tiklab.teston.test.api.http.unit.cases.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.join.annotation.JoinQuery;

import javax.validation.constraints.NotNull;

@ApiModel
@Mapper(targetAlias = "FormUrlencodedEntity")
@Join
public class FormUrlencoded extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="apiUnit",desc="所属接口",required = true)
    @Mappings({
            @Mapping(source = "apiUnit.id",target = "apiUnitId")
    })
    @JoinQuery(key = "id")
    private ApiUnitCase apiUnit;

    @NotNull
    @ApiProperty(name="paramName",desc="paramName",required = true)
    private java.lang.String paramName;

    @NotNull
    @ApiProperty(name="dataType",desc="dataType",required = true)
    private java.lang.String dataType;

    @NotNull
    @ApiProperty(name="required",desc="required",required = true)
    private java.lang.Integer required;

    @ApiProperty(name="desc",desc="desc")
    private java.lang.String desc;

    @ApiProperty(name="value",desc="value")
    private java.lang.String value;

    @ApiProperty(name="sort",desc="sort")
    private java.lang.Integer sort;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public ApiUnitCase getApiUnit() {
        return apiUnit;
    }

    public void setApiUnit(ApiUnitCase apiUnit) {
        this.apiUnit = apiUnit;
    }

    public java.lang.String getParamName() {
        return paramName;
    }
    public void setParamName(java.lang.String paramName) {
        this.paramName = paramName;
    }
    public java.lang.String getDataType() {
        return dataType;
    }

    public void setDataType(java.lang.String dataType) {
        this.dataType = dataType;
    }
    public java.lang.Integer getRequired() {
        return required;
    }

    public void setRequired(java.lang.Integer required) {
        this.required = required;
    }
    public java.lang.String getDesc() {
        return desc;
    }

    public void setDesc(java.lang.String desc) {
        this.desc = desc;
    }
    public java.lang.String getValue() {
        return value;
    }

    public void setValue(java.lang.String value) {
        this.value = value;
    }
    public java.lang.Integer getSort() {
        return sort;
    }

    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
    }
}
