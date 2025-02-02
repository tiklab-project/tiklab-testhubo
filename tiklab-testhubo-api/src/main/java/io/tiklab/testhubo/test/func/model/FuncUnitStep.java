package io.tiklab.testhubo.test.func.model;

import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;


/**
 * 功能用例下步骤 模型
 */
@ApiModel
@Mapper
public class FuncUnitStep extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="funcUnitId",desc="所属功能用例")
    private String funcUnitId;

    @ApiProperty(name="described",desc="功能描述")
    private String described;

    @ApiProperty(name="expect",desc="期望")
    private String expect;

    @ApiProperty(name="actual",desc="实际结果")
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
