package net.tiklab.teston.apitest.http.scenetest.entity;


import net.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

@Entity
@Table(name="teston_api_scene_step_instance_bind")
public class ApiSceneStepInstanceBindEntity implements Serializable {

    @Id
//    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "api_scene_instance_id",length = 32)
    private String apiSceneInstanceId;

    @Column(name = "api_unit_instance_id",length = 32)
    private String apiUnitInstanceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiSceneInstanceId() {
        return apiSceneInstanceId;
    }

    public void setApiSceneInstanceId(String apiSceneInstanceId) {
        this.apiSceneInstanceId = apiSceneInstanceId;
    }

    public String getApiUnitInstanceId() {
        return apiUnitInstanceId;
    }

    public void setApiUnitInstanceId(String apiUnitInstanceId) {
        this.apiUnitInstanceId = apiUnitInstanceId;
    }
}
