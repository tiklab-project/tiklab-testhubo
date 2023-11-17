package io.tiklab.teston.test.common.ifjudgment.model;

import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;


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

}
