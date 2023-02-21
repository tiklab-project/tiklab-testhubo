package net.tiklab.teston.testjob.service;

import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.teston.testjob.dao.QuartzTestcaseDao;
import net.tiklab.teston.testjob.entity.QuartzTestcaseEntity;
import net.tiklab.teston.testjob.model.QuartzTestcase;
import net.tiklab.teston.testjob.model.QuartzTestcaseQuery;

import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.apitest.http.scenetest.dao.ApiSceneCaseDao;
import net.tiklab.teston.apitest.http.scenetest.entity.ApiSceneCaseEntity;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneCase;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneCaseQuery;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.ObjectUtils;

/**
* QuartzTestcaseServiceImpl
*/
@Service
public class QuartzTestcaseServiceImpl implements QuartzTestcaseService {

    @Autowired
    QuartzTestcaseDao quartzTestcaseDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    ApiSceneCaseDao apiSceneCase;

    @Override
    public String createQuartzTestcase(@NotNull @Valid List<QuartzTestcase> quartzTestcase) {
        String quartzTestcase1=null;
        for (QuartzTestcase Testcase:quartzTestcase){
            QuartzTestcaseEntity quartzTestcaseEntity = BeanMapper.map(Testcase, QuartzTestcaseEntity.class);
             quartzTestcase1 = quartzTestcaseDao.createQuartzTestcase(quartzTestcaseEntity);
        }

        return quartzTestcase1;
    }

    @Override
    public void updateQuartzTestcase(@NotNull @Valid List<QuartzTestcase> quartzTestcase) {
        List<QuartzTestcaseEntity> quartzTestcaseEntityList = BeanMapper.mapList(quartzTestcase, QuartzTestcaseEntity.class);

            for (QuartzTestcaseEntity Testcase:quartzTestcaseEntityList){
                //查询数据库已存在的定时任务用例
                List<QuartzTestcaseEntity> quartzTestcaseList = quartzTestcaseDao.findQuartzTestcaseList(new QuartzTestcaseQuery().setQuartzMasterId(Testcase.getId()));
                //当修改 传过来的id为空 代表添加
                if (ObjectUtils.isEmpty(Testcase.getId())){
                    quartzTestcaseDao.createQuartzTestcase(Testcase);
                }
                //当修改 传过来的id存在 代表修改
                if (CollectionUtils.isNotEmpty(quartzTestcaseList)){
                    List<String> collect = quartzTestcaseList.stream().map(QuartzTestcaseEntity::getId).collect(Collectors.toList());
                    boolean contains = collect.contains(Testcase.getId());
                    if (!contains){
                        quartzTestcaseDao.updateQuartzTestcase(Testcase);
                    }
                }
            }
    }

    @Override
    public void deleteQuartzTestcase(@NotNull String id) {
        quartzTestcaseDao.deleteQuartzTestcase(id);
    }

    @Override
    public QuartzTestcase findOne(String id) {
        QuartzTestcaseEntity quartzTestcaseEntity = quartzTestcaseDao.findQuartzTestcase(id);

        QuartzTestcase quartzTestcase = BeanMapper.map(quartzTestcaseEntity, QuartzTestcase.class);
        return quartzTestcase;
    }

    @Override
    public List<QuartzTestcase> findList(List<String> idList) {
        List<QuartzTestcaseEntity> quartzTestcaseEntityList =  quartzTestcaseDao.findQuartzTestcaseList(idList);

        List<QuartzTestcase> quartzTestcaseList =  BeanMapper.mapList(quartzTestcaseEntityList,QuartzTestcase.class);

        return quartzTestcaseList;
    }

    @Override
    public QuartzTestcase findQuartzTestcase(@NotNull String id) {
        QuartzTestcase quartzTestcase = findOne(id);

        joinTemplate.joinQuery(quartzTestcase);
        return quartzTestcase;
    }

    @Override
    public List<QuartzTestcase> findAllQuartzTestcase() {
        List<QuartzTestcaseEntity> quartzTestcaseEntityList =  quartzTestcaseDao.findAllQuartzTestcase();

        List<QuartzTestcase> quartzTestcaseList =  BeanMapper.mapList(quartzTestcaseEntityList,QuartzTestcase.class);

        joinTemplate.joinQuery(quartzTestcaseList);
        return quartzTestcaseList;
    }

    @Override
    public List<QuartzTestcase> findQuartzTestcaseList(QuartzTestcaseQuery quartzTestcaseQuery) {
        List<QuartzTestcaseEntity> quartzTestcaseEntityList = quartzTestcaseDao.findQuartzTestcaseList(quartzTestcaseQuery);

        List<QuartzTestcase> quartzTestcaseList = BeanMapper.mapList(quartzTestcaseEntityList,QuartzTestcase.class);
        for (QuartzTestcase quartzTestcase:quartzTestcaseList){
            ApiSceneCaseEntity testCaseEntity = apiSceneCase.findApiSceneCase(quartzTestcase.getTestCase().getId());
            ApiSceneCase testCase = BeanMapper.map(testCaseEntity, ApiSceneCase.class);
            joinTemplate.joinQuery(testCase);
            quartzTestcase.setTestCase(testCase);
        }

        return quartzTestcaseList;
    }

    @Override
    public Pagination<QuartzTestcase> findQuartzTestcasePage(QuartzTestcaseQuery quartzTestcaseQuery) {

        Pagination<QuartzTestcaseEntity>  pagination = quartzTestcaseDao.findQuartzTestcasePage(quartzTestcaseQuery);

        List<QuartzTestcase> quartzTestcaseList = BeanMapper.mapList(pagination.getDataList(),QuartzTestcase.class);

        joinTemplate.joinQuery(quartzTestcaseList);

        return PaginationBuilder.build(pagination,quartzTestcaseList);
    }

    @Override
    public List<ApiSceneCase> findRepositoryTestcaseList(QuartzTestcaseQuery quartzTestcaseQuery) {
        ApiSceneCaseQuery testCaseQuery = new ApiSceneCaseQuery();
//        testCaseQuery.setRepositoryId(quartzTestcaseQuery.getRepositoryId());
//        testCaseQuery.setType(quartzTestcaseQuery.getQuartzType());
        List<ApiSceneCaseEntity> testCaseList = apiSceneCase.findApiSceneCaseList(testCaseQuery);
        List<ApiSceneCase> testCases = BeanMapper.mapList(testCaseList, ApiSceneCase.class);
        joinTemplate.joinQuery(testCases);
        return testCases;
    }


}