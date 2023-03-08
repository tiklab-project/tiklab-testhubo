package io.tiklab.teston.test.web.perf.instance.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;

import io.tiklab.teston.test.web.perf.instance.dao.WebPerfInstanceDao;
import io.tiklab.teston.test.web.perf.instance.entity.WebPerfInstanceEntity;
import io.tiklab.teston.test.web.perf.instance.model.WebPerfInstance;
import io.tiklab.teston.test.web.perf.instance.model.WebPerfInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* web性能测试实例 服务
*/
@Service
public class WebPerfInstanceServiceImpl implements WebPerfInstanceService {

    @Autowired
    WebPerfInstanceDao appPerfInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createWebPerfInstance(@NotNull @Valid WebPerfInstance webPerfInstance) {
        WebPerfInstanceEntity webPerfInstanceEntity = BeanMapper.map(webPerfInstance, WebPerfInstanceEntity.class);

        webPerfInstanceEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return appPerfInstanceDao.createWebPerfInstance(webPerfInstanceEntity);
    }

    @Override
    public void updateWebPerfInstance(@NotNull @Valid WebPerfInstance webPerfInstance) {
        WebPerfInstanceEntity webPerfInstanceEntity = BeanMapper.map(webPerfInstance, WebPerfInstanceEntity.class);

        appPerfInstanceDao.updateWebPerfInstance(webPerfInstanceEntity);
    }

    @Override
    public void deleteWebPerfInstance(@NotNull String id) {
        appPerfInstanceDao.deleteWebPerfInstance(id);
    }

    @Override
    public WebPerfInstance findOne(String id) {
        WebPerfInstanceEntity webPerfInstanceEntity = appPerfInstanceDao.findWebPerfInstance(id);

        WebPerfInstance webPerfInstance = BeanMapper.map(webPerfInstanceEntity, WebPerfInstance.class);
        return webPerfInstance;
    }

    @Override
    public List<WebPerfInstance> findList(List<String> idList) {
        List<WebPerfInstanceEntity> webPerfInstanceEntities =  appPerfInstanceDao.findWebPerfInstanceList(idList);

        List<WebPerfInstance> webPerfInstanceList =  BeanMapper.mapList(webPerfInstanceEntities, WebPerfInstance.class);
        return webPerfInstanceList;
    }

    @Override
    public WebPerfInstance findWebPerfInstance(@NotNull String id) {
        WebPerfInstance webPerfInstance = findOne(id);

        joinTemplate.joinQuery(webPerfInstance);
        return webPerfInstance;
    }

    @Override
    public List<WebPerfInstance> findAllWebPerfInstance() {
        List<WebPerfInstanceEntity> webPerfInstanceEntities =  appPerfInstanceDao.findAllWebPerfInstance();

        List<WebPerfInstance> webPerfInstanceList =  BeanMapper.mapList(webPerfInstanceEntities, WebPerfInstance.class);

        joinTemplate.joinQuery(webPerfInstanceList);
        return webPerfInstanceList;
    }

    @Override
    public List<WebPerfInstance> findWebPerfInstanceList(WebPerfInstanceQuery performanceInstanceQuery) {
        List<WebPerfInstanceEntity> webPerfInstanceEntities = appPerfInstanceDao.findWebPerfInstanceList(performanceInstanceQuery);

        List<WebPerfInstance> webPerfInstanceList = BeanMapper.mapList(webPerfInstanceEntities, WebPerfInstance.class);

        joinTemplate.joinQuery(webPerfInstanceList);

        return webPerfInstanceList;
    }

    @Override
    public Pagination<WebPerfInstance> findWebPerfInstancePage(WebPerfInstanceQuery performanceInstanceQuery) {
        Pagination<WebPerfInstanceEntity>  pagination = appPerfInstanceDao.findWebPerfInstancePage(performanceInstanceQuery);

        List<WebPerfInstance> webPerfInstanceList = BeanMapper.mapList(pagination.getDataList(), WebPerfInstance.class);

        joinTemplate.joinQuery(webPerfInstanceList);

        return PaginationBuilder.build(pagination,webPerfInstanceList);
    }
}