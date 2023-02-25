package net.tiklab.teston.test.api.http.unit.http.instance.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.GeneratorValue;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;import net.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

@Entity @Table(name="teston_api_assert_instance")
public class AssertInstanceEntity implements Serializable {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    //测试实例id
    @Column(name = "instance_id",length = 32,notNull = true)
    private String instanceId;

    //断言来源类型
    @Column(name = "source")
    private Integer source;

    //属性名称
    @Column(name = "property_name",length = 64)
    private String propertyName;

    //数据类型
    @Column(name = "data_type",length = 32)
    private String dataType;

    //比较符
    @Column(name = "comparator",length = 32,notNull = true)
    private String comparator;

    //值
    @Column(name = "value",length = 128)
    private String value;

    //断言结果, 0失败  1成功
    @Column(name = "result",length = 4)
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

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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
