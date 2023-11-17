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
@Mapper
@Join
public class ApiSceneStep extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="apiSceneId",desc="场景")
    private String apiSceneId;

    @ApiProperty(name="apiUnit",desc="绑定的单元用例")
    @Mappings({
            @Mapping(source = "apiUnit.id",target = "apiUnitId")
    })
    @JoinQuery(key = "id")
    private ApiUnitCase apiUnit;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiSceneId() {
        return apiSceneId;
    }

    public void setApiSceneId(String apiSceneId) {
        this.apiSceneId = apiSceneId;
    }

    public ApiUnitCase getApiUnit() {
        return apiUnit;
    }

    public void setApiUnit(ApiUnitCase apiUnit) {
        this.apiUnit = apiUnit;
    }

}
