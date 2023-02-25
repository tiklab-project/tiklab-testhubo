package net.tiklab.testonapix.http.perf.cases.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.Join;
import net.tiklab.join.annotation.JoinQuery;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.testonapix.http.scene.cases.model.ApiSceneCase;

import java.sql.Timestamp;

@ApiModel
@Mapper(targetAlias = "ApiPerfStepEntity")
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

    @ApiProperty(name="createTime",desc="createTime")
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
