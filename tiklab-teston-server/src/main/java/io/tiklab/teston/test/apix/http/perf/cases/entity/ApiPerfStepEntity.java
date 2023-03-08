package io.tiklab.teston.test.apix.http.perf.cases.entity;


import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 接口性能下场景步骤 实体
 */
@Entity
@Table(name="teston_api_perf_step")
public class ApiPerfStepEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    // 所属接口性能
    @Column(name = "api_perf_id",length = 32)
    private String apiPerfId;

    // 绑定的接口场景
    @Column(name = "api_scene_id",length = 32)
    private String apiSceneId;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

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

    public String getApiPerfId() {
        return apiPerfId;
    }

    public void setApiPerfId(String apiPerfId) {
        this.apiPerfId = apiPerfId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
