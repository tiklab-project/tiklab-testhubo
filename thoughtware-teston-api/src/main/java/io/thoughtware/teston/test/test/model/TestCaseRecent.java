package io.thoughtware.teston.test.test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.beans.annotation.Mapper;
import io.thoughtware.beans.annotation.Mapping;
import io.thoughtware.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.join.annotation.Join;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.teston.repository.model.Repository;
import io.thoughtware.user.user.model.User;

import java.sql.Timestamp;

/**
 * 最近访问仓库 模型
 */
@ApiModel
@Join
@Mapper
public class TestCaseRecent extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="repository",desc="所属仓库",eg="@selectOne")
    @Mappings({
            @Mapping(source = "repository.id",target = "repositoryId")
    })
    private Repository repository;

    @ApiProperty(name="user",desc="所属用户",eg="@selectOne")
    @Mappings({
            @Mapping(source = "user.id",target = "userId")
    })
    private User user;

    @ApiProperty(name="testCaseId",desc="用例")
    @Mappings({
            @Mapping(source = "testCase.id",target = "testCaseId")
    })
    private TestCase testCase;


    @ApiProperty(name="updateTime",desc="updateTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.sql.Timestamp updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
