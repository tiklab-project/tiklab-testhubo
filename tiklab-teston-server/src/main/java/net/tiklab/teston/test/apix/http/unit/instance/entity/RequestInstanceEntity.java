package net.tiklab.teston.test.apix.http.unit.instance.entity;


import net.tiklab.dal.jpa.annotation.Column;
import net.tiklab.dal.jpa.annotation.Id;
import net.tiklab.dal.jpa.annotation.Table;import net.tiklab.dal.jpa.annotation.Entity;

import java.io.Serializable;

/**
 * 接口单元
 * http协议
 * 请求数据实例 实体
 */
@Entity @Table(name="teston_api_request_instance")
public class RequestInstanceEntity implements Serializable {
    @Id
    @Column(name = "id",length = 32)
    private String id;

    // 所属接口单元用例历史实例
    @Column(name = "api_unit_instance_id",length = 32)
    private String apiUnitInstanceId;

    // 请求地址
    @Column(name = "request_url",length = 2048)
    private String requestUrl;

    // 请求类型
    @Column(name = "request_type",length = 32)
    private String requestType;

    // 请求头
    @Column(name = "request_header",length = 2048)
    private String requestHeader;

    // 请求体
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
