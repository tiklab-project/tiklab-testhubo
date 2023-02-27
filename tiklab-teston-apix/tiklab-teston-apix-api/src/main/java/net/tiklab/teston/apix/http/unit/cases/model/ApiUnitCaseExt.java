package net.tiklab.teston.apix.http.unit.cases.model;

import net.tiklab.core.BaseModel;
import net.tiklab.postin.annotation.ApiModel;

import java.util.Map;

@ApiModel
public class ApiUnitCaseExt extends BaseModel{

    //请求头
    private Map headerMap;

    //
    private String query;

    //mediaType
    private Map mediaTypeMap;

    //封装请求体数据
    private String body;

    //调用前置脚本
    private Object preScript;

    //调用后置脚本
    private Object afterScript;


    public Map getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map headerMap) {
        this.headerMap = headerMap;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Map getMediaTypeMap() {
        return mediaTypeMap;
    }

    public void setMediaTypeMap(Map mediaTypeMap) {
        this.mediaTypeMap = mediaTypeMap;
    }

    public Object getAfterScript() {
        return afterScript;
    }

    public void setAfterScript(Object afterScript) {
        this.afterScript = afterScript;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Object getPreScript() {
        return preScript;
    }

    public void setPreScript(Object preScript) {
        this.preScript = preScript;
    }
}
