package net.tiklab.testonapix.http.unit.instance.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.testonapix.http.unit.instance.model.ResponseInstance;
import net.tiklab.testonapix.http.unit.instance.model.ResponseInstanceQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* ResponseInstanceService
*/
public interface ResponseInstanceService {

    /**
    * 创建
    * @param responseInstance
    * @return
    */
    String createResponseInstance(@NotNull @Valid ResponseInstance responseInstance);

    /**
    * 更新
    * @param responseInstance
    */
    void updateResponseInstance(@NotNull @Valid ResponseInstance responseInstance);

    /**
    * 删除
    * @param id
    */
    void deleteResponseInstance(@NotNull String id);

    ResponseInstance findOne(@NotNull String id);

    List<ResponseInstance> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    ResponseInstance findResponseInstance(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<ResponseInstance> findAllResponseInstance();

    /**
    * 查询列表
    * @param responseInstanceQuery
    * @return
    */
    List<ResponseInstance> findResponseInstanceList(ResponseInstanceQuery responseInstanceQuery);

    /**
    * 按分页查询
    * @param responseInstanceQuery
    * @return
    */
    Pagination<ResponseInstance> findResponseInstancePage(ResponseInstanceQuery responseInstanceQuery);

}