package io.tiklab.teston.test.common.stepassert.model;

import io.tiklab.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.join.annotation.Join;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

/**
 *  元素断言 模型
 */
@ApiModel
@Mapper(targetName  = "io.tiklab.teston.test.common.stepassert.entity.ElementAssertEntity")
@Join
public class ElementAssert extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="assertId",desc="assertId")
    private String assertId;

    @ApiProperty(name="location",desc="定位器")
    private String location;

    @ApiProperty(name="locationValue",desc="定位值")
    private String locationValue;

    @ApiProperty(name="elementType",desc="元素类型")
    private Integer elementType;

    @ApiProperty(name="expect",desc="期望值")
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

    public Integer getElementType() {
        return elementType;
    }

    public void setElementType(Integer elementType) {
        this.elementType = elementType;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }
}
