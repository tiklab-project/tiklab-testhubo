package net.tiklab.teston.apix.http.unit.http.cases.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;
import net.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * 响应体 实体
 */
@Entity @Table(name="teston_api_response_body")
public class ResponseResultEntity implements Serializable {

    @Id
    @Column(name = "id",length = 32)
    private String id;

    // 所属接口单元用例
    @Column(name = "api_unit_id",length = 32)
    private String apiUnitId;

    // 响应类型 json、 raw
    @Column(name = "result_type",length = 32)
    private String resultType;

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

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }
}
