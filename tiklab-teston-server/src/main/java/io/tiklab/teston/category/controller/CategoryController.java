package io.tiklab.teston.category.controller;

import io.tiklab.postin.annotation.Api;

import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.teston.category.model.Categorys;
import io.tiklab.teston.category.model.CategoryQuery;
import io.tiklab.teston.category.service.CategoryService;
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
 * 目录 控制器
 */
@RestController
@RequestMapping("/category")
@Api(name = "CategoryController",desc = "目录管理")
public class CategoryController {

    private static Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(path="/createCategory",method = RequestMethod.POST)
    @ApiMethod(name = "createCategory",desc = "创建目录")
    @ApiParam(name = "category",desc = "category",required = true)
    public Result<String> createCategory(@RequestBody @NotNull @Valid Categorys categorys){
        String id = categoryService.createCategory(categorys);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateCategory",method = RequestMethod.POST)
    @ApiMethod(name = "updateCategory",desc = "更新目录")
    @ApiParam(name = "category",desc = "category",required = true)
    public Result<Void> updateCategory(@RequestBody @NotNull @Valid Categorys categorys){
        categoryService.updateCategory(categorys);

        return Result.ok();
    }

    @RequestMapping(path="/deleteCategory",method = RequestMethod.POST)
    @ApiMethod(name = "deleteCategory",desc = "删除目录")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteCategory(@NotNull String id){
        categoryService.deleteCategory(id);

        return Result.ok();
    }

    @RequestMapping(path="/findCategory",method = RequestMethod.POST)
    @ApiMethod(name = "findCategory",desc = "根据id查找目录")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Categorys> findCategory(@NotNull String id){
        Categorys categorys = categoryService.findCategory(id);

        return Result.ok(categorys);
    }

    @RequestMapping(path="/findAllCategory",method = RequestMethod.POST)
    @ApiMethod(name = "findAllCategory",desc = "查找所有目录")
    public Result<List<Categorys>> findAllCategory(){
        List<Categorys> categorysList = categoryService.findAllCategory();

        return Result.ok(categorysList);
    }

    @RequestMapping(path = "/findCategoryList",method = RequestMethod.POST)
    @ApiMethod(name = "findCategoryList",desc = "查询目录列表")
    @ApiParam(name = "categoryQuery",desc = "categoryQuery",required = true)
    public Result<List<Categorys>> findCategoryList(@RequestBody @Valid @NotNull CategoryQuery categoryQuery){
        List<Categorys> categorysList = categoryService.findCategoryList(categoryQuery);

        return Result.ok(categorysList);
    }

    @RequestMapping(path = "/findCategoryPage",method = RequestMethod.POST)
    @ApiMethod(name = "findCategoryPage",desc = "按分页查询目录")
    @ApiParam(name = "categoryQuery",desc = "categoryQuery",required = true)
    public Result<Pagination<Categorys>> findCategoryPage(@RequestBody @Valid @NotNull CategoryQuery categoryQuery){
        Pagination<Categorys> pagination = categoryService.findCategoryPage(categoryQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findCategoryListTree",method = RequestMethod.POST)
    @ApiMethod(name = "findCategoryListTree",desc = "根据查询对象查找分类列表树（带用例）")
    @ApiParam(name = "categoryQuery",desc = "查询对象",required = true)
    public Result<List<Categorys>> findCategoryListTree(@RequestBody @Valid @NotNull CategoryQuery categoryQuery){
        List<Categorys> categorysList = categoryService.findCategoryListTree(categoryQuery);

        return Result.ok(categorysList);
    }

    @RequestMapping(path = "/findCategoryListTreeTable",method = RequestMethod.POST)
    @ApiMethod(name = "findCategoryListTreeTable",desc = "根据查询对象查找分类列表树（不带用例）")
    @ApiParam(name = "categoryQuery",desc = "查询对象",required = true)
    public Result<List<Categorys>> findCategoryListTreeTable(@RequestBody @Valid @NotNull CategoryQuery categoryQuery){
        List<Categorys> categorysList = categoryService.findCategoryListTreeTable(categoryQuery);

        return Result.ok(categorysList);
    }


}
