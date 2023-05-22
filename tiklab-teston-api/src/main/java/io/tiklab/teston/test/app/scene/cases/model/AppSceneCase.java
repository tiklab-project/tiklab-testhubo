package io.tiklab.teston.test.app.scene.cases.model;

import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.teston.test.test.model.TestCases;

/**
 * app场景用例 模型
 */
@ApiModel
@Mapper(targetAlias = "AppSceneCaseEntity")
@Join
public class AppSceneCase extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="testCases",desc="用例")
    @Mappings({
            @Mapping(source = "testCase.id",target = "testCaseId")
    })
    @JoinQuery(key = "id")
    private TestCases testCase;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TestCases getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCases testCase) {
        this.testCase = testCase;
    }
}
