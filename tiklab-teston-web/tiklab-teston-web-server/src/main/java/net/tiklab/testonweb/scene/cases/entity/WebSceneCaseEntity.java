package net.tiklab.testonweb.scene.cases.entity;


import net.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

@Entity @Table(name="teston_web_scene")
public class WebSceneCaseEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "testcase_id",length = 32)
    private String testCaseId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
    }
}
