package io.thoughtware.teston.test.apix.http.unit.cases.model;


import io.thoughtware.beans.annotation.Mapper;
import io.thoughtware.core.BaseModel;
import io.thoughtware.join.annotation.Join;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

@ApiModel
@Mapper(targetName  = "io.thoughtware.teston.test.apix.http.unit.cases.entity.JsonParamEntity")
@Join
public class JsonParamUnit extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="apiUnitId",desc="所属接口")
    private String apiUnitId;

    @ApiProperty(name="schemaText",desc="所属接口")
    private String schemaText;

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

    public String getSchemaText() {
        return schemaText;
    }

    public void setSchemaText(String schemaText) {
        this.schemaText = schemaText;
    }
}
