package net.tiklab.teston.testplan.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.testplan.model.TestPlanCaseInstanceBind;
import net.tiklab.teston.testplan.model.TestPlanCaseInstanceBindQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* TestPlanCaseInstanceBindService
*/
public interface TestPlanCaseInstanceBindService {

    /**
    * 创建
    * @param testPlanCaseInstanceBind
    * @return
    */
    String createTestPlanCaseInstanceBind(@NotNull @Valid TestPlanCaseInstanceBind testPlanCaseInstanceBind);

    /**
    * 更新
    * @param testPlanCaseInstanceBind
    */
    void updateTestPlanCaseInstanceBind(@NotNull @Valid TestPlanCaseInstanceBind testPlanCaseInstanceBind);

    /**
    * 删除
    * @param id
    */
    void deleteTestPlanCaseInstanceBind(@NotNull String id);

    TestPlanCaseInstanceBind findOne(@NotNull String id);

    List<TestPlanCaseInstanceBind> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    TestPlanCaseInstanceBind findTestPlanCaseInstanceBind(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<TestPlanCaseInstanceBind> findAllTestPlanCaseInstanceBind();

    /**
    * 查询列表
    * @param testPlanCaseInstanceBindQuery
    * @return
    */
    List<TestPlanCaseInstanceBind> findTestPlanCaseInstanceBindList(TestPlanCaseInstanceBindQuery testPlanCaseInstanceBindQuery);

    /**
    * 按分页查询
    * @param testPlanCaseInstanceBindQuery
    * @return
    */
    Pagination<TestPlanCaseInstanceBind> findTestPlanCaseInstanceBindPage(TestPlanCaseInstanceBindQuery testPlanCaseInstanceBindQuery);

}