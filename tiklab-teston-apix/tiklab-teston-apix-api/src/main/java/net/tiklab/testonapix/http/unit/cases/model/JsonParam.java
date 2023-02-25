package net.tiklab.testonapix.http.unit.cases.model;

import http.unit.cases.model.ApiUnitCase;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.join.annotation.JoinQuery;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel
@Mapper(targetAlias = "JsonParamEntity")
@Join
public class JsonParam extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="parent",desc="上级参数")
    @Mappings({
            @Mapping(source = "parent.id",target = "parentId")
    })
    @JoinQuery(key = "id")
    private JsonParam parent;

    @NotNull
    @ApiProperty(name="apiUnit",desc="所属接口",required = true)
    @Mappings({
            @Mapping(source = "apiUnit.id",target = "apiUnitId")
    })
    @JoinQuery(key = "id")
    private ApiUnitCase apiUnit;

    @NotNull
    @ApiProperty(name="paramName",desc="参数名字",required = true)
    private String paramName;

    @NotNull
    @ApiProperty(name="dataType",desc="参数类型",required = true)
    private String dataType;

    @NotNull
    @ApiProperty(name="required",desc="是否必须,0:非必须;1:必须",required = true)
    private Integer required=0;

    @ApiProperty(name="desc",desc="参数说明")
    private String desc;

    @ApiProperty(name="value",desc="value")
    private String value;

    @ApiProperty(name="sort",desc="排序")
    private Integer sort;

    @ApiProperty(name="children",desc="子节点列表")
    private List<JsonParam> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JsonParam getParent() {
        return parent;
    }

    public void setParent(JsonParam parent) {
        this.parent = parent;
    }

    public ApiUnitCase getApiUnit() {
        return apiUnit;
    }

    public void setApiUnit(ApiUnitCase apiUnit) {
        this.apiUnit = apiUnit;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
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

    public List<JsonParam> getChildren() {
        return children;
    }

    public void setChildren(List<JsonParam> children) {
        this.children = children;
    }
}
