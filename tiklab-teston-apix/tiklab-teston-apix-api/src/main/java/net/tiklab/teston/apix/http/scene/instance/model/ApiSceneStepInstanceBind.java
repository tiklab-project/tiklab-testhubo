package net.tiklab.teston.apix.http.scene.instance.model;


import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.join.annotation.JoinQuery;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.teston.apix.http.unit.instance.model.ApiUnitInstance;

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
