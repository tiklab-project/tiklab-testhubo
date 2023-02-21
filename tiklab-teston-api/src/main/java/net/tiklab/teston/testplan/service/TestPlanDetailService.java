package net.tiklab.teston.testplan.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.teston.testcase.model.TestCase;
import net.tiklab.teston.testplan.model.TestPlanDetail;
import net.tiklab.teston.testplan.model.TestPlanDetailQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* TestPlanDetailService
*/
public interface TestPlanDetailService {

    /**
    * 创建
    * @param testPlanDetail
    * @return
    */
    String createTestPlanDetail(@NotNull @Valid TestPlanDetail testPlanDetail);

    /**
    * 更新
    * @param testPlanDetail
    */
    void updateTestPlanDetail(@NotNull @Valid TestPlanDetail testPlanDetail);

    /**
    * 删除
    * @param id
    */
    void deleteTestPlanDetail(@NotNull String id);

    TestPlanDetail findOne(@NotNull String id);

    List<TestPlanDetail> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    TestPlanDetail findTestPlanDetail(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<TestPlanDetail> findAllTestPlanDetail();

    /**
    * 查询列表
    * @param testPlanDetailQuery
    * @return
    */
    List<TestPlanDetail> findTestPlanDetailList(TestPlanDetailQuery testPlanDetailQuery);

    /**
    * 按分页查询
    * @param testPlanDetailQuery
    * @return
    */
    Pagination<TestPlanDetail> findTestPlanDetailPage(TestPlanDetailQuery testPlanDetailQuery);
    /**
     * 添加用例弹窗列表
     * @param testPlanDetail
     * @return
     */
    Pagination<TestCase> findTesCaseList(TestPlanDetail testPlanDetail);

    /**
     * 查询已经关联的用例
     * @param testPlanDetailQuery
     * @return
     */
    Pagination<TestPlanDetail> findBindTestCaseList(TestPlanDetailQuery testPlanDetailQuery);
}