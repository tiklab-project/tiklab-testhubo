package io.tiklab.teston.test.apix.http.unit.execute.model;

import io.tiklab.teston.test.apix.http.unit.cases.model.ApiUnitCase;
import io.tiklab.teston.test.apix.http.unit.cases.model.ApiUnitCaseExt;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

/**
 * 接口单元测试执行数据构造 模型
 */
@ApiModel
public class ApiUnitTestRequest extends BaseModel {

    @ApiProperty(name="apiUnitCase",desc="测试用例")
    private ApiUnitCase apiUnitCase;

    @ApiProperty(name="apiEnv",desc="测试基准地址")
    private String apiEnv;

    @ApiProperty(name="apiUnitCaseExt",desc="测试用例")
    private ApiUnitCaseExt apiUnitCaseExt;

    @ApiProperty(name="exeType",desc="当前执行的类型，用于测试计划中")
    private String exeType;

    public ApiUnitCase getApiUnitCase() {
        return apiUnitCase;
    }

    public void setApiUnitCase(ApiUnitCase apiUnitCase) {
        this.apiUnitCase = apiUnitCase;
    }

    public String getApiEnv() {
        return apiEnv;
    }

    public void setApiEnv(String apiEnv) {
        this.apiEnv = apiEnv;
    }

    public ApiUnitCaseExt getApiUnitCaseExt() {
        return apiUnitCaseExt;
    }

    public void setApiUnitCaseExt(ApiUnitCaseExt apiUnitCaseExt) {
        this.apiUnitCaseExt = apiUnitCaseExt;
    }

    public String getExeType() {
        return exeType;
    }

    public void setExeType(String exeType) {
        this.exeType = exeType;
    }
}
