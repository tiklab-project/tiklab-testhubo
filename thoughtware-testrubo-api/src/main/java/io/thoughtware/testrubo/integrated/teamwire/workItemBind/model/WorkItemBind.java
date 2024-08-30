package io.thoughtware.testrubo.integrated.teamwire.workItemBind.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.testrubo.test.test.model.TestCase;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.testrubo.integrated.teamwire.workItem.model.WorkItemTestOn;

import java.sql.Timestamp;

/**
 * postinUrl配置 模型
 */
@ApiModel
@Mapper
@Join
public class WorkItemBind extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="workItemId",desc="workItemId")
    @Mappings({
            @Mapping(source = "workItem.id",target = "workItemId")
    })
    @JoinQuery(key = "id")
    private WorkItemTestOn workItem;

    @ApiProperty(name="caseId",desc="用例id")
    @Mappings({
            @Mapping(source = "testCase.id",target = "caseId")
    })
    @JoinQuery(key = "id")
    private TestCase testCase;

    @ApiProperty(name="repositoryId",desc="仓库id")
    private String repositoryId;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="projectUrl",desc="teamwire服务端地址")
    private String projectUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public WorkItemTestOn getWorkItem() {
        return workItem;
    }

    public void setWorkItem(WorkItemTestOn workItem) {
        this.workItem = workItem;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }
}
