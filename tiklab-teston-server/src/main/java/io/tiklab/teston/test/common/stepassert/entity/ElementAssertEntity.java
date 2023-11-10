package io.tiklab.teston.test.common.stepassert.entity;

import io.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 元素断言 实体
 */
@Entity
@Table(name="teston_assert_element")
public class ElementAssertEntity implements Serializable {

    @Id
//    @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 所属断言
    @Column(name = "assert_id")
    private String assertId;

    // 定位器
    @Column(name = "location")
    private String location;

    // 定位器
    @Column(name = "location_value")
    private String locationValue;

    // 定位器
    @Column(name = "element_type")
    private String elementType;

    // 期望值
    @Column(name = "expect")
    private String expect;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssertId() {
        return assertId;
    }

    public void setAssertId(String assertId) {
        this.assertId = assertId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationValue() {
        return locationValue;
    }

    public void setLocationValue(String locationValue) {
        this.locationValue = locationValue;
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }


}
