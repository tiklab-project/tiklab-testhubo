package io.tiklab.teston.test.apix.http.scene.cases.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.teston.test.apix.http.unit.cases.model.ApiUnitCase;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.sql.Timestamp;

/**
 * 接口场景下绑定的步骤 模型
 */
@ApiModel
@Mapper(targetAlias = "ApiSceneStepEntity")
@Join
public class ApiSceneStep extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;


    @ApiProperty(name="apiScene",desc="场景")
    @Mappings({
            @Mapping(source = "apiScene.id",target = "apiSceneId")
    })
    @JoinQuery(key = "id")
    private ApiSceneCase apiScene;

    @ApiProperty(name="apiUnit",desc="绑定的单元用例")
    @Mappings({
            @Mapping(source = "apiUnit.id",target = "apiUnitId")
    })
    @JoinQuery(key = "id")
    private ApiUnitCase apiUnit;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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