package io.tiklab.testhubo.integrated.postin.postinapi.model;

import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

/**
 * 响应头模型
 */
@ApiModel
@Join
@Mapper
public class ResponseHeader extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private String id;

    @NotNull
    @ApiProperty(name="http",desc="所属接口",required = true)
    @Mappings({
            @Mapping(source = "http.id",target = "httpId")
    })
    @JoinQuery(key = "id")
    private HttpApi http;

    @NotNull
    @ApiProperty(name="headerName",desc="响应头名称",eg="@text32",required = true)
    private String headerName;

    @NotNull
    @ApiProperty(name="required",desc="是否必须,0:非必须;1:必须",eg="@int16",required = true)
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

    public HttpApi getHttp() {
        return http;
    }

    public void setHttp(HttpApi http) {
        this.http = http;
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
