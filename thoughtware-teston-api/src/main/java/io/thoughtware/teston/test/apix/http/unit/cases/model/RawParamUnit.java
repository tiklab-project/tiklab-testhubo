package io.thoughtware.teston.test.apix.http.unit.cases.model;
import io.thoughtware.beans.annotation.Mapper;
import io.thoughtware.beans.annotation.Mapping;
import io.thoughtware.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.join.annotation.Join;
import io.thoughtware.join.annotation.JoinQuery;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

/**
 * raw 模型
 */
@ApiModel
@Mapper(targetName  = "io.thoughtware.teston.test.apix.http.unit.cases.entity.RawParamEntity")
@Join
public class RawParamUnit extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @NotNull
    @ApiProperty(name="apiUnit",desc="所属接口",required = true)
    @Mappings({
            @Mapping(source = "apiUnit.id",target = "apiUnitId")
    })
    @JoinQuery(key = "id")
    private ApiUnitCase apiUnit;

    @NotNull
    @ApiProperty(name="raw",desc="自定义参数",required = true)
    private String raw;

    @NotNull
    @ApiProperty(name = "type",desc = "raw中类型",required = true)
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ApiUnitCase getApiUnit() {
        return apiUnit;
    }

    public void setApiUnit(ApiUnitCase apiUnit) {
        this.apiUnit = apiUnit;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}