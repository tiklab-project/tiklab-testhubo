package io.tiklab.teston.category.service;

import io.tiklab.core.page.Pagination;

import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.teston.category.model.Category;
import io.tiklab.teston.category.model.CategoryQuery;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 目录 服务接口
*/
@JoinProvider(model = Category.class)
public interface CategoryService {

    /**
    * 创建目录
    * @param category
    * @return
    */
    String createCategory(@NotNull @Valid Category category);

    /**
    * 更新目录
    * @param category
    */
    void updateCategory(@NotNull @Valid Category category);

    /**
    * 删除目录
    * @param id
    */
    void deleteCategory(@NotNull String id);
    @FindOne
    Category findOne(@NotNull String id);
    @FindList
    List<Category> findList(List<String> idList);

    /**
    * 根据id查找目录
    * @param id
    * @return
    */
    Category findCategory(@NotNull String id);

    /**
    * 查找所有目录
    * @return
    */
    @FindAll
    List<Category> findAllCategory();

    /**
    * 查询目录列表
    * @param categoryQuery
    * @return
    */
    List<Category> findCategoryList(CategoryQuery categoryQuery);

    /**
    * 按分页查询目录
    * @param categoryQuery
    * @return
    */
    Pagination<Category> findCategoryPage(CategoryQuery categoryQuery);
    /**
     * 通过查询对象查询分组树
     * 带用例
     * @param categoryQuery
     * @return
     */
    List<Category> findCategoryListTree(CategoryQuery categoryQuery);

    /**
     * 通过查询对象查询分组树
     * 不带用例
     * @param categoryQuery
     * @return
     */
    List<Category> findCategoryListTreeTable(CategoryQuery categoryQuery);
}