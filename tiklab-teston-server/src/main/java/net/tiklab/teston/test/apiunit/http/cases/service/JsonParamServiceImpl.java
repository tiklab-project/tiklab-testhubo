package net.tiklab.teston.test.apiunit.http.cases.service;

import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.teston.test.apiunit.http.cases.service.JsonParamService;
import net.tiklab.teston.test.apiunit.http.cases.dao.JsonParamDao;
import net.tiklab.teston.test.apiunit.http.cases.entity.JsonParamEntity;
import net.tiklab.teston.test.apiunit.http.cases.model.JsonParam;
import net.tiklab.teston.test.apiunit.http.cases.model.JsonParamQuery;

import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.ObjectUtils;

/**
* JsonParamServiceImpl
*/
@Service
public class JsonParamServiceImpl implements JsonParamService {

    @Autowired
    JsonParamDao jsonParamDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createJsonParam(@NotNull @Valid JsonParam jsonParam) {
        JsonParamEntity jsonParamEntity = BeanMapper.map(jsonParam, JsonParamEntity.class);

        return jsonParamDao.createJsonParam(jsonParamEntity);
    }

    @Override
    public void updateJsonParam(@NotNull @Valid JsonParam jsonParam) {
        JsonParamEntity jsonParamEntity = BeanMapper.map(jsonParam, JsonParamEntity.class);

        jsonParamDao.updateJsonParam(jsonParamEntity);
    }

    @Override
    public void deleteJsonParam(@NotNull String id) {
        jsonParamDao.deleteJsonParam(id);
    }

    @Override
    public JsonParam findOne(String id) {
        JsonParamEntity jsonParamEntity = jsonParamDao.findJsonParam(id);

        JsonParam jsonParam = BeanMapper.map(jsonParamEntity, JsonParam.class);
        return jsonParam;
    }

    @Override
    public List<JsonParam> findList(List<String> idList) {
        List<JsonParamEntity> jsonParamEntityList =  jsonParamDao.findJsonParamList(idList);

        List<JsonParam> jsonParamList =  BeanMapper.mapList(jsonParamEntityList,JsonParam.class);
        return jsonParamList;
    }

    @Override
    public JsonParam findJsonParam(@NotNull String id) {
        JsonParam jsonParam = findOne(id);

        joinTemplate.joinQuery(jsonParam);
        return jsonParam;
    }

    @Override
    public List<JsonParam> findAllJsonParam() {
        List<JsonParamEntity> jsonParamEntityList =  jsonParamDao.findAllJsonParam();

        List<JsonParam> jsonParamList =  BeanMapper.mapList(jsonParamEntityList,JsonParam.class);

        joinTemplate.joinQuery(jsonParamList);
        return jsonParamList;
    }

    @Override
    public List<JsonParam> findJsonParamList(JsonParamQuery jsonParamQuery) {
        List<JsonParamEntity> jsonParamEntityList = jsonParamDao.findJsonParamList(jsonParamQuery);

        List<JsonParam> jsonParamList = BeanMapper.mapList(jsonParamEntityList,JsonParam.class);

        joinTemplate.joinQuery(jsonParamList);

        return jsonParamList;
    }

    @Override
    public Pagination<JsonParam> findJsonParamPage(JsonParamQuery jsonParamQuery) {

        Pagination<JsonParamEntity>  pagination = jsonParamDao.findJsonParamPage(jsonParamQuery);

        List<JsonParam> jsonParamList = BeanMapper.mapList(pagination.getDataList(),JsonParam.class);

        joinTemplate.joinQuery(jsonParamList);

        return PaginationBuilder.build(pagination,jsonParamList);
    }

    @Override
    public List<JsonParam> findJsonParamListTree(JsonParamQuery jsonParamQuery) {
        List<JsonParam> matchJsonParamList = this.findJsonParamList(jsonParamQuery);

        //查找第一级参数列表
        List<JsonParam> topJsonParamList = findTopJsonParamList(matchJsonParamList);

        //设置下级节点列表
        if(topJsonParamList != null && topJsonParamList.size() > 0){
            for(JsonParam topJsonParam:topJsonParamList){
                setChildren(matchJsonParamList,topJsonParam);
            }
        }

        return topJsonParamList;
    }

    /**
     * 查找第一级参数列表
     * @param matchJsonParamList
     * @return
     */
    List<JsonParam> findTopJsonParamList(List<JsonParam> matchJsonParamList) {
        List<JsonParam> jsonParamList = matchJsonParamList.stream()
                .filter(jsonParam -> (jsonParam.getParent() == null || jsonParam.getParent().getId() == null))
                .collect(Collectors.toList());
        return jsonParamList;
    }

    /**
     * 设置下级节点列表
     * @param matchJsonParamList
     * @param parentJsonParam
     */
    void setChildren(List<JsonParam> matchJsonParamList,JsonParam parentJsonParam){
        List<JsonParam> childList = matchJsonParamList.stream()
                .filter(jsonParam -> (jsonParam.getParent() != null && !ObjectUtils.isEmpty(jsonParam.getParent().getId()) && jsonParam.getParent().getId().equals(parentJsonParam.getId())))
                .collect(Collectors.toList());

        if(childList != null && childList.size() > 0){
            parentJsonParam.setChildren(childList);

            //设置下级节点列表
            for(JsonParam childJsonParam:childList){
                setChildren(matchJsonParamList,childJsonParam);
            }
        }
    }
}