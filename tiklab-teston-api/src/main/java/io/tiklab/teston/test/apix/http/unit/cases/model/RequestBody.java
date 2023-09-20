package io.tiklab.teston.test.apix.http.unit.cases.model;

import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

/**
 * 请求体的 模型
 */
@ApiModel
@Mapper(targetName  = "io.tiklab.teston.test.apix.http.unit.cases.entity.RequestBodyEntity")
@Join
public class RequestBody extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="apiUnitId",desc="所属接口")
    private String apiUnitId;

    @ApiProperty(name="bodyType",desc="请求体类型，form/json/raw/binary")
    private String bodyType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiUnitId() {
        return apiUnitId;
    }

    public void setApiUnitId(String apiUnitId) {
        this.apiUnitId = apiUnitId;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
}
