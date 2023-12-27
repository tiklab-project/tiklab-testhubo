package io.thoughtware.teston.test.apix.http.perf.cases.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.teston.test.apix.http.scene.cases.model.ApiSceneCase;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.sql.Timestamp;

/**
 * 接口性能场景步骤 模型
 */
@ApiModel
@Mapper
@Join
public class ApiPerfStep extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="apiPerf",desc="所属apiPerf",required = true)
    @Mappings({
            @Mapping(source = "apiPerf.id",target = "apiPerfId")
    })
    @JoinQuery(key = "id")
    private ApiPerfCase apiPerf;

    @ApiProperty(name="apiScene",desc="所属apiScene",required = true)
    @Mappings({
            @Mapping(source = "apiScene.id",target = "apiSceneId")
    })
    @JoinQuery(key = "id")
    private ApiSceneCase apiScene;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ApiPerfCase getApiPerf() {
        return apiPerf;
    }

    public void setApiPerf(ApiPerfCase apiPerf) {
        this.apiPerf = apiPerf;
    }

    public ApiSceneCase getApiScene() {
        return apiScene;
    }

    public void setApiScene(ApiSceneCase apiScene) {
        this.apiScene = apiScene;
    }


    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
