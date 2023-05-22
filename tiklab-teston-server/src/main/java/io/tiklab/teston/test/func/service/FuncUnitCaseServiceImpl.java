package io.tiklab.teston.test.func.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.category.model.Categorys;
import io.tiklab.teston.category.service.CategoryService;
import io.tiklab.teston.test.func.dao.FuncUnitCaseDao;
import io.tiklab.teston.test.func.entity.FuncUnitCaseEntity;
import io.tiklab.teston.test.test.model.TestCases;
import io.tiklab.teston.test.test.model.TestCaseQuery;
import io.tiklab.teston.test.test.service.TestCaseService;
import io.tiklab.teston.test.func.model.FuncUnitCase;
import io.tiklab.teston.test.func.model.FuncUnitCaseQuery;
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

    @Override
    public String createFuncUnitCase(@NotNull @Valid FuncUnitCase funcUnitCase) {
        FuncUnitCaseEntity funcUnitCaseEntity = BeanMapper.map(funcUnitCase, FuncUnitCaseEntity.class);
        String id = funcUnitCaseDao.createFuncUnitCase(funcUnitCaseEntity);
        
        funcUnitCaseEntity.setTestCaseId(id);
        funcUnitCaseEntity.setId(id);
        funcUnitCaseDao.updateFuncUnitCase(funcUnitCaseEntity);

        TestCases testCases = funcUnitCase.getTestCase();
        testCases.setId(id);
        testCaseService.createTestCase(testCases);
        
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

        //手动添加字段
        TestCases testCases = funcUnitCase.getTestCase();
        if(testCases.getCategory()!=null){
            Categorys categorys = categoryService.findCategory(testCases.getCategory().getId());
            funcUnitCase.getTestCase().setCategory(categorys);
        }
        if(testCases.getUpdateUser()!=null){
            User updateUser = userService.findUser(testCases.getUpdateUser().getId());
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
        List<TestCases> testCasesList = testCaseService.findTestCaseList(testCaseQuery);

        List<FuncUnitCase> funcUnitCaseList = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(testCasesList)){
            for(TestCases testCases : testCasesList){
                FuncUnitCase funcUnitCase = findFuncUnitCase(testCases.getId());

                if(!ObjectUtils.isEmpty(funcUnitCase)){
                    funcUnitCaseList.add(funcUnitCase);
                }
            }
        }

        return funcUnitCaseList;
    }
}