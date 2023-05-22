package io.tiklab.teston.test.apix.http.unit.cases.service;

import io.tiklab.teston.test.apix.http.unit.cases.model.FormUrlEncoded;
import io.tiklab.teston.test.apix.http.unit.cases.model.FormUrlencodedQuery;
import io.tiklab.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* form-urlencoded 服务接口
*/
public interface FormUrlencodedService {

    /**
    * 创建form-urlencoded
    * @param formUrlencoded
    * @return
    */
    String createFormUrlencoded(@NotNull @Valid FormUrlEncoded formUrlencoded);

    /**
    * 更新form-urlencoded
    * @param formUrlencoded
    */
    void updateFormUrlencoded(@NotNull @Valid FormUrlEncoded formUrlencoded);

    /**
    * 删除form-urlencoded
    * @param id
    */
    void deleteFormUrlencoded(@NotNull String id);

    FormUrlEncoded findOne(@NotNull String id);

    List<FormUrlEncoded> findList(List<String> idList);

    /**
    * 根据id查找form-urlencoded
    * @param id
    * @return
    */
    FormUrlEncoded findFormUrlencoded(@NotNull String id);

    /**
    * 查找所有form-urlencoded
    * @return
    */
    List<FormUrlEncoded> findAllFormUrlencoded();

    /**
    * 查询form-urlencoded 列表
    * @param formUrlencodedQuery
    * @return
    */
    List<FormUrlEncoded> findFormUrlencodedList(FormUrlencodedQuery formUrlencodedQuery);

    /**
    * 按分页查询form-urlencoded
    * @param formUrlencodedQuery
    * @return
    */
    Pagination<FormUrlEncoded> findFormUrlencodedPage(FormUrlencodedQuery formUrlencodedQuery);

}