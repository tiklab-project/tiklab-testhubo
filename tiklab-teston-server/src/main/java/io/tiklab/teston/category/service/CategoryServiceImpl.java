package io.tiklab.teston.category.service;

import io.tiklab.teston.category.dao.CategoryDao;
import io.tiklab.teston.category.entity.CategoryEntity;
import io.tiklab.core.page.PaginationBuilder;


import io.tiklab.core.page.Pagination;
import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.category.model.Category;
import io.tiklab.teston.category.model.CategoryQuery;
import io.tiklab.teston.test.test.model.TestCases;
import io.tiklab.teston.test.test.model.TestCaseQuery;
import io.tiklab.teston.test.test.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.util.ObjectUtils;

/**
* 目录 服务
*/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    TestCaseService testCaseService;

    @Override
    public String createCategory(@NotNull @Valid Category category) {
        CategoryEntity categoryEntity = BeanMapper.map(category, CategoryEntity.class);

        return categoryDao.createCategory(categoryEntity);
    }

    @Override
    public void updateCategory(@NotNull @Valid Category category) {
        CategoryEntity categoryEntity = BeanMapper.map(category, CategoryEntity.class);

        categoryDao.updateCategory(categoryEntity);
    }

    @Override
    public void deleteCategory(@NotNull String id) {

        testCaseService.deleteTestCaseByCategoryId(id);

        categoryDao.deleteCategory(id);
    }

    @Override
    public Category findOne(String id) {
        CategoryEntity categoryEntity = categoryDao.findCategory(id);

        Category category = BeanMapper.map(categoryEntity, Category.class);
        return category;
    }

    @Override
    public List<Category> findList(List<String> idList) {
        List<CategoryEntity> categoryEntityList =  categoryDao.findCategoryList(idList);

        List<Category> categoryList =  BeanMapper.mapList(categoryEntityList, Category.class);
        return categoryList;
    }

    @Override
    public Category findCategory(@NotNull String id) {
        Category category = findOne(id);

        joinTemplate.joinQuery(category);
        return category;
    }

    @Override
    public List<Category> findAllCategory() {
        List<CategoryEntity> categoryEntityList =  categoryDao.findAllCategory();

        List<Category> categoryList =  BeanMapper.mapList(categoryEntityList, Category.class);

        joinTemplate.joinQuery(categoryList);
        return categoryList;
    }

    @Override
    public List<Category> findCategoryList(CategoryQuery categoryQuery) {
        List<CategoryEntity> categoryEntityList = categoryDao.findCategoryList(categoryQuery);

        List<Category> categoryList = BeanMapper.mapList(categoryEntityList, Category.class);

        joinTemplate.joinQuery(categoryList);

        return categoryList;
    }

    @Override
    public Pagination<Category> findCategoryPage(CategoryQuery categoryQuery) {

        Pagination<CategoryEntity>  pagination = categoryDao.findCategoryPage(categoryQuery);

        List<Category> categoryList = BeanMapper.mapList(pagination.getDataList(), Category.class);

        joinTemplate.joinQuery(categoryList);

        return PaginationBuilder.build(pagination, categoryList);
    }

    @Override
    public List<Category> findCategoryListTree(CategoryQuery categoryQuery) {
        //查找所有符合条件列表
        List<Category> matchCategoryList = findCategoryList(categoryQuery);

        //查找并设置分类下面的接口数
        List<Category> categoryMethodList = findCategoryMethodList(matchCategoryList, categoryQuery);

        //查找第一级分类列表
        List<Category> topCategoryList = findTopCategoryList(categoryMethodList);

        //查找并设置子分类列表
        if(topCategoryList != null){
            for(Category topCategory : topCategoryList){
                setChildren(matchCategoryList, topCategory);
            }
        }
        return topCategoryList;
    }

    @Override
    public List<Category> findCategoryListTreeTable(CategoryQuery categoryQuery){
        //查找所有符合条件列表
        List<Category> matchCategoryList = findCategoryList(categoryQuery);

        //查找第一级分类列表
        List<Category> topCategoryList = findTopCategoryList(matchCategoryList);

        //查找并设置子分类列表
        if(topCategoryList != null){
            for(Category topCategory : topCategoryList){
                setChildren(matchCategoryList, topCategory);
            }
        }
        return topCategoryList;
    }


    /**
     * 查找分类列表下的接口
     * @param matchCategoryList
     * @return
     */
    List<Category> findCategoryMethodList(List<Category> matchCategoryList, CategoryQuery categoryQuery){
        List<Category> categoryList = matchCategoryList.stream().map(category -> {
            TestCaseQuery testCaseQuery = new TestCaseQuery();
            testCaseQuery.setCategoryId(category.getId());
            testCaseQuery.setCaseType(categoryQuery.getCaseType());
            testCaseQuery.setTestType(categoryQuery.getTestType());
            List<TestCases> testCasesList = testCaseService.findTestCaseList(testCaseQuery);
            category.setCaseNum(testCasesList.size());
            return category;
        }).collect(Collectors.toList());


        return categoryList;
    }

    /**
     * 查找第一级分类列表
     * @param matchCategoryList
     * @return
     */
    List<Category> findTopCategoryList(List<Category> matchCategoryList){
        return matchCategoryList.stream()
                .filter(category -> (category.getParentId() == null || ObjectUtils.isEmpty(category.getParentId())))
                .collect(Collectors.toList());
    }

    /**
     * 查找并设置下级分类列表
     * @param matchCategoryList
     * @param parentCaegory
     */
    void setChildren(List<Category> matchCategoryList, Category parentCaegory){
        List<Category> childCategoryList = matchCategoryList.stream()
                .filter(category -> (category.getParentId() != null  && category.getParentId().equals(parentCaegory.getId())))
                .collect(Collectors.toList());

        if(childCategoryList != null && childCategoryList.size() > 0){
            parentCaegory.setChildren(childCategoryList);

            for(Category category : childCategoryList){
                setChildren(matchCategoryList, category);
            }
        }
    }

}