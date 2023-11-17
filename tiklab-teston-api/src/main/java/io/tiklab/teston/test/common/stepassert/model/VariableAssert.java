package io.tiklab.teston.test.common.stepassert.model;

import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

/**
 * 变量断言 模型
 */
@ApiModel
@Mapper(targetName  = "io.tiklab.teston.test.common.stepassert.entity.VariableAssertEntity")
@Join
public class VariableAssert extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="assertId",desc="assertId")
    private String assertId;

    @ApiProperty(name="variable",desc="变量")
    private String variable;

    @ApiProperty(name="compare",desc="比较")
    private Integer compare;

    @ApiProperty(name="expect",desc="变量值")
    private String expect;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssertId() {
        return assertId;
    }

    public void setAssertId(String assertId) {
        this.assertId = assertId;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public Integer getCompare() {
        return compare;
    }

    public void setCompare(Integer compare) {
        this.compare = compare;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }
}
