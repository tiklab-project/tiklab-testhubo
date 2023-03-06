package net.tiklab.teston.test.apix.http.unit.instance.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.test.apix.http.unit.instance.model.AssertInstanceQuery;
import net.tiklab.teston.test.apix.http.unit.instance.model.AssertInstance;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 断言实例 服务接口
*/
public interface AssertInstanceService {

    /**
    * 创建断言实例
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
    * 删除断言实例
    * @param id
    */
    void deleteAssertInstance(@NotNull String id);

    AssertInstance findOne(@NotNull String id);

    List<AssertInstance> findList(List<String> idList);

    /**
    * 根据id查找断言实例
    * @param id
    * @return
    */
    AssertInstance findAssertInstance(@NotNull String id);

    /**
    * 查找所有断言实例
    * @return
    */
    List<AssertInstance> findAllAssertInstance();

    /**
    * 根据查询参数查询断言实例列表
    * @param assertInstanceQuery
    * @return
    */
    List<AssertInstance> findAssertInstanceList(AssertInstanceQuery assertInstanceQuery);

    /**
    * 根据查询参数按分页查询断言实例
    * @param assertInstanceQuery
    * @return
    */
    Pagination<AssertInstance> findAssertInstancePage(AssertInstanceQuery assertInstanceQuery);

}