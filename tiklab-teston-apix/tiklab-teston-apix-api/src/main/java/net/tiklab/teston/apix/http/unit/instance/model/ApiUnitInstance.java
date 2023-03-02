package net.tiklab.teston.apix.http.unit.instance.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.join.annotation.JoinQuery;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.teston.apix.http.unit.cases.model.ApiUnitCase;
import net.tiklab.user.user.model.User;

import java.sql.Timestamp;

/**
 * 用例实例或用例步骤记录 模型
 */
@ApiModel
@Mapper(targetAlias = "ApiUnitInstanceEntity")
@Join
public class ApiUnitInstance extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="apiUnit",desc="所属接口")
    @Mappings({
            @Mapping(source = "apiUnit.id",target = "apiUnitId")
    })
    @JoinQuery(key = "id")
    private ApiUnitCase apiUnit;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="elapsedTime",desc="耗时")
    private Double elapsedTime;

    @ApiProperty(name="requestInstance",desc="实例-请求部分")
    private RequestInstance requestInstance;

    @ApiProperty(name="responseInstance",desc="实例-响应部分")
    private ResponseInstance responseInstance;

    @ApiProperty(name="result",desc="结果  1,0")
    private Integer result;

    @ApiProperty(name="statusCode",desc="状态码")
    private Integer statusCode;

    @ApiProperty(name = "errMessage", desc = "错误信息")
    private String errMessage;

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

    public ApiUnitInstance setId(String id) {
        this.id = id;
        return this;
    }

    public ApiUnitCase getApiUnit() {
        return apiUnit;
    }

    public void setApiUnit(ApiUnitCase apiUnit) {
        this.apiUnit = apiUnit;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Double getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Double elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public RequestInstance getRequestInstance() {
        return requestInstance;
    }

    public void setRequestInstance(RequestInstance requestInstance) {
        this.requestInstance = requestInstance;
    }

    public ResponseInstance getResponseInstance() {
        return responseInstance;
    }

    public void setResponseInstance(ResponseInstance responseInstance) {
        this.responseInstance = responseInstance;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
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
