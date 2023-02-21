package net.tiklab.teston.apptest.perftest.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.JoinQuery;
import net.tiklab.teston.apptest.scenetest.model.AppSceneCase;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;


@ApiModel
@Mapper(targetAlias = "AppPerfStepEntity")
//@Join
public class AppPerfStep extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="appPerf",desc="场景")
    @Mappings({
            @Mapping(source = "appPerf.id",target = "appPerfId")
    })
    @JoinQuery(key = "id")
    private AppPerfCase appPerf;

    @ApiProperty(name="appScene",desc="绑定的unitcase")
    @Mappings({
            @Mapping(source = "appScene.id",target = "appSceneId")
    })
    @JoinQuery(key = "id")
    private AppSceneCase appScene;

    @ApiProperty(name="createTime",desc="createTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.sql.Timestamp createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AppPerfCase getAppPerf() {
        return appPerf;
    }

    public void setAppPerf(AppPerfCase appPerf) {
        this.appPerf = appPerf;
    }

    public AppSceneCase getAppScene() {
        return appScene;
    }

    public void setAppScene(AppSceneCase appScene) {
        this.appScene = appScene;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
