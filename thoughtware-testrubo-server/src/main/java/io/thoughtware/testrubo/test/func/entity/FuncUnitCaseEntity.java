package io.thoughtware.testrubo.test.func.entity;

import io.thoughtware.dal.jpa.annotation.*;

/**
 * 功能用例 实体
 */
@Entity
@Table(name="teston_func_unit")
public class FuncUnitCaseEntity {
    @Id
     @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 所属测试用例
    @Column(name = "testcase_id" )
    private String  testCaseId;


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
