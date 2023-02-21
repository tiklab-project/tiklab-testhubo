package net.tiklab.teston.apitest.http.unittest.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.teston.apitest.http.unittest.model.FormParam;
import net.tiklab.teston.apitest.http.unittest.model.FormParamQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* FormParamService
*/
public interface FormParamService {

    /**
    * 创建
    * @param formParam
    * @return
    */
    String createFormParam(@NotNull @Valid FormParam formParam);

    /**
    * 更新
    * @param formParam
    */
    void updateFormParam(@NotNull @Valid FormParam formParam);

    /**
    * 删除
    * @param id
    */
    void deleteFormParam(@NotNull String id);

    FormParam findOne(@NotNull String id);

    List<FormParam> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    FormParam findFormParam(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<FormParam> findAllFormParam();

    /**
    * 查询列表
    * @param formParamQuery
    * @return
    */
    List<FormParam> findFormParamList(FormParamQuery formParamQuery);

    /**
    * 按分页查询
    * @param formParamQuery
    * @return
    */
    Pagination<FormParam> findFormParamPage(FormParamQuery formParamQuery);

}