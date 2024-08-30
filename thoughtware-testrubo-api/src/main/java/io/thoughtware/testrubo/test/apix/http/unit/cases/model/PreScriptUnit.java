package io.thoughtware.testrubo.test.apix.http.unit.cases.model;

import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

/**
 *
 */
@ApiModel
@Mapper(targetName = "io.thoughtware.testrubo.test.apix.http.unit.cases.entity.PreScriptEntity")
@Join
public class PreScriptUnit extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @NotNull
    @ApiProperty(name="apiUnitId",desc="所属接口",required = true)
    private String apiUnitId;

    @NotNull
    @ApiProperty(name="scriptex",desc="脚本定义",required = true)
    private String scriptex;

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

    public String getScriptex() {
        return scriptex;
    }

    public void setScriptex(String scriptex) {
        this.scriptex = scriptex;
    }
}
