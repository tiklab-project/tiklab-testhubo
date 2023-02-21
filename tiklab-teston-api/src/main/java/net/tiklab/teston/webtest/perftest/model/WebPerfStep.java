package net.tiklab.teston.webtest.perftest.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.beans.annotation.Mapping;
import net.tiklab.beans.annotation.Mappings;
import net.tiklab.core.BaseModel;
import net.tiklab.join.annotation.JoinQuery;
import net.tiklab.teston.webtest.scenetest.model.WebSceneCase;
import com.fasterxml.jackson.annotation.JsonFormat;


@ApiModel
@Mapper(targetAlias = "WebPerfStepEntity")
public class WebPerfStep extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="webPerf",desc="场景")
    @Mappings({
            @Mapping(source = "webPerf.id",target = "webPerfId")
    })
    @JoinQuery(key = "id")
    private WebPerfCase webPerf;

    @ApiProperty(name="webScene",desc="绑定的unitcase")
    @Mappings({
            @Mapping(source = "webScene.id",target = "webSceneId")
    })
    @JoinQuery(key = "id")
    private WebSceneCase webScene;

    @ApiProperty(name="createTime",desc="createTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.sql.Timestamp createTime;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }

    public WebPerfCase getWebPerf() {
        return webPerf;
    }

    public void setWebPerf(WebPerfCase webPerf) {
        this.webPerf = webPerf;
    }

    public WebSceneCase getWebScene() {
        return webScene;
    }

    public void setWebScene(WebSceneCase webScene) {
        this.webScene = webScene;
    }

    public java.sql.Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.sql.Timestamp createTime) {
        this.createTime = createTime;
    }
}
