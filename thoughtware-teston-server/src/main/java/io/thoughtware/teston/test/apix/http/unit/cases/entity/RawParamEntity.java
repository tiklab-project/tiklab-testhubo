package io.thoughtware.teston.test.apix.http.unit.cases.entity;


import io.thoughtware.dal.jpa.annotation.Column;
import io.thoughtware.dal.jpa.annotation.Id;
import io.thoughtware.dal.jpa.annotation.Table;import io.thoughtware.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * raw 实体
 */
@Entity @Table(name="teston_api_raw")
public class RawParamEntity implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    // 所属接口单元用例
    @Column(name = "api_unit_id",length = 32,notNull = true)
    private String apiUnitId;

    // raw文本
    @Column(name = "raw",length = 2048,notNull = true)
    private String raw;

    // raw中类型
    @Column(name = "type",length = 32,notNull = true)
    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }
}
