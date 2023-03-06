package net.tiklab.teston.test.apix.http.unit.cases.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;import net.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * 后置脚本 实体
 */
@Entity @Table(name="teston_api_after_script")
public class AfterScriptEntity implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    // 所属接口
    @Column(name = "api_unit_id",length = 32,notNull = true)
    private String apiUnitId;

    // 后置脚本定义
    @Column(name = "script",length = 2048,notNull = true)
    private String scriptex;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiUnitId() {
        return apiUnitId;
    }

    public void setApiUnitId(String apiUnitId) {
        this.apiUnitId = apiUnitId;
    }

    public String getScriptex() {
        return scriptex;
    }

    public void setScriptex(String scriptex) {
        this.scriptex = scriptex;
    }


}
