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

@ApiModel
@Mapper(targetAlias = "AssertCaseEntity")
@Join
public class AssertCase extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @NotNull
    @ApiProperty(name="apiUnit",desc="所属接口",required = true)
    @Mappings({
            @Mapping(source = "apiUnit.id",target = "apiUnitId")
    })
    @JoinQuery(key = "id")
    private ApiUnitCase apiUnit;

    @ApiProperty(name="source",desc="来源,1:状态码;2:请求头;3:请求体")
    private Integer source;

    @ApiProperty(name="propertyName",desc="属性名称")
    private String propertyName;

    @ApiProperty(name="dataType",desc="数据类型")
    private String dataType;

    @ApiProperty(name="comparator",desc="比较符")
    private String comparator;

    @ApiProperty(name="value",desc="值")
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

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
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
    public String getComparator() {
        return comparator;
    }

    public void setComparator(String comparator) {
        this.comparator = comparator;
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
