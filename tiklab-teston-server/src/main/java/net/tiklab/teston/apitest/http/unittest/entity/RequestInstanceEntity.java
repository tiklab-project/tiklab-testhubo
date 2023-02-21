package net.tiklab.teston.apitest.http.unittest.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.GeneratorValue;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;import net.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

@Entity @Table(name="teston_api_request_instance")
public class RequestInstanceEntity implements Serializable {
    @Id
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "api_unit_instance_id",length = 32)
    private String apiUnitInstanceId;

    @Column(name = "request_url",length = 2048)
    private String requestUrl;

    @Column(name = "request_type",length = 32)
    private String requestType;

    @Column(name = "request_header",length = 2048)
    private String requestHeader;

    @Column(name = "request_param",length = 2048)
    private String requestParam;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiUnitInstanceId() {
        return apiUnitInstanceId;
    }

    public void setApiUnitInstanceId(String apiUnitInstanceId) {
        this.apiUnitInstanceId = apiUnitInstanceId;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
