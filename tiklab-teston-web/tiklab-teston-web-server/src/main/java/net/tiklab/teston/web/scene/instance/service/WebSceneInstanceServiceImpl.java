package net.tiklab.teston.web.scene.instance.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.web.scene.instance.entity.WebSceneInstanceEntity;
import net.tiklab.teston.web.scene.instance.model.WebSceneInstanceQuery;
import net.tiklab.teston.web.scene.execute.model.WebSceneTestResponse;
import net.tiklab.teston.web.scene.instance.dao.WebSceneInstanceDao;
import net.tiklab.teston.web.scene.instance.model.WebSceneInstance;
import net.tiklab.teston.web.scene.instance.model.WebSceneInstanceStep;
import net.tiklab.teston.web.scene.instance.model.WebSceneInstanceStepQuery;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* web场景实例 服务
*/
@Service
public class WebSceneInstanceServiceImpl implements WebSceneInstanceService {

    @Autowired
    WebSceneInstanceDao webSceneInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;
    
    @Autowired
    WebSceneInstanceStepService webSceneInstanceStepService;

    @Override
    public String createWebSceneInstance(@NotNull @Valid WebSceneInstance webSceneInstance) {
        WebSceneInstanceEntity webSceneInstanceEntity = BeanMapper.map(webSceneInstance, WebSceneInstanceEntity.class);

        webSceneInstanceEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return webSceneInstanceDao.createWebSceneInstance(webSceneInstanceEntity);
    }

    @Override
    public void updateWebSceneInstance(@NotNull @Valid WebSceneInstance webSceneInstance) {
        WebSceneInstanceEntity webSceneInstanceEntity = BeanMapper.map(webSceneInstance, WebSceneInstanceEntity.class);

        webSceneInstanceDao.updateWebSceneInstance(webSceneInstanceEntity);
    }

    @Override
    public void deleteWebSceneInstance(@NotNull String id) {
        webSceneInstanceDao.deleteWebSceneInstance(id);
    }

    @Override
    public WebSceneInstance findOne(String id) {
        WebSceneInstanceEntity webSceneInstanceEntity = webSceneInstanceDao.findWebSceneInstance(id);

        WebSceneInstance webSceneInstance = BeanMapper.map(webSceneInstanceEntity, WebSceneInstance.class);
        return webSceneInstance;
    }

    @Override
    public List<WebSceneInstance> findList(List<String> idList) {
        List<WebSceneInstanceEntity> webSceneInstanceEntityList =  webSceneInstanceDao.findWebSceneInstanceList(idList);

        List<WebSceneInstance> webSceneInstanceList =  BeanMapper.mapList(webSceneInstanceEntityList, WebSceneInstance.class);
        return webSceneInstanceList;
    }

    @Override
    public WebSceneInstance findWebSceneInstance(@NotNull String id) {
        WebSceneInstance webSceneInstance = findOne(id);

        List<WebSceneInstanceStep> webSceneInstanceStepList = webSceneInstanceStepService.findWebSceneInstanceStepList(new WebSceneInstanceStepQuery().setWebSceneInstanceId(id));

        webSceneInstance.setStepList(webSceneInstanceStepList);

        joinTemplate.joinQuery(webSceneInstance);
        return webSceneInstance;
    }

    @Override
    public List<WebSceneInstance> findAllWebSceneInstance() {
        List<WebSceneInstanceEntity> webSceneInstanceEntities =  webSceneInstanceDao.findAllWebSceneInstance();

        List<WebSceneInstance> webSceneInstanceList =  BeanMapper.mapList(webSceneInstanceEntities, WebSceneInstance.class);

        joinTemplate.joinQuery(webSceneInstanceList);
        return webSceneInstanceList;
    }

    @Override
    public List<WebSceneInstance> findWebSceneInstanceList(WebSceneInstanceQuery webSceneInstanceQuery) {
        List<WebSceneInstanceEntity> webSceneInstanceEntities = webSceneInstanceDao.findWebSceneInstanceList(webSceneInstanceQuery);

        List<WebSceneInstance> webSceneInstanceList = BeanMapper.mapList(webSceneInstanceEntities, WebSceneInstance.class);

        joinTemplate.joinQuery(webSceneInstanceList);

        return webSceneInstanceList;
    }

    @Override
    public Pagination<WebSceneInstance> findWebSceneInstancePage(WebSceneInstanceQuery webSceneInstanceQuery) {
        Pagination<WebSceneInstanceEntity>  pagination = webSceneInstanceDao.findWebSceneInstancePage(webSceneInstanceQuery);

        List<WebSceneInstance> webSceneInstanceList = BeanMapper.mapList(pagination.getDataList(), WebSceneInstance.class);

        joinTemplate.joinQuery(webSceneInstanceList);

        return PaginationBuilder.build(pagination,webSceneInstanceList);
    }

    @Override
    public String saveWebSceneInstanceToSql(WebSceneInstance webSceneInstance, WebSceneTestResponse webSceneTestResponse) {

        String webSceneInstanceId= createWebSceneInstance(webSceneInstance);

        //保存单个步骤
        if(CollectionUtils.isNotEmpty(webSceneTestResponse.getWebUnitResultList())){
            for(WebSceneInstanceStep webSceneInstanceStep:webSceneTestResponse.getWebUnitResultList()){
                webSceneInstanceStep.setWebSceneInstanceId(webSceneInstanceId);
                webSceneInstanceStepService.createWebSceneInstanceStep(webSceneInstanceStep);
            }
        }

        return webSceneInstanceId;
    }

}