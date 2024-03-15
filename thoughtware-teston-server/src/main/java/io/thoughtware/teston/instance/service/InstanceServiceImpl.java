package io.thoughtware.teston.instance.service;

import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.DeleteBuilders;
import io.thoughtware.teston.common.MagicValue;
import io.thoughtware.teston.test.apix.http.perf.instance.service.ApiPerfInstanceService;
import io.thoughtware.teston.test.apix.http.scene.instance.service.ApiSceneInstanceService;
import io.thoughtware.teston.test.apix.http.unit.instance.service.ApiUnitInstanceService;
import io.thoughtware.teston.test.app.scene.instance.service.AppSceneInstanceService;
import io.thoughtware.teston.test.web.scene.instance.service.WebSceneInstanceService;
import io.thoughtware.teston.testplan.instance.service.TestPlanInstanceService;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.teston.instance.dao.InstanceDao;
import io.thoughtware.teston.instance.entity.InstanceEntity;
import io.thoughtware.teston.instance.model.Instance;
import io.thoughtware.teston.instance.model.InstanceQuery;
import io.thoughtware.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* 公共实例 服务
*/
@Service
public class InstanceServiceImpl implements InstanceService {

    @Autowired
    InstanceDao instanceDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ApiUnitInstanceService apiUnitInstanceService;

    @Autowired
    ApiSceneInstanceService apiSceneInstanceService;

    @Autowired
    ApiPerfInstanceService apiPerfInstanceService;

    @Autowired
    AppSceneInstanceService appSceneInstanceService;

    @Autowired
    WebSceneInstanceService webSceneInstanceService;

    @Autowired
    TestPlanInstanceService testPlanInstanceService;

    @Override
    public String createInstance(@NotNull @Valid Instance instance) {

        instance.setCreateTime(new Timestamp(System.currentTimeMillis()));
        User user = new User();
        user.setId(LoginContext.getLoginId());
        instance.setCreateUser(user);
        InstanceEntity instanceEntity = BeanMapper.map(instance, InstanceEntity.class);

        return instanceDao.createInstance(instanceEntity);
    }

    @Override
    public void updateInstance(@NotNull @Valid Instance instance) {
        InstanceEntity instanceEntity = BeanMapper.map(instance, InstanceEntity.class);

        instanceDao.updateInstance(instanceEntity);
    }

    @Override
    public void deleteInstance(@NotNull String id,String caseType) {
        instanceDao.deleteInstance(id);

        //删除对应的用例
        switch (caseType) {
            case MagicValue.CASE_TYPE_API_UNIT -> {
                apiUnitInstanceService.deleteApiUnitInstance(id);
                break;
            }
            case MagicValue.CASE_TYPE_API_SCENE -> {
                apiSceneInstanceService.deleteApiSceneInstance(id);
                break;
            }
            case MagicValue.CASE_TYPE_API_PERFORM -> {
                apiPerfInstanceService.deleteApiPerfInstance(id);
                break;
            }
            case MagicValue.CASE_TYPE_WEB -> {
                webSceneInstanceService.deleteWebSceneInstance(id);
                break;
            }
            case MagicValue.CASE_TYPE_APP -> {
                appSceneInstanceService.deleteAppSceneInstance(id);
                break;
            }
            case MagicValue.TEST_PLAN -> {
                testPlanInstanceService.deleteTestPlanInstance(id);
                break;
            }

            default -> {
            }
        }
    }


    @Override
    public void deleteAllInstance(String caseId) {

        InstanceQuery instanceQuery = new InstanceQuery();
        instanceQuery.setBelongId(caseId);
        List<Instance> instanceList = findInstanceList(instanceQuery);

        for(Instance instance : instanceList){
            deleteInstance(instance.getId(),instance.getType());
        }
    }



    @Override
    public Instance findOne(String id) {
        InstanceEntity instanceEntity = instanceDao.findInstance(id);

        Instance instance = BeanMapper.map(instanceEntity, Instance.class);
        return instance;
    }

    @Override
    public List<Instance> findList(List<String> idList) {
        List<InstanceEntity> instanceEntityList =  instanceDao.findInstanceList(idList);

        List<Instance> instanceList =  BeanMapper.mapList(instanceEntityList, Instance.class);
        return instanceList;
    }

    @Override
    public Instance findInstance(@NotNull String id) {
        Instance instance = findOne(id);

        joinTemplate.joinQuery(instance);
        return instance;
    }

    @Override
    public Instance findRecentInstance(String belongId) {
        InstanceEntity recentInstance = instanceDao.findRecentInstance(belongId);
        Instance instance = BeanMapper.map(recentInstance, Instance.class);
        return instance;
    }


    @Override
    public List<Instance> findAllInstance() {
        List<InstanceEntity> instanceEntityList =  instanceDao.findAllInstance();

        List<Instance> instanceList =  BeanMapper.mapList(instanceEntityList, Instance.class);

        joinTemplate.joinQuery(instanceList);
        return instanceList;
    }

    @Override
    public List<Instance> findInstanceList(InstanceQuery instanceQuery) {
        List<InstanceEntity> instanceEntityList = instanceDao.findInstanceList(instanceQuery);

        List<Instance> instanceList = BeanMapper.mapList(instanceEntityList, Instance.class);

        joinTemplate.joinQuery(instanceList);

        return instanceList;
    }

    @Override
    public Pagination<Instance> findInstancePage(InstanceQuery instanceQuery) {

        Pagination<InstanceEntity>  pagination = instanceDao.findInstancePage(instanceQuery);

        List<Instance> instanceList = BeanMapper.mapList(pagination.getDataList(), Instance.class);

        joinTemplate.joinQuery(instanceList);

        return PaginationBuilder.build(pagination, instanceList);
    }

    @Override
    public int getRecentExecuteNum(String belongId) {
        int recentExecuteNum = instanceDao.getRecentExecuteNum(belongId);
        if(recentExecuteNum==0){
            return 1;
        }else {
            return ++recentExecuteNum;
        }
    }

    @Override
    public String getRecentExecuteInstanceId(String belongId) {
        String recentExecuteInstanceId = instanceDao.getRecentExecuteInstanceId(belongId);
        return recentExecuteInstanceId;
    }


}