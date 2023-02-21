package net.tiklab.teston.apptest.perftest.entity;


import net.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="teston_app_perf_step")
public class AppPerfStepEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "app_perf_id",length = 32)
    private String appPerfId;

    @Column(name = "app_scene_id",length = 32)
    private String appSceneId;

    @Column(name = "create_time")
    private Timestamp createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppPerfId() {
        return appPerfId;
    }

    public void setAppPerfId(String appPerfId) {
        this.appPerfId = appPerfId;
    }

    public String getAppSceneId() {
        return appSceneId;
    }

    public void setAppSceneId(String appSceneId) {
        this.appSceneId = appSceneId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
