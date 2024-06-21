package io.thoughtware.teston.test.web.scene.cases.model;

import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.teston.test.test.model.TestCase;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;

/**
 * web场景用例 模型
 */
@ApiModel
@Mapper(targetName = "io.thoughtware.teston.web.scene.cases.entity.WebSceneCaseEntity")
@Join
public class WebSceneCase extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="testCase",desc="所属用例",required = true)
    @Mappings({
            @Mapping(source = "testCase.id",target = "testCaseId")
    })
    @JoinQuery(key = "id")
    private TestCase testCase;

    @ApiProperty(name="stepNum",desc="步骤数量")
    private int stepNum;

    @ApiProperty(name="variableNum",desc="变量数量")
    private int variableNum;

    @ApiProperty(name="instanceNum",desc="历史数量")
    private int instanceNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public int getStepNum() {
        return stepNum;
    }

    public void setStepNum(int stepNum) {
        this.stepNum = stepNum;
    }

    public int getVariableNum() {
        return variableNum;
    }

    public void setVariableNum(int variableNum) {
        this.variableNum = variableNum;
    }

    public int getInstanceNum() {
        return instanceNum;
    }

    public void setInstanceNum(int instanceNum) {
        this.instanceNum = instanceNum;
    }
}
