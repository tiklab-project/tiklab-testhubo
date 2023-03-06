package net.tiklab.teston.test.apix.http.unit.cases.model;

import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.join.annotation.JoinQuery;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

/**
 * 请求头 模型
 */
@ApiModel
@Mapper(targetAlias = "RequestHeaderEntity")
@Join
public class RequestHeader extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @NotNull
    @ApiProperty(name="apiUnit",desc="所属接口",required = true)
    @Mappings({
            @Mapping(source = "apiUnit.id",target = "apiUnitId")
    })
    @JoinQuery(key = "id")
    private ApiUnitCase apiUnit;

    @NotNull
    @ApiProperty(name="headerName",desc="请求头名字",required = true)
    private String headerName;

    @NotNull
    @ApiProperty(name="required",desc="是否必须,0:非必须;1:必须",eg="@int16",required = true)
    private Integer required=0;

    @ApiProperty(name="desc",desc="描述")
    private String desc;

    @ApiProperty(name="value",desc="示例值")
    private String value;

    @ApiProperty(name="sort",desc="排序")
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ApiUnitCase getApiUnit() {
        return apiUnit;
    }

    public void setApiUnit(ApiUnitCase apiUnit) {
        this.apiUnit = apiUnit;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
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
