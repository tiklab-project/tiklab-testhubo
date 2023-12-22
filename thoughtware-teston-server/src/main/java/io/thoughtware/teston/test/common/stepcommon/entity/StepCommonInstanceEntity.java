package io.thoughtware.teston.test.common.stepcommon.entity;

import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 步骤公共历史 实体
 */
@Entity
@Table(name="teston_case_step_instance_common")
public class StepCommonInstanceEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    // 所属断言
    @Column(name = "instance_id")
    private String instanceId;

    // 类型
    @Column(name = "type")
    private String type;

    // 排序
    @Column(name = "result")
    private Integer result;

    // 排序
    @Column(name = "sort")
    private Integer sort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
