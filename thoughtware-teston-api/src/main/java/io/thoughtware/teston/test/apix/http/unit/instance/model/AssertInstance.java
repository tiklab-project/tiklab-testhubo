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

    @ApiProperty(name="testInstance",desc="所属测试实例",required = true)
    private String instanceId;

    @ApiProperty(name="source",desc="来源,1:状态码;2:请求头;3:请求体")
    private Integer source;

    @ApiProperty(name="actualResult",desc="实际结果")
    private String actualResult;

    @ApiProperty(name="comparator",desc="比较符",required = true)
    private String comparator;

    @ApiProperty(name="value",desc="比较的值")
    private String value;

    @ApiProperty(name="result",desc="result")
    private Integer result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getActualResult() {
        return actualResult;
    }

    public void setActualResult(String actualResult) {
        this.actualResult = actualResult;
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
