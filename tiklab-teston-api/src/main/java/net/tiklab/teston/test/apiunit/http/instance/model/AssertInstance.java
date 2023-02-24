package net.tiklab.teston.test.apiunit.http.instance.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.join.annotation.JoinQuery;
import net.tiklab.teston.test.apiunit.http.instance.model.ApiUnitInstance;

import javax.validation.constraints.NotNull;

@ApiModel
@Mapper(targetAlias = "AssertInstanceEntity")
@Join
public class AssertInstance extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="testInstance",desc="所属测试实例",required = true)
    @Mappings({
            @Mapping(source = "testInstance.id",target = "instanceId")
    })
    @JoinQuery(key = "id")
    private ApiUnitInstance testInstance;

    @ApiProperty(name="source",desc="来源,1:状态码;2:请求头;3:请求体")
    private java.lang.Integer source;

    @ApiProperty(name="propertyName",desc="属性名称")
    private java.lang.String propertyName;

    @ApiProperty(name="dataType",desc="数据类型")
    private java.lang.String dataType;

    @NotNull
    @ApiProperty(name="comparator",desc="比较符",required = true)
    private java.lang.String comparator;

    @ApiProperty(name="value",desc="值")
    private java.lang.String value;

    @ApiProperty(name="result",desc="result")
    private java.lang.Integer result;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public ApiUnitInstance getTestInstance() {
        return testInstance;
    }

    public void setTestInstance(ApiUnitInstance testInstance) {
        this.testInstance = testInstance;
    }

    public java.lang.Integer getSource() {
        return source;
    }

    public void setSource(java.lang.Integer source) {
        this.source = source;
    }
    public java.lang.String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(java.lang.String propertyName) {
        this.propertyName = propertyName;
    }
    public java.lang.String getDataType() {
        return dataType;
    }

    public void setDataType(java.lang.String dataType) {
        this.dataType = dataType;
    }
    public java.lang.String getComparator() {
        return comparator;
    }

    public void setComparator(java.lang.String comparator) {
        this.comparator = comparator;
    }
    public java.lang.String getValue() {
        return value;
    }

    public void setValue(java.lang.String value) {
        this.value = value;
    }
    public java.lang.Integer getResult() {
        return result;
    }

    public void setResult(java.lang.Integer result) {
        this.result = result;
    }
}
