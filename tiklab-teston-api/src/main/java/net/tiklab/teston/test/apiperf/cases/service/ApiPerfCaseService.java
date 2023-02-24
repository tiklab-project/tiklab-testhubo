package net.tiklab.teston.test.apiperf.cases.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.test.apiperf.cases.model.ApiPerfCase;
import net.tiklab.teston.test.apiperf.cases.model.ApiPerfCaseQuery;
import net.tiklab.teston.test.testcase.model.TestCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* ApiPerfCaseService
*/
@JoinProvider(model = ApiPerfCase.class)
public interface ApiPerfCaseService {

    /**
    * 创建
    * @param perfCase
    * @return
    */
    String createApiPerfCase(@NotNull @Valid ApiPerfCase perfCase);

    /**
    * 更新
    * @param perfCase
    */
    void updateApiPerfCase(@NotNull @Valid ApiPerfCase perfCase);

    /**
    * 删除
    * @param id
    */
    void deleteApiPerfCase(@NotNull String id);

    @FindOne
    ApiPerfCase findOne(@NotNull String id);

    @FindList
    List<ApiPerfCase> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    ApiPerfCase findApiPerfCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<ApiPerfCase> findAllApiPerfCase();

    /**
    * 查询列表
    * @param performanceTestQuery
    * @return
    */
    List<ApiPerfCase> findApiPerfCaseList(ApiPerfCaseQuery performanceTestQuery);

    /**
    * 按分页查询
    * @param performanceTestQuery
    * @return
    */
    Pagination<ApiPerfCase> findApiPerfCasePage(ApiPerfCaseQuery performanceTestQuery);


    /**
     * 通过testCaseQuery查询
     * @param testCaseQuery
     * @return
     */
    List<ApiPerfCase> findApiPerfCaseListByTestCase(TestCaseQuery testCaseQuery);


}