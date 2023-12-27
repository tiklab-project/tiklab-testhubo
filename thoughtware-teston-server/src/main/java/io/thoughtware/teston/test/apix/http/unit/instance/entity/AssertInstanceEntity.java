package io.thoughtware.teston.test.apix.http.unit.instance.entity;


import io.thoughtware.dal.jpa.annotation.Column;
import io.thoughtware.dal.jpa.annotation.GeneratorValue;
import io.thoughtware.dal.jpa.annotation.Id;
import io.thoughtware.dal.jpa.annotation.Table;import io.thoughtware.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * 接口单元
 * http协议
 * 断言实例 实体
 */
@Entity @Table(name="teston_api_assert_instance")
public class AssertInstanceEntity implements Serializable {

    @Id
    @GeneratorValue(length = 12)
    @Column(name = "id")
    private String id;

    //测试实例id
    @Column(name = "instance_id")
    private String instanceId;

    //断言来源类型
    @Column(name = "source")
    private Integer source;

    //实际值
    @Column(name = "actual_result")
    private String actualResult;

    //比较符
    @Column(name = "comparator")
    private String comparator;

    //比较的值
    @Column(name = "value")
    private String value;

    //断言结果, 0失败  1成功
    @Column(name = "result")
    private Integer result;

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

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getActualResult() {
        return actualResult;
    }

    public void setActualResult(String actualResult) {
        this.actualResult = actualResult;
    }

    public String getComparator() {
        return comparator;
    }

    public void setComparator(String comparator) {
        this.comparator = comparator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
