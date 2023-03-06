package net.tiklab.teston.test.func.entity;

import net.tiklab.dal.jpa.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 功能用例下步骤 实体
 */
@Entity
@Table(name="teston_func_unit_step")
public class FuncUnitStepEntity implements Serializable {
    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    // 所属功能用例
    @Column(name = "func_unit_id",length = 32)
    private String funcUnitId;

    // 功能描述
    @Column(name = "described",length =512)
    private String described;

    // 期望
    @Column(name = "expect",length = 512)
    private String expect;

    // 实际结果
    @Column(name = "actual",length = 512)
    private String actual;

    // 创建时间
    @Column(name = "create_time")
    private Timestamp createTime;

    // 更新时间
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
