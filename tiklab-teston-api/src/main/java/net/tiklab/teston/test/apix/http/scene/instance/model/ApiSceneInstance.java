package net.tiklab.teston.test.apix.http.scene.instance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.join.annotation.JoinQuery;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.user.user.model.User;

import java.sql.Timestamp;
import java.util.List;

/**
 * 场景历史实例 模型
 */
@ApiModel
@Mapper(targetAlias = "ApiSceneInstanceEntity")
@Join
public class ApiSceneInstance extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="apiSceneId",desc="测试用例id")
    private String apiSceneId;

    @ApiProperty(name="result",desc="测试结果 1,0 ")
    private Integer result;

    @ApiProperty(name="elapsedTime",desc="耗时")
    private Integer elapsedTime;

    @ApiProperty(name="testNumber",desc="步骤数量")
    private Integer testNumber;

    @ApiProperty(name="passNumber",desc="测试通过数量")
    private Integer passNumber;

    @ApiProperty(name="failNumber",desc="未通过数量")
    private Integer failNumber;

    @ApiProperty(name="passRate",desc="测试通过率")
    private String passRate;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="stepList",desc="步骤示例列表")
    private List<ApiSceneStepInstanceBind> stepList;

    @ApiProperty(name="createUser",desc="创建人")
    @Mappings({
            @Mapping(source = "createUser.id",target = "createUser")
    })
    @JoinQuery(key = "id")
    private User createUser;

    @ApiProperty(name="executeNumber",desc="执行次数")
    private Integer executeNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getTestNumber() {
        return testNumber;
    }

    public void setTestNumber(Integer testNumber) {
        this.testNumber = testNumber;
    }

    public Integer getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Integer elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public List<ApiSceneStepInstanceBind> getStepList() {
        return stepList;
    }

    public void setStepList(List<ApiSceneStepInstanceBind> stepList) {
        this.stepList = stepList;
    }

    public String getApiSceneId() {
        return apiSceneId;
    }

    public void setApiSceneId(String apiSceneId) {
        this.apiSceneId = apiSceneId;
    }

    public Integer getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(Integer passNumber) {
        this.passNumber = passNumber;
    }

    public Integer getFailNumber() {
        return failNumber;
    }

    public void setFailNumber(Integer failNumber) {
        this.failNumber = failNumber;
    }

    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Integer getExecuteNumber() {
        return executeNumber;
    }

    public void setExecuteNumber(Integer executeNumber) {
        this.executeNumber = executeNumber;
    }
}
