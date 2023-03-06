package net.tiklab.teston.test.web.perf.cases.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;

import net.tiklab.teston.test.test.model.TestCase;
import net.tiklab.teston.test.test.service.TestCaseService;
import net.tiklab.teston.test.web.perf.cases.dao.WebPerfStepDao;
import net.tiklab.teston.test.web.perf.cases.entity.WebPerfStepEntity;
import net.tiklab.teston.test.web.perf.cases.model.WebPerfStep;
import net.tiklab.teston.test.web.perf.cases.model.WebPerfStepQuery;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
* web性能用例下绑定的场景 服务
*/
@Service
public class WebPerfStepServiceImpl implements WebPerfStepService {

    @Autowired
    WebPerfStepDao webPerfStepDao;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createWebPerfStep(@NotNull @Valid WebPerfStep webPerfStep) {
        WebPerfStepEntity webPerfStepEntity = BeanMapper.map(webPerfStep, WebPerfStepEntity.class);

        webPerfStepEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return webPerfStepDao.createWebPerfStep(webPerfStepEntity);
    }

    @Override
    public void updateWebPerfStep(@NotNull @Valid WebPerfStep webPerfStep) {
        WebPerfStepEntity webPerfStepEntity = BeanMapper.map(webPerfStep, WebPerfStepEntity.class);

        webPerfStepDao.updateWebPerfStep(webPerfStepEntity);
    }

    @Override
    public void deleteWebPerfStep(@NotNull String id) {
        webPerfStepDao.deleteWebPerfStep(id);
    }

    @Override
    public WebPerfStep findOne(String id) {
        WebPerfStepEntity webPerfStepEntity = webPerfStepDao.findWebPerfStep(id);

        WebPerfStep webPerfStep = BeanMapper.map(webPerfStepEntity, WebPerfStep.class);
        return webPerfStep;
    }

    @Override
    public List<WebPerfStep> findList(List<String> idList) {
        List<WebPerfStepEntity> webPerfStepEntityList =  webPerfStepDao.findWebPerfStepList(idList);

        List<WebPerfStep> webPerfStepList =  BeanMapper.mapList(webPerfStepEntityList,WebPerfStep.class);
        return webPerfStepList;
    }

    @Override
    public WebPerfStep findWebPerfStep(@NotNull String id) {
        WebPerfStep webPerfStep = findOne(id);

        joinTemplate.joinQuery(webPerfStep);

        return webPerfStep;
    }

    @Override
    public List<WebPerfStep> findAllWebPerfStep() {
        List<WebPerfStepEntity> webPerfStepEntityList =  webPerfStepDao.findAllWebPerfStep();

        List<WebPerfStep> webPerfStepList =  BeanMapper.mapList(webPerfStepEntityList,WebPerfStep.class);

        joinTemplate.joinQuery(webPerfStepList);

        return webPerfStepList;
    }

    @Override
    public List<WebPerfStep> findWebPerfStepList(WebPerfStepQuery webPerfStepQuery) {
        List<WebPerfStepEntity> webPerfStepEntityList = webPerfStepDao.findWebPerfStepList(webPerfStepQuery);

        List<WebPerfStep> webPerfStepList = BeanMapper.mapList(webPerfStepEntityList,WebPerfStep.class);

        joinTemplate.joinQuery(webPerfStepList);

        //第三层字段显示不出来，手动加入
        ArrayList<WebPerfStep> arrayList = new ArrayList<>();
        if(webPerfStepList.size()>0){
            for(WebPerfStep webPerfStep:webPerfStepList){
                TestCase testCase = testCaseService.findTestCase(webPerfStep.getWebScene().getId());

                webPerfStep.getWebScene().setTestCase(testCase);

                arrayList.add(webPerfStep);
            }
        }


        return webPerfStepList;
    }

    @Override
    public Pagination<WebPerfStep> findWebPerfStepPage(WebPerfStepQuery webPerfStepQuery) {
        Pagination<WebPerfStepEntity>  pagination = webPerfStepDao.findWebPerfStepPage(webPerfStepQuery);

        List<WebPerfStep> webPerfStepList = BeanMapper.mapList(pagination.getDataList(),WebPerfStep.class);

        joinTemplate.joinQuery(webPerfStepList);

        return PaginationBuilder.build(pagination,webPerfStepList);
    }

    @Override
    public void bindWebScene(List<WebPerfStep> webPerfStepList) {
        if(CollectionUtils.isNotEmpty(webPerfStepList)){
            for(WebPerfStep webPerfStep:webPerfStepList){
                createWebPerfStep(webPerfStep);
            }
        }
    }
}