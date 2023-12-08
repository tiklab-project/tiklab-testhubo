package io.thoughtware.teston.test.web.perf.cases.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.beans.annotation.Mapper;
import io.thoughtware.beans.annotation.Mapping;
import io.thoughtware.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.join.annotation.JoinQuery;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.teston.test.web.scene.cases.model.WebSceneCase;

/**
 * web性能下绑定的场景 模型
 */
@ApiModel
@Mapper
public class WebPerfStep extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="webPerf",desc="场景")
    @Mappings({
            @Mapping(source = "webPerf.id",target = "webPerfId")
    })
    @JoinQuery(key = "id")
    private WebPerfCase webPerf;

    @ApiProperty(name="webScene",desc="绑定的web场景")
    @Mappings({
            @Mapping(source = "webScene.id",target = "webSceneId")
    })
    @JoinQuery(key = "id")
    private WebSceneCase webScene;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.sql.Timestamp createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
