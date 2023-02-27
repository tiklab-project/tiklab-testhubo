package net.tiklab.teston.manager.category.controller;

import net.tiklab.postin.annotation.Api;

import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.manager.category.model.Category;
import net.tiklab.teston.manager.category.model.CategoryQuery;
import net.tiklab.teston.manager.category.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * CategoryController
 */
@RestController
@RequestMapping("/category")
@Api(name = "CategoryController",desc = "测试用例分组")
public class CategoryController {

    private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(path="/createCategory",method = RequestMethod.POST)
    @ApiMethod(name = "createCategory",desc = "createCategory")
    @ApiParam(name = "category",desc = "category",required = true)
    public Result<String> createCategory(@RequestBody @NotNull @Valid Category category){
        String id = categoryService.createCategory(category);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateCategory",method = RequestMethod.POST)
    @ApiMethod(name = "updateCategory",desc = "updateCategory")
    @ApiParam(name = "category",desc = "category",required = true)
    public Result<Void> updateCategory(@RequestBody @NotNull @Valid Category category){
        categoryService.updateCategory(category);

        return Result.ok();
    }

    @RequestMapping(path="/deleteCategory",method = RequestMethod.POST)
    @ApiMethod(name = "deleteCategory",desc = "deleteCategory")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteCategory(@NotNull String id){
        categoryService.deleteCategory(id);

        return Result.ok();
    }

    @RequestMapping(path="/findCategory",method = RequestMethod.POST)
    @ApiMethod(name = "findCategory",desc = "findCategory")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Category> findCategory(@NotNull String id){
        Category category = categoryService.findCategory(id);

        return Result.ok(category);
    }

    @RequestMapping(path="/findAllCategory",method = RequestMethod.POST)
    @ApiMethod(name = "findAllCategory",desc = "findAllCategory")
    public Result<List<Category>> findAllCategory(){
        List<Category> categoryList = categoryService.findAllCategory();

        return Result.ok(categoryList);
    }

    @RequestMapping(path = "/findCategoryList",method = RequestMethod.POST)
    @ApiMethod(name = "findCategoryList",desc = "findCategoryList")
    @ApiParam(name = "categoryQuery",desc = "categoryQuery",required = true)
    public Result<List<Category>> findCategoryList(@RequestBody @Valid @NotNull CategoryQuery categoryQuery){
        List<Category> categoryList = categoryService.findCategoryList(categoryQuery);

        return Result.ok(categoryList);
    }

    @RequestMapping(path = "/findCategoryListTree",method = RequestMethod.POST)
    @ApiMethod(name = "findCategoryListTree",desc = "根据查询对象查找分类列表树")
    @ApiParam(name = "categoryQuery",desc = "查询对象",required = true)
    public Result<List<Category>> findCategoryListTree(@RequestBody @Valid @NotNull CategoryQuery categoryQuery){
        List<Category> categoryList = categoryService.findCategoryListTree(categoryQuery);

        return Result.ok(categoryList);
    }

    @RequestMapping(path = "/findCategoryListTreeTable",method = RequestMethod.POST)
    @ApiMethod(name = "findCategoryListTreeTable",desc = "根据查询对象查找分类列表树")
    @ApiParam(name = "categoryQuery",desc = "查询对象",required = true)
    public Result<List<Category>> findCategoryListTreeTable(@RequestBody @Valid @NotNull CategoryQuery categoryQuery){
        List<Category> categoryList = categoryService.findCategoryListTreeTable(categoryQuery);

        return Result.ok(categoryList);
    }

    @RequestMapping(path = "/findCategoryPage",method = RequestMethod.POST)
    @ApiMethod(name = "findCategoryPage",desc = "findCategoryPage")
    @ApiParam(name = "categoryQuery",desc = "categoryQuery",required = true)
    public Result<Pagination<Category>> findCategoryPage(@RequestBody @Valid @NotNull CategoryQuery categoryQuery){
        Pagination<Category> pagination = categoryService.findCategoryPage(categoryQuery);

        return Result.ok(pagination);
    }

}
