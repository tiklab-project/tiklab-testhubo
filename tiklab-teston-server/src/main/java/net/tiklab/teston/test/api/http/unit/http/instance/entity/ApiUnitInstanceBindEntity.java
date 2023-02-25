package net.tiklab.teston.test.api.http.unit.http.instance.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.Entity;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="teston_api_unit_instance_bind")
public class ApiUnitInstanceBindEntity implements Serializable {

    @Id
//    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "api_unit_id",length = 32)
    private String apiUnitId;

    @Column(name = "api_unit_instance_id",length = 32)
    private String apiUnitInstanceId;

    @Column(name = "create_time")
    private Timestamp createTime;

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

    public String getApiUnitInstanceId() {
        return apiUnitInstanceId;
    }

    public void setApiUnitInstanceId(String apiUnitInstanceId) {
        this.apiUnitInstanceId = apiUnitInstanceId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
