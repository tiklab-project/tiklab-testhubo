package io.thoughtware.teston.test.common.ifjudgment.model;

import io.thoughtware.beans.annotation.Mapper;
import io.thoughtware.core.BaseModel;
import io.thoughtware.join.annotation.Join;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;


/**
 * if中的值比较 模型
 */
@ApiModel
@Mapper
@Join
public class IfVariable extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="stepId",desc="stepId")
    private String stepId;

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

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
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