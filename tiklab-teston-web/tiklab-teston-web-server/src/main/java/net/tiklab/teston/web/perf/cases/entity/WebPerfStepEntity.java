package net.tiklab.teston.web.perf.cases.entity;


import net.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * web性能下绑定的场景 实体
 */
@Entity
@Table(name="teston_web_perf_step")
public class WebPerfStepEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    // 所属web性能
    @Column(name = "web_perf_id",length = 32)
    private String webPerfId;

    // 绑定的web场景
    @Column(name = "web_scene_id",length = 32)
    private String webSceneId;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getWebPerfId() {
        return webPerfId;
    }

    public void setWebPerfId(String webPerfId) {
        this.webPerfId = webPerfId;
    }

    public String getWebSceneId() {
        return webSceneId;
    }

    public void setWebSceneId(String webSceneId) {
        this.webSceneId = webSceneId;
    }
}
