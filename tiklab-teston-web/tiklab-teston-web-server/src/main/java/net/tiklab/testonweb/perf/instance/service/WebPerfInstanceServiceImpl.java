package net.tiklab.testonweb.perf.instance.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;

import net.tiklab.testonweb.perf.instance.dao.WebPerfInstanceDao;
import net.tiklab.testonweb.perf.instance.entity.WebPerfInstanceEntity;
import net.tiklab.testonweb.perf.instance.model.WebPerfInstance;
import net.tiklab.testonweb.perf.instance.model.WebPerfInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* WebPerfInstanceServiceImpl
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