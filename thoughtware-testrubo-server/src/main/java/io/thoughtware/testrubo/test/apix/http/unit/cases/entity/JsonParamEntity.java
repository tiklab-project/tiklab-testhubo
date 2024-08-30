package io.thoughtware.testrubo.test.apix.http.unit.cases.entity;


import io.thoughtware.dal.jpa.annotation.Column;
import io.thoughtware.dal.jpa.annotation.Id;
import io.thoughtware.dal.jpa.annotation.Table;
import io.thoughtware.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * json 实体
 */
@Entity @Table(name="teston_api_json")
public class JsonParamEntity implements Serializable {

    @Id
//    @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 所属接口单元用例
    @Column(name = "api_unit_id",length = 32,notNull = true)
    private String apiUnitId;

    // schema字符串
    @Column(name = "schema_text",length = 2048)
    private String schemaText;


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

    public String getSchemaText() {
        return schemaText;
    }

    public void setSchemaText(String schemaText) {
        this.schemaText = schemaText;
    }
}
