package io.tiklab.testhubo.integrated.postin.postinapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 接口定义中响应区的模型
 */
@ApiModel
@Mapper
public class ApiResponse extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识")
    private String id;

    @NotNull
    @ApiProperty(name="http",desc="所属接口",required = true)
    private String httpId;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="httpCode",desc="返回http状态码，默认200为正常")
    private Integer httpCode;

    @ApiProperty(name="name",desc="名称")
    private String name;

    @ApiProperty(name="dataType",desc="内容格式:json raw")
    private String dataType;

    @ApiProperty(name="jsonText",desc="根据dataType，设置相应的值 json")
    private String jsonText;

    @ApiProperty(name="rawText",desc="根据dataType，设置相应的值 raw")
    private String rawText;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHttpId() {
        return httpId;
    }

    public void setHttpId(String httpId) {
        this.httpId = httpId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getJsonText() {
        return jsonText;
    }

    public void setJsonText(String jsonText) {
        this.jsonText = jsonText;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }
}

