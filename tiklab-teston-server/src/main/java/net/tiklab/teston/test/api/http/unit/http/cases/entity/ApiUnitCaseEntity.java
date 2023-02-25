package net.tiklab.teston.test.api.http.unit.http.cases.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.GeneratorValue;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;import net.tiklab.dal.jpa.annotation.Entity;
import net.tiklab.teston.test.testcase.model.TestCase;

import java.io.Serializable;


@Entity
@Table(name="teston_api_unit")
public class ApiUnitCaseEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "testcase_id",length = 32,notNull = true)
    private String testCaseId;

    @Column(name = "path",length = 256)
    private String path;

    @Column(name = "method_type",length = 32)
    private String methodType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestCaseId() {
        return testCaseId;
    }

    public TestCase setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
        return null;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }
}
