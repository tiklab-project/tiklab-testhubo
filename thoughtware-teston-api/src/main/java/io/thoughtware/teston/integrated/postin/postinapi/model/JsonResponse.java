package io.thoughtware.teston.integrated.postin.postinapi.model;

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

/**
 * 响应中json 模型
 */
@ApiModel
@Join
@Mapper
public class JsonResponse extends BaseModel {

    @ApiProperty(name="id",desc="唯一ID")
    private String id;

    @ApiProperty(name="parent",desc="上级属性")
    @Mappings({
            @Mapping(source = "parent.id",target = "parentId")
    })
    @JoinQuery(key = "id")
    private JsonResponse parent;

    @NotNull
    @ApiProperty(name="http",desc="所属接口",eg="@selectOne",required = true)
    @Mappings({
            @Mapping(source = "http.id",target = "httpId")
    })
    @JoinQuery(key = "id")
    private HttpApi http;

    @NotNull
    @ApiProperty(name="propertyName",desc="属性名称",required = true)
    private String propertyName;

    @NotNull
    @ApiProperty(name="dataType",desc="数据类型,[int,string,boolean]",required = true)
    private String dataType;

    @NotNull
    @ApiProperty(name="required",desc="是否必须,0:非必须;1:必须",required = true)
    private Integer required;

    @ApiProperty(name="desc",desc="描述",eg="@text32")
    private String desc;

    @ApiProperty(name="value",desc="示例值")
    private String value;

    @ApiProperty(name="sort",desc="排序")
    private Integer sort;

    @ApiProperty(name="children",desc="下级属性列表")
    private List<JsonResponse> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HttpApi getHttp() {
        return http;
    }

    public void setHttp(HttpApi http) {
        this.http = http;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public JsonResponse getParent() {
        return parent;
    }

    public void setParent(JsonResponse parent) {
        this.parent = parent;
    }

    public List<JsonResponse> getChildren() {
        return children;
    }

    public void setChildren(List<JsonResponse> children) {
        this.children = children;
    }
}
