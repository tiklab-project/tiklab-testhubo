package io.thoughtware.testhubo.test.common.ifjudgment.model;

import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.util.List;


/**
 * if判断 模型
 */
@ApiModel
@Mapper
@Join
public class IfJudgmentInstance extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="stepInstanceId",desc="stepInstanceId")
    private String stepInstanceId;

    @ApiProperty(name="relation",desc="条件关系:and/or")
    private String relation;

    @ApiProperty(name="ifVariableInstanceList",desc="if 变量列表")
    private List<IfVariableInstance> ifVariableInstanceList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStepInstanceId() {
        return stepInstanceId;
    }

    public void setStepInstanceId(String stepInstanceId) {
        this.stepInstanceId = stepInstanceId;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public List<IfVariableInstance> getIfVariableInstanceList() {
        return ifVariableInstanceList;
    }

    public void setIfVariableInstanceList(List<IfVariableInstance> ifVariableInstanceList) {
        this.ifVariableInstanceList = ifVariableInstanceList;
    }


}
