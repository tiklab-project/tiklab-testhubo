package io.thoughtware.teston.support.environment.service;

import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.teston.support.environment.entity.WebEnvEntity;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.teston.support.environment.dao.WebEnvDao;
import io.thoughtware.teston.support.environment.model.WebEnv;
import io.thoughtware.teston.support.environment.model.WebEnvQuery;

import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* Web环境 服务
*/
@Service
@Exporter
public class WebEnvServiceImpl implements WebEnvService {

    @Autowired
    WebEnvDao webEnvDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createWebEnv(@NotNull @Valid WebEnv webEnv) {
        WebEnvEntity webEnvEntity = BeanMapper.map(webEnv, WebEnvEntity.class);

        return webEnvDao.createWebEnv(webEnvEntity);
    }

    @Override
    public void updateWebEnv(@NotNull @Valid WebEnv webEnv) {
        WebEnvEntity webEnvEntity = BeanMapper.map(webEnv, WebEnvEntity.class);

        webEnvDao.updateWebEnv(webEnvEntity);
    }

    @Override
    public void deleteWebEnv(@NotNull String id) {
        webEnvDao.deleteWebEnv(id);
    }

    @Override
    public void deleteAllWebEnv(String repositoryId) {
        DeleteCondition deleteCondition = DeleteBuilders.createDelete(WebEnvEntity.class)
                .eq("repositoryId", repositoryId)
                .get();
        webEnvDao.deleteWebEnv(deleteCondition);
    }


    @Override
    public WebEnv findOne(String id) {
        WebEnvEntity webEnvEntity = webEnvDao.findWebEnv(id);

        WebEnv webEnv = BeanMapper.map(webEnvEntity, WebEnv.class);
        return webEnv;
    }

    @Override
    public List<WebEnv> findList(List<String> idList) {
        List<WebEnvEntity> webEnvEntityList =  webEnvDao.findWebEnvList(idList);

        List<WebEnv> webEnvList =  BeanMapper.mapList(webEnvEntityList,WebEnv.class);
        return webEnvList;
    }

    @Override
    public WebEnv findWebEnv(@NotNull String id) {
        WebEnv webEnv = findOne(id);

        joinTemplate.joinQuery(webEnv);

        return webEnv;
    }

    @Override
    public List<WebEnv> findAllWebEnv() {
        List<WebEnvEntity> webEnvEntityList =  webEnvDao.findAllWebEnv();

        List<WebEnv> webEnvList =  BeanMapper.mapList(webEnvEntityList,WebEnv.class);

        joinTemplate.joinQuery(webEnvList);

        return webEnvList;
    }

    @Override
    public List<WebEnv> findWebEnvList(WebEnvQuery webEnvQuery) {
        List<WebEnvEntity> webEnvEntityList = webEnvDao.findWebEnvList(webEnvQuery);

        List<WebEnv> webEnvList = BeanMapper.mapList(webEnvEntityList,WebEnv.class);

        joinTemplate.joinQuery(webEnvList);

        return webEnvList;
    }

    @Override
    public Pagination<WebEnv> findWebEnvPage(WebEnvQuery webEnvQuery) {
        Pagination<WebEnvEntity>  pagination = webEnvDao.findWebEnvPage(webEnvQuery);

        List<WebEnv> webEnvList = BeanMapper.mapList(pagination.getDataList(),WebEnv.class);

        joinTemplate.joinQuery(webEnvList);

        return PaginationBuilder.build(pagination,webEnvList);
    }
}