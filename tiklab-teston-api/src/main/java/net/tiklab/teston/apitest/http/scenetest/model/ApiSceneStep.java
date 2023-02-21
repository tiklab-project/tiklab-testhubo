package net.tiklab.teston.apitest.http.scenetest.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.join.annotation.JoinQuery;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitCase;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

@ApiModel
@Mapper(targetAlias = "ApiSceneStepEntity")
@Join
public class ApiSceneStep extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;


    @ApiProperty(name="apiScene",desc="场景")
    @Mappings({
            @Mapping(source = "apiScene.id",target = "apiSceneId")
    })
    @JoinQuery(key = "id")
    private ApiSceneCase apiScene;

    @ApiProperty(name="apiUnit",desc="绑定的unitcase")
    @Mappings({
            @Mapping(source = "apiUnit.id",target = "apiUnitId")
    })
    @JoinQuery(key = "id")
    private ApiUnitCase apiUnit;

    @ApiProperty(name="createTime",desc="createTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.sql.Timestamp createTime;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public ApiSceneCase getApiScene() {
        return apiScene;
    }

    public void setApiScene(ApiSceneCase apiScene) {
        this.apiScene = apiScene;
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
}
