package net.tiklab.teston.testplan.entity;


import net.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

@Entity
@Table(name="teston_test_plan_case_instance_bind")
public class TestPlanCaseInstanceBindEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "test_plan_instance_id",length = 32)
    private String testPlanInstanceId;

    @Column(name = "case_instance_id",length = 32)
    private String caseInstanceId;

    @Column(name="name",length = 128)
    private String name;

    @Column(name="test_type",length = 32)
    private String testType;

    @Column(name="case_type",length = 32)
    private String caseType;

    @Column(name="result")
    private Integer result;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestPlanInstanceId() {
        return testPlanInstanceId;
    }

    public void setTestPlanInstanceId(String testPlanInstanceId) {
        this.testPlanInstanceId = testPlanInstanceId;
    }

    public String getCaseInstanceId() {
        return caseInstanceId;
    }

    public void setCaseInstanceId(String caseInstanceId) {
        this.caseInstanceId = caseInstanceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
