package io.thoughtware.teston.test.apix.http.unit.instance.model;

import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

/**
 * 接口单元
 * http协议
 * 断言实例 模型
 */
@ApiModel
@Mapper(targetName  = "io.thoughtware.teston.test.apix.http.unit.instance.entity.AssertInstanceEntity")
@Join
public class AssertInstance extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @NotNull
    @ApiProperty(name="testInstance",desc="所属测试实例",required = true)
    @Mappings({
            @Mapping(source = "testInstance.id",target = "instanceId")
    })
    @JoinQuery(key = "id")
    private ApiUnitInstance testInstance;

    @ApiProperty(name="source",desc="来源,1:状态码;2:请求头;3:请求体")
    private Integer source;

    @ApiProperty(name="propertyName",desc="属性名称")
    private String propertyName;

    @ApiProperty(name="dataType",desc="数据类型")
    private String dataType;

    @NotNull
    @ApiProperty(name="comparator",desc="比较符",required = true)
    private String comparator;

    @ApiProperty(name="value",desc="值")
    private String value;

    @ApiProperty(name="result",desc="result")
    private Integer result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ApiUnitInstance getTestInstance() {
        return testInstance;
    }

    public void setTestInstance(ApiUnitInstance testInstance) {
        this.testInstance = testInstance;
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
    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
