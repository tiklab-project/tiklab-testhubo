package io.thoughtware.teston.test.apix.http.unit.cases.model;


import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel
@Mapper(targetName  = "io.thoughtware.teston.test.apix.http.unit.cases.entity.JsonResponseEntity")
@Join
public class JsonResponseUnit extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="parent",desc="上级属性")
    @Mappings({
            @Mapping(source = "parent.id",target = "parentId")
    })
    @JoinQuery(key = "id")
    private JsonResponseUnit parent;

    @NotNull
    @ApiProperty(name="apiUnit",desc="所属接口",required = true)
    @Mappings({
            @Mapping(source = "apiUnit.id",target = "apiUnitId")
    })
    @JoinQuery(key = "id")
    private ApiUnitCase apiUnit;

    @NotNull
    @ApiProperty(name="propertyName",desc="名字",required = true)
    private String propertyName;

    @NotNull
    @ApiProperty(name="dataType",desc="属性类型",required = true)
    private String dataType;

    @NotNull
    @ApiProperty(name="required",desc="是否必须,0:非必须;1:必须",required = true)
    private Integer required=0;

    @ApiProperty(name="desc",desc="参数说明")
    private String desc;

    @ApiProperty(name="value",desc="示例值")
    private String value;

    @ApiProperty(name="sort",desc="排序")
    private Integer sort;

    @ApiProperty(name="children",desc="下级属性列表")
    private List<JsonResponseUnit> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JsonResponseUnit getParent() {
        return parent;
    }

    public void setParent(JsonResponseUnit parent) {
        this.parent = parent;
    }

    public ApiUnitCase getApiUnit() {
        return apiUnit;
    }

    public void setApiUnit(ApiUnitCase apiUnit) {
        this.apiUnit = apiUnit;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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

    public List<JsonResponseUnit> getChildren() {
        return children;
    }

    public void setChildren(List<JsonResponseUnit> children) {
        this.children = children;
    }
}
