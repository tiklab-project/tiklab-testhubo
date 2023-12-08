package io.thoughtware.teston.test.apix.http.perf.cases.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.beans.annotation.Mapper;
import io.thoughtware.core.BaseModel;
import io.thoughtware.join.annotation.Join;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.sql.Timestamp;

/**
 * 接口性能 模型
 */
@ApiModel
@Mapper
@Join
public class ApiPerfTestData extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="caseId",desc="用例")
    private String caseId;

    @ApiProperty(name="name",desc="名称")
    private String name;

    @ApiProperty(name="testData",desc="测试数据")
    private String testData;

    @ApiProperty(name="type",desc="类型")
    private String type;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.sql.Timestamp createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
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
}
