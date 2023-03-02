package net.tiklab.teston.apix.http.unit.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.apix.http.unit.cases.model.FormParam;
import net.tiklab.teston.apix.http.unit.cases.model.FormParamQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* form-data 服务接口
*/
public interface FormParamService {

    /**
    * 创建form-data
    * @param formParam
    * @return
    */
    String createFormParam(@NotNull @Valid FormParam formParam);

    /**
    * 更新form-data
    * @param formParam
    */
    void updateFormParam(@NotNull @Valid FormParam formParam);

    /**
    * 删除form-data
    * @param id
    */
    void deleteFormParam(@NotNull String id);

    FormParam findOne(@NotNull String id);

    List<FormParam> findList(List<String> idList);

    /**
    * 根据id查找form-data
    * @param id
    * @return
    */
    FormParam findFormParam(@NotNull String id);

    /**
    * 查找所有form-data
    * @return
    */
    List<FormParam> findAllFormParam();

    /**
    * 根据查询参数查询form-data列表
    * @param formParamQuery
    * @return
    */
    List<FormParam> findFormParamList(FormParamQuery formParamQuery);

    /**
    * 根据查询参数按分页查询form-data
    * @param formParamQuery
    * @return
    */
    Pagination<FormParam> findFormParamPage(FormParamQuery formParamQuery);

}