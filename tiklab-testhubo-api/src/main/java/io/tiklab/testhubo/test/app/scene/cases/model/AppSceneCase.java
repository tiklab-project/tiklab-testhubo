package io.tiklab.testhubo.test.app.scene.cases.model;

import io.tiklab.testhubo.test.test.model.TestCase;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

/**
 * app场景用例 模型
 */
@ApiModel
@Mapper(targetName = "io.tiklab.testhubo.app.scene.cases.entity.AppSceneCaseEntity")
@Join
public class AppSceneCase extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="testCases",desc="用例")
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
