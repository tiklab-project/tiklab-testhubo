package net.tiklab.teston.apix.http.unit.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.apix.http.unit.cases.model.FormUrlencoded;
import net.tiklab.teston.apix.http.unit.cases.model.FormUrlencodedQuery;

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
    String createFormUrlencoded(@NotNull @Valid FormUrlencoded formUrlencoded);

    /**
    * 更新form-urlencoded
    * @param formUrlencoded
    */
    void updateFormUrlencoded(@NotNull @Valid FormUrlencoded formUrlencoded);

    /**
    * 删除form-urlencoded
    * @param id
    */
    void deleteFormUrlencoded(@NotNull String id);

    FormUrlencoded findOne(@NotNull String id);

    List<FormUrlencoded> findList(List<String> idList);

    /**
    * 根据id查找form-urlencoded
    * @param id
    * @return
    */
    FormUrlencoded findFormUrlencoded(@NotNull String id);

    /**
    * 查找所有form-urlencoded
    * @return
    */
    List<FormUrlencoded> findAllFormUrlencoded();

    /**
    * 查询form-urlencoded 列表
    * @param formUrlencodedQuery
    * @return
    */
    List<FormUrlencoded> findFormUrlencodedList(FormUrlencodedQuery formUrlencodedQuery);

    /**
    * 按分页查询form-urlencoded
    * @param formUrlencodedQuery
    * @return
    */
    Pagination<FormUrlencoded> findFormUrlencodedPage(FormUrlencodedQuery formUrlencodedQuery);

}