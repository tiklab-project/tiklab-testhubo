package io.tiklab.teston.category.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.teston.category.model.Categorys;
import io.tiklab.teston.category.model.CategoryQuery;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 目录 服务接口
*/
@JoinProvider(model = Categorys.class)
public interface CategoryService {

    /**
    * 创建目录
    * @param categorys
    * @return
    */
    String createCategory(@NotNull @Valid Categorys categorys);

    /**
    * 更新目录
    * @param categorys
    */
    void updateCategory(@NotNull @Valid Categorys categorys);

    /**
    * 删除目录
    * @param id
    */
    void deleteCategory(@NotNull String id);
    @FindOne
    Categorys findOne(@NotNull String id);
    @FindList
    List<Categorys> findList(List<String> idList);

    /**
    * 根据id查找目录
    * @param id
    * @return
    */
    Categorys findCategory(@NotNull String id);

    /**
    * 查找所有目录
    * @return
    */
    @FindAll
    List<Categorys> findAllCategory();

    /**
    * 查询目录列表
    * @param categoryQuery
    * @return
    */
    List<Categorys> findCategoryList(CategoryQuery categoryQuery);

    /**
    * 按分页查询目录
    * @param categoryQuery
    * @return
    */
    Pagination<Categorys> findCategoryPage(CategoryQuery categoryQuery);
    /**
     * 通过查询对象查询分组树
     * 带用例
     * @param categoryQuery
     * @return
     */
    List<Categorys> findCategoryListTree(CategoryQuery categoryQuery);

    /**
     * 通过查询对象查询分组树
     * 不带用例
     * @param categoryQuery
     * @return
     */
    List<Categorys> findCategoryListTreeTable(CategoryQuery categoryQuery);
}