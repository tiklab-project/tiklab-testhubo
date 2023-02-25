package net.tiklab.testonweb.perf.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.test.testcase.model.TestCaseQuery;
import net.tiklab.testonweb.perf.cases.model.WebPerfCase;
import net.tiklab.testonweb.perf.cases.model.WebPerfCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* WebPerfCaseService
*/
@JoinProvider(model = WebPerfCase.class)
public interface WebPerfCaseService {

    /**
    * 创建
    * @param webPerfCase
    * @return
    */
    String createWebPerfCase(@NotNull @Valid WebPerfCase webPerfCase);

    /**
    * 更新
    * @param webPerfCase
    */
    void updateWebPerfCase(@NotNull @Valid WebPerfCase webPerfCase);

    /**
    * 删除
    * @param id
    */
    void deleteWebPerfCase(@NotNull String id);

    @FindOne
    WebPerfCase findOne(@NotNull String id);

    @FindList
    List<WebPerfCase> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    WebPerfCase findWebPerfCase(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<WebPerfCase> findAllWebPerfCase();

    /**
    * 查询列表
    * @param webPerfCaseQuery
    * @return
    */
    List<WebPerfCase> findWebPerfCaseList(WebPerfCaseQuery webPerfCaseQuery);

    /**
    * 按分页查询
    * @param webPerfCaseQuery
    * @return
    */
    Pagination<WebPerfCase> findWebPerfCasePage(WebPerfCaseQuery webPerfCaseQuery);

    /**
     * 通过testcase查询
     * @param testCaseQuery
     * @return
     */
    List<WebPerfCase> findWebPerfCaseListByTestCase(TestCaseQuery testCaseQuery);

}