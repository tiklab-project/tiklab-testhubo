package io.tiklab.teston.test.apix.http.scene.cases.service;



import io.tiklab.teston.test.apix.http.scene.cases.dao.ApiSceneStepDao;
import io.tiklab.teston.test.apix.http.scene.cases.entity.ApiSceneStepEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;

import io.tiklab.teston.test.apix.http.scene.cases.model.ApiSceneStep;
import io.tiklab.teston.test.apix.http.scene.cases.model.ApiSceneStepQuery;
import io.tiklab.teston.test.test.model.TestCase;
import io.tiklab.teston.test.test.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
* 接口场景下绑定的步骤 服务
*/
@Service
public class ApiSceneStepServiceImpl implements ApiSceneStepService {

    @Autowired
    ApiSceneStepDao apiSceneStepDao;

    @Autowired
    TestCaseService testCaseService;


    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createApiSceneStep(@NotNull @Valid ApiSceneStep apiSceneStep) {
        ApiSceneStepEntity apiSceneStepEntity = BeanMapper.map(apiSceneStep, ApiSceneStepEntity.class);
        apiSceneStepEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        List<ApiSceneStepEntity> apiSceneStepEntityList = findApiSceneStepEntityList(apiSceneStep.getApiScene().getId());
        if (apiSceneStepEntityList!=null && apiSceneStepEntityList.size() > 0) {
            apiSceneStepEntity.setSort(apiSceneStepEntityList.size());
        }else {
            apiSceneStepEntity.setSort(0);
        }

        return apiSceneStepDao.createApiSceneStep(apiSceneStepEntity);
    }

    @Override
    public void updateApiSceneStep(@NotNull @Valid ApiSceneStep apiSceneStep) {
        if(apiSceneStep.getOldSort()!=null){
            Integer curSort = apiSceneStep.getSort();
            Integer oldSort = apiSceneStep.getOldSort();

            List<ApiSceneStepEntity> apiSceneStepEntityList = findApiSceneStepEntityList(apiSceneStep.getApiScene().getId());
            if(curSort>oldSort){
                for(int i=oldSort+1;i<=curSort;i++){
                    ApiSceneStepEntity apiSceneStepEntity = apiSceneStepEntityList.get(i);
                    apiSceneStepEntity.setSort(apiSceneStepEntity.getSort()-1);
                    apiSceneStepDao.updateApiSceneStep(apiSceneStepEntity);
                }
            }
            if(curSort<oldSort){
                for(int i=oldSort-1;i>=curSort;i--){
                    ApiSceneStepEntity apiSceneStepEntity = apiSceneStepEntityList.get(i);
                    apiSceneStepEntity.setSort(apiSceneStepEntity.getSort()+1);
                    apiSceneStepDao.updateApiSceneStep(apiSceneStepEntity);
                }
            }
        }


        ApiSceneStepEntity apiSceneStepEntity = BeanMapper.map(apiSceneStep, ApiSceneStepEntity.class);
        apiSceneStepDao.updateApiSceneStep(apiSceneStepEntity);
    }

    @Override
    public void deleteApiSceneStep(@NotNull String id) {
        ApiSceneStepEntity apiSceneStep = apiSceneStepDao.findApiSceneStep(id);
        if(apiSceneStep== null){return;}
        Integer sort = apiSceneStep.getSort();

        List<ApiSceneStepEntity> apiSceneStepEntityList = findApiSceneStepEntityList(apiSceneStep.getApiSceneId());
        for(ApiSceneStepEntity apiSceneStepEntity:apiSceneStepEntityList){
            if(apiSceneStepEntity.getSort()>sort){
                apiSceneStepEntity.setSort(apiSceneStepEntity.getSort()-1);
                apiSceneStepDao.updateApiSceneStep(apiSceneStepEntity);
            }

            if(apiSceneStepEntity.getSort().equals(sort)){
                apiSceneStepDao.deleteApiSceneStep(id);
            }
        }
    }

    @Override
    public ApiSceneStep findOne(String id) {
        ApiSceneStepEntity apiSceneStepEntity = apiSceneStepDao.findApiSceneStep(id);

        ApiSceneStep apiSceneStep = BeanMapper.map(apiSceneStepEntity, ApiSceneStep.class);
        return apiSceneStep;
    }

    @Override
    public List<ApiSceneStep> findList(List<String> idList) {
        List<ApiSceneStepEntity> apiSceneStepEntityList =  apiSceneStepDao.findApiSceneStepList(idList);

        List<ApiSceneStep> apiSceneStepList =  BeanMapper.mapList(apiSceneStepEntityList,ApiSceneStep.class);
        return apiSceneStepList;
    }

    @Override
    public ApiSceneStep findApiSceneStep(@NotNull String id) {
        ApiSceneStep apiSceneStep = findOne(id);

        joinTemplate.joinQuery(apiSceneStep);

        return apiSceneStep;
    }

    @Override
    public List<ApiSceneStep> findAllApiSceneStep() {
        List<ApiSceneStepEntity> apiSceneStepEntityList =  apiSceneStepDao.findAllApiSceneStep();

        List<ApiSceneStep> apiSceneStepList =  BeanMapper.mapList(apiSceneStepEntityList,ApiSceneStep.class);

        joinTemplate.joinQuery(apiSceneStepList);

        return apiSceneStepList;
    }

    @Override
    public List<ApiSceneStep> findApiSceneStepList(ApiSceneStepQuery apiSceneStepQuery) {
        List<ApiSceneStepEntity> apiSceneStepEntityList = apiSceneStepDao.findApiSceneStepList(apiSceneStepQuery);

        List<ApiSceneStep> apiSceneStepList = BeanMapper.mapList(apiSceneStepEntityList,ApiSceneStep.class);

        joinTemplate.joinQuery(apiSceneStepList);

        //第三层字段显示不出来，手动加入
        ArrayList<ApiSceneStep> arrayList = new ArrayList<>();
        if(apiSceneStepList.size()>0){
            for(ApiSceneStep apiSceneStep:apiSceneStepList){
                TestCase testCase = testCaseService.findTestCase(apiSceneStep.getApiUnit().getId());

                apiSceneStep.getApiUnit().setTestCase(testCase);

                arrayList.add(apiSceneStep);
            }
        }

        return arrayList;
    }

    @Override
    public Pagination<ApiSceneStep> findApiSceneStepPage(ApiSceneStepQuery apiSceneStepQuery) {
        Pagination<ApiSceneStepEntity>  pagination = apiSceneStepDao.findApiSceneStepPage(apiSceneStepQuery);

        List<ApiSceneStep> apiSceneStepList = BeanMapper.mapList(pagination.getDataList(),ApiSceneStep.class);

        joinTemplate.joinQuery(apiSceneStepList);

        return PaginationBuilder.build(pagination,apiSceneStepList);
    }

    @Override
    public void bindApiUnit(List<ApiSceneStep> apiSceneStepList) {
        for (ApiSceneStep apiSceneStep : apiSceneStepList) {
            createApiSceneStep(apiSceneStep);
        }
    }

    private List<ApiSceneStepEntity> findApiSceneStepEntityList(String apiSceneId){
        ApiSceneStepQuery apiSceneStepQuery = new ApiSceneStepQuery();
        apiSceneStepQuery.setApiSceneId(apiSceneId);
        List<ApiSceneStepEntity> apiSceneStepList = apiSceneStepDao.findApiSceneStepList(apiSceneStepQuery);

        return apiSceneStepList;
    }
}