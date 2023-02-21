package net.tiklab.teston.apitest.http.unittest.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;

@ApiModel
@Mapper(targetAlias = "ResponseResultEntity")
@Join
public class ResponseResult extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="apiUnitId",desc="所属接口")
    private String apiUnitId;

    @ApiProperty(name="resultType",desc="返回结果类型,json/raw")
    private java.lang.String resultType;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public String getApiUnitId() {
        return apiUnitId;
    }

    public void setApiUnitId(String apiUnitId) {
        this.apiUnitId = apiUnitId;
    }

    public java.lang.String getResultType() {
        return resultType;
    }

    public void setResultType(java.lang.String resultType) {
        this.resultType = resultType;
    }
}
