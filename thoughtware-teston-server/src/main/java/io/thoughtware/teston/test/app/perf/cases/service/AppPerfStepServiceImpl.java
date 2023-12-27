package io.thoughtware.teston.test.app.perf.cases.service;

import io.thoughtware.teston.test.app.perf.cases.model.AppPerfStep;
import io.thoughtware.teston.test.app.perf.cases.model.AppPerfStepQuery;
import io.thoughtware.teston.test.test.model.TestCase;
import io.thoughtware.teston.test.test.service.TestCaseService;
import io.thoughtware.teston.test.app.perf.cases.dao.AppPerfStepDao;
import io.thoughtware.teston.test.app.perf.cases.entity.AppPerfStepEntity;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
* app性能测试用例下绑定的步骤 服务
*/
@Service
public class AppPerfStepServiceImpl implements AppPerfStepService {

    @Autowired
    AppPerfStepDao appPerfStepDao;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAppPerfStep(@NotNull @Valid AppPerfStep appPerfStep) {
        AppPerfStepEntity appPerfStepEntity = BeanMapper.map(appPerfStep, AppPerfStepEntity.class);

        appPerfStepEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return appPerfStepDao.createAppPerfStep(appPerfStepEntity);
    }

    @Override
    public void updateAppPerfStep(@NotNull @Valid AppPerfStep appPerfStep) {
        AppPerfStepEntity appPerfStepEntity = BeanMapper.map(appPerfStep, AppPerfStepEntity.class);

        appPerfStepDao.updateAppPerfStep(appPerfStepEntity);
    }

    @Override
    public void deleteAppPerfStep(@NotNull String id) {
        appPerfStepDao.deleteAppPerfStep(id);
    }

    @Override
    public AppPerfStep findOne(String id) {
        AppPerfStepEntity appPerfStepEntity = appPerfStepDao.findAppPerfStep(id);

        AppPerfStep appPerfStep = BeanMapper.map(appPerfStepEntity, AppPerfStep.class);
        return appPerfStep;
    }

    @Override
    public List<AppPerfStep> findList(List<String> idList) {
        List<AppPerfStepEntity> appPerfStepEntityList =  appPerfStepDao.findAppPerfStepList(idList);

        List<AppPerfStep> appPerfStepList =  BeanMapper.mapList(appPerfStepEntityList,AppPerfStep.class);
        return appPerfStepList;
    }

    @Override
    public AppPerfStep findAppPerfStep(@NotNull String id) {
        AppPerfStep appPerfStep = findOne(id);

        joinTemplate.joinQuery(appPerfStep);

        return appPerfStep;
    }

    @Override
    public List<AppPerfStep> findAllAppPerfStep() {
        List<AppPerfStepEntity> appPerfStepEntityList =  appPerfStepDao.findAllAppPerfStep();

        List<AppPerfStep> appPerfStepList =  BeanMapper.mapList(appPerfStepEntityList,AppPerfStep.class);

        joinTemplate.joinQuery(appPerfStepList);

        return appPerfStepList;
    }

    @Override
    public List<AppPerfStep> findAppPerfStepList(AppPerfStepQuery appPerfStepQuery) {
        List<AppPerfStepEntity> appPerfStepEntityList = appPerfStepDao.findAppPerfStepList(appPerfStepQuery);

        List<AppPerfStep> appPerfStepList = BeanMapper.mapList(appPerfStepEntityList,AppPerfStep.class);

        joinTemplate.joinQuery(appPerfStepList);

        //第三层字段显示不出来，手动加入
        ArrayList<AppPerfStep> arrayList = new ArrayList<>();
        if(appPerfStepList.size()>0){
            for(AppPerfStep appPerfStep:appPerfStepList){
                TestCase testCase = testCaseService.findTestCase(appPerfStep.getAppScene().getId());

                appPerfStep.getAppScene().setTestCase(testCase);


                arrayList.add(appPerfStep);
            }
        }

        return arrayList;
    }

    @Override
    public Pagination<AppPerfStep> findAppPerfStepPage(AppPerfStepQuery appPerfStepQuery) {
        Pagination<AppPerfStepEntity>  pagination = appPerfStepDao.findAppPerfStepPage(appPerfStepQuery);

        List<AppPerfStep> appPerfStepList = BeanMapper.mapList(pagination.getDataList(),AppPerfStep.class);

        joinTemplate.joinQuery(appPerfStepList);

        return PaginationBuilder.build(pagination,appPerfStepList);
    }

    @Override
    public void bindAppScene(List<AppPerfStep> appPerfStepList) {
        if(CollectionUtils.isNotEmpty(appPerfStepList)){
            for(AppPerfStep appPerfStep:appPerfStepList){
                createAppPerfStep(appPerfStep);
            }
        }
    }
}