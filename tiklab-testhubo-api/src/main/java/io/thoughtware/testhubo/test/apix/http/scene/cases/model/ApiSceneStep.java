package io.thoughtware.testhubo.test.apix.http.scene.cases.model;

import io.thoughtware.testhubo.test.apix.http.unit.cases.model.ApiUnitCase;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.testhubo.test.apix.http.unit.cases.model.ApiUnitCaseDataConstruction;

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

    private ApiUnitCaseDataConstruction apiUnitCaseDataConstruction;

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

    public ApiUnitCaseDataConstruction getApiUnitCaseDataConstruction() {
        return apiUnitCaseDataConstruction;
    }

    public void setApiUnitCaseDataConstruction(ApiUnitCaseDataConstruction apiUnitCaseDataConstruction) {
        this.apiUnitCaseDataConstruction = apiUnitCaseDataConstruction;
    }
}
