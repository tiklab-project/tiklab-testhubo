package net.tiklab.teston.apix.http.scene.instance.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;

import net.tiklab.teston.apix.http.scene.execute.model.ApiSceneTestResponse;
import net.tiklab.teston.apix.http.scene.instance.entity.ApiSceneInstanceEntity;
import net.tiklab.teston.apix.http.scene.instance.model.ApiSceneInstance;
import net.tiklab.teston.apix.http.scene.instance.model.ApiSceneInstanceQuery;
import net.tiklab.teston.apix.http.scene.instance.model.ApiSceneStepInstanceBind;
import net.tiklab.teston.apix.http.scene.instance.model.ApiSceneStepInstanceBindQuery;
import net.tiklab.teston.apix.http.unit.instance.model.ApiUnitInstance;
import net.tiklab.teston.apix.http.unit.instance.service.ApiUnitInstanceService;
import net.tiklab.teston.manager.testcase.service.TestCaseService;
import net.tiklab.teston.apix.http.scene.instance.dao.ApiSceneInstanceDao;
import net.tiklab.utils.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* TestInstanceServiceImpl
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


        apiSceneInstance.setStepList(apiSceneStepInstanceBindList);

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
            apiSceneStepInstanceBind.setId(apiSceneInstanceId);
            apiSceneStepInstanceBindService.createApiSceneStepInstanceBind(apiSceneStepInstanceBind);

        });


        return apiSceneInstanceId;
    }


}