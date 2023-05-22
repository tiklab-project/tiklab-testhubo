package io.tiklab.teston.test.apix.http.unit.cases.service;

import io.tiklab.teston.test.apix.http.unit.cases.model.FormParams;
import io.tiklab.core.page.Pagination;
import io.tiklab.teston.test.apix.http.unit.cases.model.FormParamQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* form-data 服务接口
*/
public interface FormParamService {

    /**
    * 创建form-data
    * @param formParams
    * @return
    */
    String createFormParam(@NotNull @Valid FormParams formParams);

    /**
    * 更新form-data
    * @param formParams
    */
    void updateFormParam(@NotNull @Valid FormParams formParams);

    /**
    * 删除form-data
    * @param id
    */
    void deleteFormParam(@NotNull String id);

    FormParams findOne(@NotNull String id);

    List<FormParams> findList(List<String> idList);

    /**
    * 根据id查找form-data
    * @param id
    * @return
    */
    FormParams findFormParam(@NotNull String id);

    /**
    * 查找所有form-data
    * @return
    */
    List<FormParams> findAllFormParam();

    /**
    * 根据查询参数查询form-data列表
    * @param formParamQuery
    * @return
    */
    List<FormParams> findFormParamList(FormParamQuery formParamQuery);

    /**
    * 根据查询参数按分页查询form-data
    * @param formParamQuery
    * @return
    */
    Pagination<FormParams> findFormParamPage(FormParamQuery formParamQuery);

}