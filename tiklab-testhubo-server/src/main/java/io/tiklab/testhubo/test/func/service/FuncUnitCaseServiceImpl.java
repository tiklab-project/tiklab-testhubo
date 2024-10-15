package io.tiklab.testhubo.test.func.service;

import io.tiklab.testhubo.integrated.teamwire.workItemBind.service.WorkItemBindService;
import io.tiklab.testhubo.test.common.stepcommon.service.StepCommonService;
import io.tiklab.testhubo.test.func.model.FuncUnitCase;
import io.tiklab.testhubo.test.func.model.FuncUnitCaseQuery;
import io.tiklab.testhubo.test.test.model.TestCase;
import io.tiklab.testhubo.test.test.model.TestCaseQuery;
import io.tiklab.testhubo.test.test.service.TestCaseService;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.testhubo.category.model.Category;
import io.tiklab.testhubo.category.service.CategoryService;
import io.tiklab.testhubo.test.func.dao.FuncUnitCaseDao;
import io.tiklab.testhubo.test.func.entity.FuncUnitCaseEntity;
import io.tiklab.user.user.model.User;
import io.tiklab.user.user.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
* 功能用例 服务
*/
@Service
public class FuncUnitCaseServiceImpl implements FuncUnitCaseService {

    @Autowired
    FuncUnitCaseDao funcUnitCaseDao;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @Autowired
    StepCommonService stepCommonService;

    @Autowired
    WorkItemBindService workItemBindService;

    @Override
    public String createFuncUnitCase(@NotNull @Valid FuncUnitCase funcUnitCase) {
        FuncUnitCaseEntity funcUnitCaseEntity = BeanMapper.map(funcUnitCase, FuncUnitCaseEntity.class);
        String id = funcUnitCaseDao.createFuncUnitCase(funcUnitCaseEntity);
        
        funcUnitCaseEntity.setTestCaseId(id);
        funcUnitCaseEntity.setId(id);
        funcUnitCaseDao.updateFuncUnitCase(funcUnitCaseEntity);

        TestCase testCase = funcUnitCase.getTestCase();
        testCase.setId(id);
        testCaseService.createTestCase(testCase);
        
        return id;
    }

    @Override
    public void updateFuncUnitCase(@NotNull @Valid FuncUnitCase funcUnitCase) {
        FuncUnitCaseEntity funcUnitCaseEntity = BeanMapper.map(funcUnitCase, FuncUnitCaseEntity.class);

        funcUnitCaseDao.updateFuncUnitCase(funcUnitCaseEntity);

        testCaseService.updateTestCase(funcUnitCase.getTestCase());
    }

    @Override
    public void deleteFuncUnitCase(@NotNull String id) {
        funcUnitCaseDao.deleteFuncUnitCase(id);

        stepCommonService.deleteAllStepCommon(id);

        workItemBindService.deleteAllWorkItemBind(id);
    }

    @Override
    public FuncUnitCase findOne(String id) {
        FuncUnitCaseEntity funcUnitCaseEntity = funcUnitCaseDao.findFuncUnitCase(id);

        FuncUnitCase funcUnitCase = BeanMapper.map(funcUnitCaseEntity, FuncUnitCase.class);
        return funcUnitCase;
    }

    @Override
    public List<FuncUnitCase> findList(List<String> idList) {
        List<FuncUnitCaseEntity> funcUnitCaseEntityList =  funcUnitCaseDao.findFuncUnitCaseList(idList);

        List<FuncUnitCase> funcUnitCaseList =  BeanMapper.mapList(funcUnitCaseEntityList, FuncUnitCase.class);
        return funcUnitCaseList;
    }

    @Override
    public FuncUnitCase findFuncUnitCase(@NotNull String id) {
        FuncUnitCase funcUnitCase = findOne(id);

        joinTemplate.joinQuery(funcUnitCase);

        int funcSceneStepNum = stepCommonService.findStepNum(id);
        funcUnitCase.setStepNum(funcSceneStepNum);

        int totalNum = workItemBindService.findTotalNum(id);
        funcUnitCase.setDefectNum(totalNum);

        //手动添加字段
        TestCase testCase = funcUnitCase.getTestCase();
        if(testCase.getCategory()!=null){
            Category category = categoryService.findCategory(testCase.getCategory().getId());
            funcUnitCase.getTestCase().setCategory(category);
        }
        if(testCase.getUpdateUser()!=null){
            User updateUser = userService.findUser(testCase.getUpdateUser().getId());
            funcUnitCase.getTestCase().setUpdateUser(updateUser);
        }


        return funcUnitCase;
    }

    @Override
    public List<FuncUnitCase> findAllFuncUnitCase() {
        List<FuncUnitCaseEntity> funcUnitCaseEntityList =  funcUnitCaseDao.findAllFuncUnitCase();

        List<FuncUnitCase> funcUnitCaseList =  BeanMapper.mapList(funcUnitCaseEntityList, FuncUnitCase.class);

        joinTemplate.joinQuery(funcUnitCaseList);
        return funcUnitCaseList;
    }

    @Override
    public List<FuncUnitCase> findFuncUnitCaseList(FuncUnitCaseQuery functionTestCaseQuery) {
        List<FuncUnitCaseEntity> funcUnitCaseEntityList = funcUnitCaseDao.findFuncUnitCaseList(functionTestCaseQuery);

        List<FuncUnitCase> funcUnitCaseList = BeanMapper.mapList(funcUnitCaseEntityList, FuncUnitCase.class);

        joinTemplate.joinQuery(funcUnitCaseList);

        return funcUnitCaseList;
    }

    @Override
    public Pagination<FuncUnitCase> findFuncUnitCasePage(FuncUnitCaseQuery functionTestCaseQuery) {
        Pagination<FuncUnitCaseEntity>  pagination = funcUnitCaseDao.findFuncUnitCasePage(functionTestCaseQuery);

        List<FuncUnitCase> funcUnitCaseList = BeanMapper.mapList(pagination.getDataList(), FuncUnitCase.class);

        joinTemplate.joinQuery(funcUnitCaseList);

        return PaginationBuilder.build(pagination,funcUnitCaseList);
    }

    @Override
    public List<FuncUnitCase> findFuncUnitCaseListByTestCase(TestCaseQuery testCaseQuery) {
        List<TestCase> testCaseList = testCaseService.findTestCaseList(testCaseQuery);

        List<FuncUnitCase> funcUnitCaseList = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(testCaseList)){
            for(TestCase testCase : testCaseList){
                FuncUnitCase funcUnitCase = findFuncUnitCase(testCase.getId());

                if(!ObjectUtils.isEmpty(funcUnitCase)){
                    funcUnitCaseList.add(funcUnitCase);
                }
            }
        }

        return funcUnitCaseList;
    }
}