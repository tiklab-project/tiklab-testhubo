package net.tiklab.teston.test.apiscene.cases.entity;


import net.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="teston_api_scene_step")
public class ApiSceneStepEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "api_scene_id",length = 32)
    private String apiSceneId;

    @Column(name = "api_unit_id",length = 32)
    private String apiUnitId;

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

    public String getApiUnitId() {
        return apiUnitId;
    }

    public void setApiUnitId(String apiUnitId) {
        this.apiUnitId = apiUnitId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
