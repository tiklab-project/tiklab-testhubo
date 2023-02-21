package net.tiklab.teston.functest.unittest.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.beans.annotation.Mapper;
import net.tiklab.core.BaseModel;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

@ApiModel
@Mapper(targetAlias = "FuncUnitStepEntity")
public class FuncUnitStep extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="funcUnitId",desc="funcUnitId")
    private java.lang.String funcUnitId;

    @ApiProperty(name="described",desc="desc")
    private java.lang.String described;

    @ApiProperty(name="expect",desc="expect")
    private java.lang.String expect;

    @ApiProperty(name="actual",desc="actual")
    private java.lang.String actual;

    @ApiProperty(name="createTime",desc="createTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.sql.Timestamp createTime;

    @ApiProperty(name="updateTime",desc="updateTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private java.sql.Timestamp updateTime;

    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getFuncUnitId() {
        return funcUnitId;
    }

    public void setFuncUnitId(java.lang.String funcUnitId) {
        this.funcUnitId = funcUnitId;
    }

    public String getDescribed() {
        return described;
    }

    public void setDescribed(String described) {
        this.described = described;
    }

    public java.lang.String getExpect() {
        return expect;
    }

    public void setExpect(java.lang.String expect) {
        this.expect = expect;
    }
    public java.lang.String getActual() {
        return actual;
    }

    public void setActual(java.lang.String actual) {
        this.actual = actual;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
