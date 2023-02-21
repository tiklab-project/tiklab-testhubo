package net.tiklab.teston.testjob.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.GeneratorValue;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;import net.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

@Entity @Table(name="teston_quartz_testcase")
public class QuartzTestcaseEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    //定时器主表id
    @Column(name = "quartz_master_id",length = 32,notNull = true)
    private String quartzMasterId;

    //用例id
    @Column(name = "testcase_id",length = 32,notNull = true)
    private String testcaseId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuartzMasterId() {
        return quartzMasterId;
    }

    public void setQuartzMasterId(String quartzMasterId) {
        this.quartzMasterId = quartzMasterId;
    }

    public String getTestcaseId() {
        return testcaseId;
    }

    public void setTestcaseId(String testcaseId) {
        this.testcaseId = testcaseId;
    }
}
