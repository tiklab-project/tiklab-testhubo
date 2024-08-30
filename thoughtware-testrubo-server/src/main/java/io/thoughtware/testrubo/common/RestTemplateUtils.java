package io.thoughtware.testrubo.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.thoughtware.core.exception.ApplicationException;
import io.thoughtware.core.exception.SystemException;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class RestTemplateUtils {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 发起Post请求
     * @param requestUrl 请求地址
     * @param param 请求参数
     * @param tClass 返回类型
     * @return 请求结果
     * @throws ApplicationException 请求失败
     */
    public <T> T requestPost( String requestUrl, MultiValueMap<String, Object> param , Class<T> tClass){

        // 设置 HTTP 头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // 创建请求实体
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(param, headers);

        ResponseEntity<JSONObject> response;
        try {
            response = restTemplate.exchange(requestUrl, HttpMethod.POST, requestEntity, JSONObject.class);
        }catch (ResourceAccessException e){
            boolean timedOut = Objects.requireNonNull(e.getMessage()).contains("Read timed out");
            boolean connectOut = Objects.requireNonNull(e.getMessage()).contains("Connect timed out");
            if (timedOut || connectOut){
                throw new ApplicationException(50001,"请求超时！");
            }
            throw new SystemException(e);
        }

        JSONObject jsonObject = response.getBody();
        return findBody(jsonObject,tClass);
    }

    /**
     * 转换接口请求内容
     * @param jsonObject 接口返回数据
     * @param tClass 转换模型
     * @return 转换后对象
     * @param <T> 对象类型
     */
    private <T> T findBody(JSONObject jsonObject,Class<T> tClass){
        if (Objects.isNull(jsonObject)){
            throw new ApplicationException(50001,"获取接口返回数据为空！");
        }
        Integer code = jsonObject.getInteger("code");
        if (code != 0){
            String msg = jsonObject.getString("msg");
            throw new SystemException("接口请求错误！,Message："+msg);
        }
        JSONObject data = jsonObject.getJSONObject("data");
        if (Objects.isNull(data)){
            return null;
        }
        return JSONObject.parseObject(data.toJSONString(), tClass);
    }

    public <T> List<T> requestPostList( String requestUrl, Object param, Class<T> tClass){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 创建带有头部和请求体的 HttpEntity
        HttpEntity<Object> requestEntity = new HttpEntity<>(param, headers);

        ResponseEntity<JSONObject> response;
        try {
            response = restTemplate.exchange(requestUrl, HttpMethod.POST, requestEntity, JSONObject.class);
        }catch (ResourceAccessException e){
            boolean timedOut = Objects.requireNonNull(e.getMessage()).contains("Read timed out");
            boolean connectOut = Objects.requireNonNull(e.getMessage()).contains("Connect timed out");
            if (timedOut || connectOut){
                throw new ApplicationException(50001,"请求超时！");
            }
            throw new SystemException(e);
        }
        JSONObject jsonObject = response.getBody();
        return findBodyList(jsonObject,tClass);
    }

    /**
     * 转换接口请求内容
     * @param jsonObject 接口返回数据
     * @param tClass 转换模型
     * @return 转换后对象
     * @param <T> 对象类型
     */
    private <T> List<T> findBodyList(JSONObject jsonObject, Class<T> tClass){

        if (Objects.isNull( jsonObject)){
            throw new SystemException("获取返回值为空！");
        }
        Integer code = jsonObject.getInteger("code");
        if (code != 0){
            String msg = jsonObject.getString("msg");
            throw new SystemException("接口请求错误！,Message："+msg);
        }
        JSONArray data = jsonObject.getJSONArray("data");
        if (Objects.isNull(data)){
            return null;
        }
        return data.toJavaList(tClass);
    }



    public <T> Pagination<T> requestPostPage( String requestUrl, Object param, Class<T> tClass){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // 创建带有头部和请求体的 HttpEntity
        HttpEntity<Object> requestEntity = new HttpEntity<>(param, headers);

        ResponseEntity<JSONObject> response;
        try {
            response = restTemplate.exchange(requestUrl, HttpMethod.POST, requestEntity, JSONObject.class);
        }catch (ResourceAccessException e){
            boolean timedOut = Objects.requireNonNull(e.getMessage()).contains("Read timed out");
            boolean connectOut = Objects.requireNonNull(e.getMessage()).contains("Connect timed out");
            if (timedOut || connectOut){
                throw new ApplicationException(50001,"请求超时！");
            }
            throw new SystemException(e);
        }
        JSONObject jsonObject = response.getBody();
        return findBodyPage(jsonObject,tClass);
    }


    private <T> Pagination<T> findBodyPage(JSONObject jsonObject, Class<T> tClass){

        if (Objects.isNull( jsonObject)){
            throw new SystemException("获取返回值为空！");
        }
        Integer code = jsonObject.getInteger("code");
        if (code != 0){
            String msg = jsonObject.getString("msg");
            throw new SystemException("接口请求错误！,Message："+msg);
        }
        JSONObject data = jsonObject.getJSONObject("data");

        if (Objects.isNull(data)){
            throw new SystemException("接口返回为空！");
        }
//        Pagination pagination = data.toJavaObject(Pagination.class);
//
//        return pagination;

        Pagination pagination = JSONObject.parseObject(data.toJSONString(), Pagination.class);
        JSONArray objects = JSONObject.parseArray(pagination.getDataList().toString());

        return PaginationBuilder.build(pagination, objects.toJavaList(tClass));
    }

    /**
     *
     * @param mediaType 请求类型 MediaType.MULTIPART_FORM_DATA --formdata  MediaType.APPLICATION_JSON --json
     * @param headerMap 其他请求头
     * @return 请求头
     */
    public HttpHeaders initHeaders(MediaType mediaType, Map<String,String> headerMap){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(mediaType));
        if (Objects.isNull(headerMap)){
            return headers;
        }
        for (Map.Entry<String, String> header : headerMap.entrySet()) {
            String key = header.getKey();
            String value = header.getValue();
            headers.set(key,value);
        }
        return headers;
    }

}
