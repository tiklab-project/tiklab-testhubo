package io.tiklab.teston.test.app.perf.cases.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.tiklab.beans.annotation.Mapper;
import io.tiklab.beans.annotation.Mapping;
import io.tiklab.beans.annotation.Mappings;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.join.annotation.JoinQuery;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.teston.test.app.scene.cases.model.AppSceneCase;

import java.sql.Timestamp;

/**
 * app性能测试用例下绑定的步骤 模型
 */
@ApiModel
@Mapper(targetAlias = "AppPerfStepEntity")
@Join
public class AppPerfStep extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="appPerf",desc="场景")
    @Mappings({
            @Mapping(source = "appPerf.id",target = "appPerfId")
    })
    @JoinQuery(key = "id")
    private AppPerfCase appPerf;

    @ApiProperty(name="appScene",desc="绑定的接口场景")
    @Mappings({
            @Mapping(source = "appScene.id",target = "appSceneId")
    })
    @JoinQuery(key = "id")
    private AppSceneCase appScene;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp createTime;

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
