package io.thoughtware.teston.test.func.service;

import io.thoughtware.teston.test.common.stepcommon.service.StepCommonService;
import io.thoughtware.teston.test.func.model.FuncUnitCase;
import io.thoughtware.teston.test.func.model.FuncUnitCaseQuery;
import io.thoughtware.teston.test.test.model.TestCase;
import io.thoughtware.teston.test.test.model.TestCaseQuery;
import io.thoughtware.teston.test.test.service.TestCaseService;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.teston.category.model.Category;
import io.thoughtware.teston.category.service.CategoryService;
import io.thoughtware.teston.test.func.dao.FuncUnitCaseDao;
import io.thoughtware.teston.test.func.entity.FuncUnitCaseEntity;
import io.thoughtware.user.user.model.User;
import io.thoughtware.user.user.service.UserService;
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

        testCaseService.deleteTestCase(id);
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