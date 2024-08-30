package io.thoughtware.testrubo.test.func.entity;

import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * 功能用例下步骤 实体
 */
@Entity
@Table(name="teston_func_unit_step")
public class FuncUnitStepEntity implements Serializable {

    @Id
//    @GeneratorValue(length = 12)
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

}
