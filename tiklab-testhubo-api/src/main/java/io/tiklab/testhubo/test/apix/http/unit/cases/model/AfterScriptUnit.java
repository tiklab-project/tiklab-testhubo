package io.tiklab.testhubo.test.apix.http.unit.cases.model;

import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.toolkit.beans.annotation.Mapping;
import io.tiklab.toolkit.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.toolkit.join.annotation.Join;
import io.tiklab.toolkit.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

/**
 * 后置脚本 模型
 */
@ApiModel
@Mapper(targetName  = "io.tiklab.testhubo.test.apix.http.unit.cases.entity.AfterScriptEntity")
@Join
public class AfterScriptUnit extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @NotNull
    @ApiProperty(name="apiUnitId",desc="所属接口",required = true)
    private String apiUnitId;


    @NotNull
    @ApiProperty(name="scriptex",desc="后置脚本定义",required = true)
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
