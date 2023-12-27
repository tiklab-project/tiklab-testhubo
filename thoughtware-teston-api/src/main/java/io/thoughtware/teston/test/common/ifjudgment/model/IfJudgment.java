package io.thoughtware.teston.test.common.ifjudgment.model;

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
public class IfJudgment extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="caseId",desc="用例id")
    private String caseId;

    @ApiProperty(name="relation",desc="条件关系:and/or")
    private String relation;

    @ApiProperty(name="ifVariableList",desc="if 变量列表")
    private List<IfVariable> ifVariableList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public List<IfVariable> getIfVariableList() {
        return ifVariableList;
    }

    public void setIfVariableList(List<IfVariable> ifVariableList) {
        this.ifVariableList = ifVariableList;
    }
}
