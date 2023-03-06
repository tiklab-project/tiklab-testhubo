package net.tiklab.teston.apix.http.unit.cases.service;

import net.tiklab.core.page.PaginationBuilder;


import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.apix.http.unit.cases.dao.PreScriptDao;
import net.tiklab.teston.apix.http.unit.cases.entity.PreScriptEntity;
import net.tiklab.teston.apix.http.unit.cases.model.PreScript;
import net.tiklab.teston.apix.http.unit.cases.model.PreScriptQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 前置脚本 服务
*/
@Service
public class PreScriptServiceImpl implements PreScriptService {

    @Autowired
    PreScriptDao preScriptDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createPreScript(@NotNull @Valid PreScript preScript) {
        PreScriptEntity preScriptEntity = BeanMapper.map(preScript, PreScriptEntity.class);

        return preScriptDao.createPreScript(preScriptEntity);
    }

    @Override
    public void updatePreScript(@NotNull @Valid PreScript preScript) {
        PreScriptEntity preScriptEntity = BeanMapper.map(preScript, PreScriptEntity.class);

        preScriptDao.updatePreScript(preScriptEntity);
    }

    @Override
    public void deletePreScript(@NotNull String id) {
        preScriptDao.deletePreScript(id);
    }

    @Override
    public PreScript findOne(String id) {
        PreScriptEntity preScriptEntity = preScriptDao.findPreScript(id);

        PreScript preScript = BeanMapper.map(preScriptEntity, PreScript.class);
        return preScript;
    }

    @Override
    public List<PreScript> findList(List<String> idList) {
        List<PreScriptEntity> preScriptEntityList =  preScriptDao.findPreScriptList(idList);

        List<PreScript> preScriptList =  BeanMapper.mapList(preScriptEntityList,PreScript.class);
        return preScriptList;
    }

    @Override
    public PreScript findPreScript(@NotNull String id) {
        PreScript preScript = findOne(id);

        joinTemplate.joinQuery(preScript);
        return preScript;
    }

    @Override
    public List<PreScript> findAllPreScript() {
        List<PreScriptEntity> preScriptEntityList =  preScriptDao.findAllPreScript();

        List<PreScript> preScriptList =  BeanMapper.mapList(preScriptEntityList,PreScript.class);

        joinTemplate.joinQuery(preScriptList);
        return preScriptList;
    }

    @Override
    public List<PreScript> findPreScriptList(PreScriptQuery preScriptQuery) {
        List<PreScriptEntity> preScriptEntityList = preScriptDao.findPreScriptList(preScriptQuery);

        List<PreScript> preScriptList = BeanMapper.mapList(preScriptEntityList,PreScript.class);

        joinTemplate.joinQuery(preScriptList);

        return preScriptList;
    }

    @Override
    public Pagination<PreScript> findPreScriptPage(PreScriptQuery preScriptQuery) {

        Pagination<PreScriptEntity>  pagination = preScriptDao.findPreScriptPage(preScriptQuery);

        List<PreScript> preScriptList = BeanMapper.mapList(pagination.getDataList(),PreScript.class);

        joinTemplate.joinQuery(preScriptList);

        return PaginationBuilder.build(pagination,preScriptList);
    }
}