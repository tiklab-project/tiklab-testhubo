package net.tiklab.teston.testjob.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.join.annotation.JoinQuery;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneCase;

import javax.validation.constraints.NotNull;

@ApiModel
@Join
@Mapper(targetAlias = "QuartzTestcaseEntity")
public class QuartzTestcase extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @NotNull
    @ApiProperty(name="method",desc="定时任务主表",required = true)
    @Mappings({
            @Mapping(source = "quartzMaster.id",target = "quartzMasterId")
    })
    @JoinQuery(key = "id")
    private QuartzMaster quartzMaster;

    @NotNull
    @ApiProperty(name="testCase",desc="测试用例",required = true)
    @Mappings({
            @Mapping(source = "testCase.id",target = "testcaseId")
    })
    @JoinQuery(key = "id")
    private ApiSceneCase testCase;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public QuartzMaster getQuartzMaster() {
        return quartzMaster;
    }

    public void setQuartzMaster(QuartzMaster quartzMaster) {
        this.quartzMaster = quartzMaster;
    }

    public ApiSceneCase getTestCase() {
        return testCase;
    }

    public void setTestCase(ApiSceneCase testCase) {
        this.testCase = testCase;
    }
}
