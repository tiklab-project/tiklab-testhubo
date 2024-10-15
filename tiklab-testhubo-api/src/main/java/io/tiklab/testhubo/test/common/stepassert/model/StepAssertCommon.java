package io.tiklab.testhubo.test.common.stepassert.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.sql.Timestamp;

/**
 * 断言公共 模型
 */
@ApiModel
@Mapper(targetName = "io.tiklab.testhubo.test.common.stepassert.entity.StepAssertCommonEntity")
@Join
public class StepAssertCommon extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="stepId",desc="stepId")
    private String stepId;

    @ApiProperty(name="type",desc="断言类型")
    private String type;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private java.sql.Timestamp createTime;

    @ApiProperty(name="variableAssert",desc="变量断言")
    private VariableAssert variableAssert;
    @ApiProperty(name="elementAssert",desc="元素断言")
    private ElementAssert elementAssert;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public VariableAssert getVariableAssert() {
        return variableAssert;
    }

    public void setVariableAssert(VariableAssert variableAssert) {
        this.variableAssert = variableAssert;
    }

    public ElementAssert getElementAssert() {
        return elementAssert;
    }

    public void setElementAssert(ElementAssert elementAssert) {
        this.elementAssert = elementAssert;
    }
}
