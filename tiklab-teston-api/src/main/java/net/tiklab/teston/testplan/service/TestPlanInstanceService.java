package net.tiklab.teston.testplan.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.testplan.model.TestPlanInstance;
import net.tiklab.teston.testplan.model.TestPlanInstanceQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* TestPlanInstanceService
*/
@JoinProvider(model = TestPlanInstance.class)
public interface TestPlanInstanceService {

    /**
    * 创建
    * @param testPlanInstance
    * @return
    */
    String createTestPlanInstance(@NotNull @Valid TestPlanInstance testPlanInstance);

    /**
    * 更新
    * @param testPlanInstance
    */
    void updateTestPlanInstance(@NotNull @Valid TestPlanInstance testPlanInstance);

    /**
    * 删除
    * @param id
    */
    void deleteTestPlanInstance(@NotNull String id);

    @FindOne
    TestPlanInstance findOne(@NotNull String id);

    @FindList
    List<TestPlanInstance> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    TestPlanInstance findTestPlanInstance(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<TestPlanInstance> findAllTestPlanInstance();

    /**
    * 查询列表
    * @param testPlanInstanceQuery
    * @return
    */
    List<TestPlanInstance> findTestPlanInstanceList(TestPlanInstanceQuery testPlanInstanceQuery);

    /**
    * 按分页查询
    * @param testPlanInstanceQuery
    * @return
    */
    Pagination<TestPlanInstance> findTestPlanInstancePage(TestPlanInstanceQuery testPlanInstanceQuery);

}