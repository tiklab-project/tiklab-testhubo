package io.tiklab.teston.test.apix.http.unit.cases.model;

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
 * 接口单元用例 模型
 */
@ApiModel
@Join
@Mapper
public class ApiUnitCase extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="testCases",desc="用例")
    @Mappings({
            @Mapping(source = "testCase.id",target = "testCaseId")
    })
    @JoinQuery(key = "id")
    private TestCases testCase;

    @ApiProperty(name="path",desc="路径")
    private String path;

    @ApiProperty(name="methodType",desc="请求类型,GET/POST")
    private String methodType;


    public String getId() {
        return id;
    }

    public ApiUnitCase setId(String id) {
        this.id = id;
        return this;
    }

    public TestCases getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCases testCase) {
        this.testCase = testCase;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

}
