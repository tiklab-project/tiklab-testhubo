package io.tiklab.teston.test.apix.http.scene.instance.model;


import io.tiklab.teston.test.apix.http.unit.instance.model.ApiUnitInstance;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

/**
 * 接口场景下步骤的实例公共表 模型
 */
@ApiModel
@Mapper(targetAlias = "ApiSceneStepInstanceBindEntity")
@Join
public class ApiSceneStepInstanceBind extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="apiSceneInstanceId",desc="接口场景历史id")
    private String apiSceneInstanceId;

    @ApiProperty(name="apiUnitInstance",desc="接口单元历史id")
    @Mappings({
            @Mapping(source = "apiUnitInstance.id",target = "apiUnitInstanceId")
    })
    @JoinQuery(key = "id")
    private ApiUnitInstance apiUnitInstance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getApiSceneInstanceId() {
        return apiSceneInstanceId;
    }

    public void setApiSceneInstanceId(String apiSceneInstanceId) {
        this.apiSceneInstanceId = apiSceneInstanceId;
    }

    public ApiUnitInstance getApiUnitInstance() {
        return apiUnitInstance;
    }

    public void setApiUnitInstance(ApiUnitInstance apiUnitInstance) {
        this.apiUnitInstance = apiUnitInstance;
    }
}
