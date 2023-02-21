package net.tiklab.teston.functest.unittest.entity;

import net.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="teston_func_unit_step")
public class FuncUnitStepEntity implements Serializable {
    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "func_unit_id",length = 32)
    private String funcUnitId;

    @Column(name = "described",length =512)
    private String described;

    @Column(name = "expect",length = 512)
    private String expect;

    @Column(name = "actual",length = 512)
    private String actual;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "update_time")
    private Timestamp updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFuncUnitId() {
        return funcUnitId;
    }

    public void setFuncUnitId(String funcUnitId) {
        this.funcUnitId = funcUnitId;
    }

    public String getDescribed() {
        return described;
    }

    public void setDescribed(String described) {
        this.described = described;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
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
