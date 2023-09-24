package io.tiklab.teston.test.apix.http.scene.instance.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;

import io.tiklab.teston.test.apix.http.scene.execute.model.ApiSceneTestResponse;
import io.tiklab.teston.test.apix.http.scene.instance.entity.ApiSceneInstanceEntity;
import io.tiklab.teston.test.apix.http.scene.instance.model.ApiSceneInstance;
import io.tiklab.teston.test.apix.http.scene.instance.model.ApiSceneInstanceQuery;
import io.tiklab.teston.test.apix.http.scene.instance.model.ApiSceneStepInstanceBind;
import io.tiklab.teston.test.apix.http.scene.instance.model.ApiSceneStepInstanceBindQuery;
import io.tiklab.teston.test.apix.http.unit.cases.model.ApiUnitCase;
import io.tiklab.teston.test.apix.http.unit.cases.service.ApiUnitCaseService;
import io.tiklab.teston.test.apix.http.unit.instance.model.ApiUnitInstance;
import io.tiklab.teston.test.apix.http.unit.instance.service.ApiUnitInstanceService;
import io.tiklab.teston.test.test.service.TestCaseService;
import io.tiklab.teston.test.apix.http.scene.instance.dao.ApiSceneInstanceDao;
import io.tiklab.eam.common.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
* 接口场景实例 服务
*/
@Service
public class ApiSceneInstanceServiceImpl implements ApiSceneInstanceService {

    @Autowired
    ApiSceneInstanceDao apiSceneInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ApiUnitInstanceService apiUnitInstanceService;

    @Autowired
    ApiSceneStepInstanceBindService apiSceneStepInstanceBindService;

    @Autowired
    TestCaseService unitCaseService;

    @Autowired
    ApiUnitCaseService apiUnitCaseService;

    @Override
    public String createApiSceneInstance(@NotNull @Valid ApiSceneInstance scenInstance) {
        ApiSceneInstanceEntity apiSceneInstanceEntity = BeanMapper.map(scenInstance, ApiSceneInstanceEntity.class);

        apiSceneInstanceEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        String userId = LoginContext.getLoginId();
        apiSceneInstanceEntity.setCreateUser(userId);

        return apiSceneInstanceDao.createTestInstance(apiSceneInstanceEntity);
    }

    @Override
    public void updateApiSceneInstance(@NotNull @Valid ApiSceneInstance scenInstance) {
        ApiSceneInstanceEntity testInstanceEntity = BeanMapper.map(scenInstance, ApiSceneInstanceEntity.class);

        apiSceneInstanceDao.updateTestInstance(testInstanceEntity);
    }

    @Override
    public void deleteApiSceneInstance(@NotNull String id) {
        apiSceneInstanceDao.deleteTestInstance(id);
    }

    @Override
    public ApiSceneInstance findOne(String id) {
        ApiSceneInstanceEntity testInstanceEntity = apiSceneInstanceDao.findTestInstance(id);

        ApiSceneInstance testInstance = BeanMapper.map(testInstanceEntity, ApiSceneInstance.class);
        return testInstance;
    }

    @Override
    public List<ApiSceneInstance> findList(List<String> idList) {
        List<ApiSceneInstanceEntity> testInstanceEntityList =  apiSceneInstanceDao.findTestInstanceList(idList);

        List<ApiSceneInstance> testInstanceList =  BeanMapper.mapList(testInstanceEntityList, ApiSceneInstance.class);
        return testInstanceList;
    }

    @Override
    public ApiSceneInstance findApiSceneInstance(@NotNull String id) {
        ApiSceneInstance apiSceneInstance = findOne(id);

        //通过中间表把所有步骤历史查不出
        ApiSceneStepInstanceBindQuery apiSceneStepInstanceBindQuery = new ApiSceneStepInstanceBindQuery();
        apiSceneStepInstanceBindQuery.setApiSceneInstanceId(id);
        List<ApiSceneStepInstanceBind> apiSceneStepInstanceBindList = apiSceneStepInstanceBindService.findApiSceneStepInstanceBindList(apiSceneStepInstanceBindQuery);

        //用于手动添加字段
        ArrayList<ApiSceneStepInstanceBind> newApiStepList = new ArrayList<>();
        for(ApiSceneStepInstanceBind apiSceneStepInstanceBind:apiSceneStepInstanceBindList){
            ApiUnitCase apiUnit = apiSceneStepInstanceBind.getApiUnitInstance().getApiUnit();
            ApiUnitCase apiUnitCase = apiUnitCaseService.findApiUnitCase(apiUnit.getId());

            apiSceneStepInstanceBind.getApiUnitInstance().setApiUnit(apiUnitCase);

            newApiStepList.add(apiSceneStepInstanceBind);
        }


        apiSceneInstance.setStepList(newApiStepList);

        joinTemplate.joinQuery(apiSceneInstance);
        return apiSceneInstance;
    }

    @Override
    public List<ApiSceneInstance> findAllApiSceneInstance() {
        List<ApiSceneInstanceEntity> testInstanceEntities =  apiSceneInstanceDao.findAllTestInstance();

        List<ApiSceneInstance> testInstanceList =  BeanMapper.mapList(testInstanceEntities, ApiSceneInstance.class);

        joinTemplate.joinQuery(testInstanceList);
        return testInstanceList;
    }

    @Override
    public List<ApiSceneInstance> findApiSceneInstanceList(ApiSceneInstanceQuery apiSceneInstanceQuery) {
        List<ApiSceneInstanceEntity> testInstanceEntities = apiSceneInstanceDao.findTestInstanceList(apiSceneInstanceQuery);

        List<ApiSceneInstance> testInstanceList = BeanMapper.mapList(testInstanceEntities, ApiSceneInstance.class);

        joinTemplate.joinQuery(testInstanceList);

        return testInstanceList;
    }

    @Override
    public Pagination<ApiSceneInstance> findApiSceneInstancePage(ApiSceneInstanceQuery apiSceneInstanceQuery) {
        Pagination<ApiSceneInstanceEntity>  pagination = apiSceneInstanceDao.findTestInstancePage(apiSceneInstanceQuery);

        List<ApiSceneInstance> testInstanceList = BeanMapper.mapList(pagination.getDataList(), ApiSceneInstance.class);

        joinTemplate.joinQuery(testInstanceList);

        return PaginationBuilder.build(pagination,testInstanceList);
    }

    @Override
    public String saveApiSceneInstanceToSql(ApiSceneInstance apiSceneInstance, ApiSceneTestResponse apiSceneTestResponse) {
        String apiSceneInstanceId = createApiSceneInstance(apiSceneInstance);

        //所有ApiUnitInstance设置apiSceneInstanceId创建
        List<ApiUnitInstance> apiUnitInstanceList = apiSceneTestResponse.getApiUnitInstanceList();
        apiUnitInstanceList.forEach(apiUnitInstance -> {

            String apiUnitInstanceId = apiUnitInstanceService.saveApiUnitInstanceToSql(apiUnitInstance);

            //关联表， 用于 场景步骤历史 与 场景历史 相关联
            ApiSceneStepInstanceBind apiSceneStepInstanceBind = new ApiSceneStepInstanceBind();
            apiSceneStepInstanceBind.setApiSceneInstanceId(apiSceneInstanceId);

            ApiUnitInstance apiUnitInstance1 = new ApiUnitInstance();
            apiUnitInstance1.setId(apiUnitInstanceId);
            apiSceneStepInstanceBind.setApiUnitInstance(apiUnitInstance1);
            apiSceneStepInstanceBind.setId(apiUnitInstanceId);
            apiSceneStepInstanceBindService.createApiSceneStepInstanceBind(apiSceneStepInstanceBind);

        });


        return apiSceneInstanceId;
    }


}