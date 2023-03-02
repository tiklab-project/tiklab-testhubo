package net.tiklab.teston.manager.testplan.instance.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.manager.testplan.instance.model.TestPlanInstance;
import net.tiklab.teston.manager.testplan.instance.model.TestPlanInstanceQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 测试计划实例 服务接口
*/
@JoinProvider(model = TestPlanInstance.class)
public interface TestPlanInstanceService {

    /**
    * 创建测试计划实例
    * @param testPlanInstance
    * @return
    */
    String createTestPlanInstance(@NotNull @Valid TestPlanInstance testPlanInstance);

    /**
    * 更新测试计划实例
    * @param testPlanInstance
    */
    void updateTestPlanInstance(@NotNull @Valid TestPlanInstance testPlanInstance);

    /**
    * 删除测试计划实例
    * @param id
    */
    void deleteTestPlanInstance(@NotNull String id);

    @FindOne
    TestPlanInstance findOne(@NotNull String id);

    @FindList
    List<TestPlanInstance> findList(List<String> idList);

    /**
    * 根据id查找测试计划实例
    * @param id
    * @return
    */
    TestPlanInstance findTestPlanInstance(@NotNull String id);

    /**
    * 查找所有测试计划实例
    * @return
    */
    @FindAll
    List<TestPlanInstance> findAllTestPlanInstance();

    /**
    * 根据查询参数查询测试计划实例列表
    * @param testPlanInstanceQuery
    * @return
    */
    List<TestPlanInstance> findTestPlanInstanceList(TestPlanInstanceQuery testPlanInstanceQuery);

    /**
    * 根据查询参数按分页查询测试计划实例
    * @param testPlanInstanceQuery
    * @return
    */
    Pagination<TestPlanInstance> findTestPlanInstancePage(TestPlanInstanceQuery testPlanInstanceQuery);

}