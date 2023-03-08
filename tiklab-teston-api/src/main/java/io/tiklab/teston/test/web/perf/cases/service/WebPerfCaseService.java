package io.tiklab.teston.test.web.perf.cases.service;

import io.tiklab.teston.test.web.perf.cases.model.WebPerfCase;
import io.tiklab.teston.test.web.perf.cases.model.WebPerfCaseQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.teston.test.test.model.TestCaseQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* web性能用例 服务接口
*/
@JoinProvider(model = WebPerfCase.class)
public interface WebPerfCaseService {

    /**
    * 创建web性能用例
    * @param webPerfCase
    * @return
    */
    String createWebPerfCase(@NotNull @Valid WebPerfCase webPerfCase);

    /**
    * 更新web性能用例
    * @param webPerfCase
    */
    void updateWebPerfCase(@NotNull @Valid WebPerfCase webPerfCase);

    /**
    * 删除web性能用例
    * @param id
    */
    void deleteWebPerfCase(@NotNull String id);

    @FindOne
    WebPerfCase findOne(@NotNull String id);

    @FindList
    List<WebPerfCase> findList(List<String> idList);

    /**
    * 根据id查找web性能用例
    * @param id
    * @return
    */
    WebPerfCase findWebPerfCase(@NotNull String id);

    /**
    * 查找所有web性能用例
    * @return
    */
    @FindAll
    List<WebPerfCase> findAllWebPerfCase();

    /**
    * 根据查询参数查询web性能用例列表
    * @param webPerfCaseQuery
    * @return
    */
    List<WebPerfCase> findWebPerfCaseList(WebPerfCaseQuery webPerfCaseQuery);

    /**
    * 根据查询参数按分页查询web性能用例
    * @param webPerfCaseQuery
    * @return
    */
    Pagination<WebPerfCase> findWebPerfCasePage(WebPerfCaseQuery webPerfCaseQuery);

    /**
     * 通过testcase查询web性能用例
     * @param testCaseQuery
     * @return
     */
    List<WebPerfCase> findWebPerfCaseListByTestCase(TestCaseQuery testCaseQuery);

}