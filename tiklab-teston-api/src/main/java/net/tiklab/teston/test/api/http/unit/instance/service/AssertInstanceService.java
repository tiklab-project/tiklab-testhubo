package net.tiklab.teston.test.api.http.unit.instance.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.test.api.http.unit.instance.model.AssertInstance;
import net.tiklab.teston.test.api.http.unit.instance.model.AssertInstanceQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* AssertInstanceService
*/
public interface AssertInstanceService {

    /**
    * 创建
    * @param assertInstance
    * @return
    */
    String createAssertInstance(@NotNull @Valid AssertInstance assertInstance);

    /**
    * 更新
    * @param assertInstance
    */
    void updateAssertInstance(@NotNull @Valid AssertInstance assertInstance);

    /**
    * 删除
    * @param id
    */
    void deleteAssertInstance(@NotNull String id);

    AssertInstance findOne(@NotNull String id);

    List<AssertInstance> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    AssertInstance findAssertInstance(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<AssertInstance> findAllAssertInstance();

    /**
    * 查询列表
    * @param assertInstanceQuery
    * @return
    */
    List<AssertInstance> findAssertInstanceList(AssertInstanceQuery assertInstanceQuery);

    /**
    * 按分页查询
    * @param assertInstanceQuery
    * @return
    */
    Pagination<AssertInstance> findAssertInstancePage(AssertInstanceQuery assertInstanceQuery);

}