package net.tiklab.teston.functest.unittest.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.functest.unittest.dao.FuncUnitCaseDao;
import net.tiklab.teston.functest.unittest.entity.FuncUnitCaseEntity;
import net.tiklab.teston.functest.unittest.model.FuncUnitCase;
import net.tiklab.teston.functest.unittest.model.FuncUnitCaseQuery;
import net.tiklab.teston.testcase.model.TestCase;
import net.tiklab.teston.testcase.model.TestCaseQuery;
import net.tiklab.teston.testcase.service.TestCaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
* FuncUnitCaseServiceImpl
*/
@Service
public class FuncUnitCaseServiceImpl implements FuncUnitCaseService {

    @Autowired
    FuncUnitCaseDao funcUnitCaseDao;

    @Autowired
    TestCaseService testCaseService;

    @Autowired
    JoinTemplate joinTemplate;

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
            for(TestCase testCase:testCaseList){
                FuncUnitCase funcUnitCase = findFuncUnitCase(testCase.getId());

                if(!ObjectUtils.isEmpty(funcUnitCase)){
                    funcUnitCaseList.add(funcUnitCase);
                }
            }
        }

        return funcUnitCaseList;
    }
}