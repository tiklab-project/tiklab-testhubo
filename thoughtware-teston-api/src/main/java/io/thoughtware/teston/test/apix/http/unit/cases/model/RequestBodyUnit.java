package io.thoughtware.teston.test.apix.http.unit.cases.model;

import io.thoughtware.beans.annotation.Mapper;
import io.thoughtware.core.BaseModel;
import io.thoughtware.join.annotation.Join;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

/**
 * 请求体的 模型
 */
@ApiModel
@Mapper(targetName  = "io.thoughtware.teston.test.apix.http.unit.cases.entity.RequestBodyEntity")
@Join
public class RequestBodyUnit extends BaseModel{

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
