package net.tiklab.teston.apitest.http.unittest.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitCase;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitCaseExt;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitCaseQuery;
import net.tiklab.teston.testcase.model.TestCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* StepService
*/
@JoinProvider(model = ApiUnitCase.class)
public interface ApiUnitCaseService {

    /**
    * 创建
    * @param unitCase
    * @return
    */
    String createApiUnitCase(@NotNull @Valid ApiUnitCase unitCase);

    /**
    * 更新
    * @param unitCase
    */
    void updateApiUnitCase(@NotNull @Valid ApiUnitCase unitCase);

    /**
    * 删除
    * @param id
    */
    void deleteApiUnitCase(@NotNull String id);

    @FindOne
    ApiUnitCase findOne(@NotNull String id);

    @FindList
    List<ApiUnitCase> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    ApiUnitCase findApiUnitCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<ApiUnitCase> findAllApiUnitCase();

    /**
    * 查询列表
    * @param apiUnitCaseQuery
    * @return
    */
    List<ApiUnitCase> findApiUnitCaseList(ApiUnitCaseQuery apiUnitCaseQuery);

    /**
    * 按分页查询
    * @param apiUnitCaseQuery
    * @return
    */
    Pagination<ApiUnitCase> findApiUnitCasePage(ApiUnitCaseQuery apiUnitCaseQuery);

    /**
     * -------------for exec-----------
     */
    ApiUnitCaseExt findApiUnitCaseExt(ApiUnitCase apiUnitCase);

    /**
     * 通过testCaseQuery查询
     * @param testCaseQuery
     * @return
     */
     List<ApiUnitCase> findApiUnitCaseListByTestCase(TestCaseQuery testCaseQuery);

}