package net.tiklab.teston.test.api.http.unit.http.cases.service;

import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.teston.test.api.http.unit.http.cases.dao.AfterScriptDao;
import net.tiklab.teston.test.api.http.unit.cases.service.AfterScriptService;
import net.tiklab.teston.test.api.http.unit.http.cases.entity.AfterScriptEntity;
import net.tiklab.teston.test.api.http.unit.cases.model.AfterScript;
import net.tiklab.teston.test.api.http.unit.cases.model.AfterScriptQuery;

import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* AfterScriptServiceImpl
*/
@Service
public class AfterScriptServiceImpl implements AfterScriptService {

    @Autowired
    AfterScriptDao afterScriptDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAfterScript(@NotNull @Valid AfterScript afterScript) {
        AfterScriptEntity afterScriptEntity = BeanMapper.map(afterScript, AfterScriptEntity.class);

        return afterScriptDao.createAfterScript(afterScriptEntity);
    }

    @Override
    public void updateAfterScript(@NotNull @Valid AfterScript afterScript) {
        AfterScriptEntity afterScriptEntity = BeanMapper.map(afterScript, AfterScriptEntity.class);

        afterScriptDao.updateAfterScript(afterScriptEntity);
    }

    @Override
    public void deleteAfterScript(@NotNull String id) {
        afterScriptDao.deleteAfterScript(id);
    }

    @Override
    public AfterScript findOne(String id) {
        AfterScriptEntity afterScriptEntity = afterScriptDao.findAfterScript(id);

        AfterScript afterScript = BeanMapper.map(afterScriptEntity, AfterScript.class);
        return afterScript;
    }

    @Override
    public List<AfterScript> findList(List<String> idList) {
        List<AfterScriptEntity> afterScriptEntityList =  afterScriptDao.findAfterScriptList(idList);

        List<AfterScript> afterScriptList =  BeanMapper.mapList(afterScriptEntityList,AfterScript.class);
        return afterScriptList;
    }

    @Override
    public AfterScript findAfterScript(@NotNull String id) {
        AfterScript afterScript = findOne(id);

        joinTemplate.joinQuery(afterScript);
        return afterScript;
    }

    @Override
    public List<AfterScript> findAllAfterScript() {
        List<AfterScriptEntity> afterScriptEntityList =  afterScriptDao.findAllAfterScript();

        List<AfterScript> afterScriptList =  BeanMapper.mapList(afterScriptEntityList,AfterScript.class);

        joinTemplate.joinQuery(afterScriptList);
        return afterScriptList;
    }

    @Override
    public List<AfterScript> findAfterScriptList(AfterScriptQuery afterScriptQuery) {
        List<AfterScriptEntity> afterScriptEntityList = afterScriptDao.findAfterScriptList(afterScriptQuery);

        List<AfterScript> afterScriptList = BeanMapper.mapList(afterScriptEntityList,AfterScript.class);

        joinTemplate.joinQuery(afterScriptList);

        return afterScriptList;
    }

    @Override
    public Pagination<AfterScript> findAfterScriptPage(AfterScriptQuery afterScriptQuery) {

        Pagination<AfterScriptEntity>  pagination = afterScriptDao.findAfterScriptPage(afterScriptQuery);

        List<AfterScript> afterScriptList = BeanMapper.mapList(pagination.getDataList(),AfterScript.class);

        joinTemplate.joinQuery(afterScriptList);

        return PaginationBuilder.build(pagination,afterScriptList);
    }
}