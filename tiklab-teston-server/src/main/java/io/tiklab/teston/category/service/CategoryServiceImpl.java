package io.tiklab.teston.category.service;

import io.tiklab.teston.category.dao.CategoryDao;
import io.tiklab.teston.category.entity.CategorysEntity;
import io.tiklab.core.page.PaginationBuilder;


import io.tiklab.core.page.Pagination;
import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.category.model.Categorys;
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
    public String createCategory(@NotNull @Valid Categorys categorys) {
        CategorysEntity categorysEntity = BeanMapper.map(categorys, CategorysEntity.class);

        return categoryDao.createCategory(categorysEntity);
    }

    @Override
    public void updateCategory(@NotNull @Valid Categorys categorys) {
        CategorysEntity categorysEntity = BeanMapper.map(categorys, CategorysEntity.class);

        categoryDao.updateCategory(categorysEntity);
    }

    @Override
    public void deleteCategory(@NotNull String id) {

        testCaseService.deleteTestCaseByCategoryId(id);

        categoryDao.deleteCategory(id);
    }

    @Override
    public Categorys findOne(String id) {
        CategorysEntity categorysEntity = categoryDao.findCategory(id);

        Categorys categorys = BeanMapper.map(categorysEntity, Categorys.class);
        return categorys;
    }

    @Override
    public List<Categorys> findList(List<String> idList) {
        List<CategorysEntity> categorysEntityList =  categoryDao.findCategoryList(idList);

        List<Categorys> categorysList =  BeanMapper.mapList(categorysEntityList, Categorys.class);
        return categorysList;
    }

    @Override
    public Categorys findCategory(@NotNull String id) {
        Categorys categorys = findOne(id);

        joinTemplate.joinQuery(categorys);
        return categorys;
    }

    @Override
    public List<Categorys> findAllCategory() {
        List<CategorysEntity> categorysEntityList =  categoryDao.findAllCategory();

        List<Categorys> categorysList =  BeanMapper.mapList(categorysEntityList, Categorys.class);

        joinTemplate.joinQuery(categorysList);
        return categorysList;
    }

    @Override
    public List<Categorys> findCategoryList(CategoryQuery categoryQuery) {
        List<CategorysEntity> categorysEntityList = categoryDao.findCategoryList(categoryQuery);

        List<Categorys> categorysList = BeanMapper.mapList(categorysEntityList, Categorys.class);

        joinTemplate.joinQuery(categorysList);

        return categorysList;
    }

    @Override
    public Pagination<Categorys> findCategoryPage(CategoryQuery categoryQuery) {

        Pagination<CategorysEntity>  pagination = categoryDao.findCategoryPage(categoryQuery);

        List<Categorys> categorysList = BeanMapper.mapList(pagination.getDataList(), Categorys.class);

        joinTemplate.joinQuery(categorysList);

        return PaginationBuilder.build(pagination, categorysList);
    }

    @Override
    public List<Categorys> findCategoryListTree(CategoryQuery categoryQuery) {
        //查找所有符合条件列表
        List<Categorys> matchCategorysList = findCategoryList(categoryQuery);

        //查找并设置分类下面的接口数
        List<Categorys> categorysMethodList = findCategoryMethodList(matchCategorysList, categoryQuery);

        //查找第一级分类列表
        List<Categorys> topCategorysList = findTopCategoryList(categorysMethodList);

        //查找并设置子分类列表
        if(topCategorysList != null){
            for(Categorys topCategorys : topCategorysList){
                setChildren(matchCategorysList, topCategorys);
            }
        }
        return topCategorysList;
    }

    @Override
    public List<Categorys> findCategoryListTreeTable(CategoryQuery categoryQuery){
        //查找所有符合条件列表
        List<Categorys> matchCategorysList = findCategoryList(categoryQuery);

        //查找第一级分类列表
        List<Categorys> topCategorysList = findTopCategoryList(matchCategorysList);

        //查找并设置子分类列表
        if(topCategorysList != null){
            for(Categorys topCategorys : topCategorysList){
                setChildren(matchCategorysList, topCategorys);
            }
        }
        return topCategorysList;
    }


    /**
     * 查找分类列表下的接口
     * @param matchCategorysList
     * @return
     */
    List<Categorys> findCategoryMethodList(List<Categorys> matchCategorysList, CategoryQuery categoryQuery){
        List<Categorys> categorysList = matchCategorysList.stream().map(category -> {
            TestCaseQuery testCaseQuery = new TestCaseQuery();
            testCaseQuery.setCategoryId(category.getId());
            testCaseQuery.setCaseType(categoryQuery.getCaseType());
            testCaseQuery.setTestType(categoryQuery.getTestType());
            List<TestCases> testCasesList = testCaseService.findTestCaseList(testCaseQuery);
            category.setCaseNum(testCasesList.size());
            return category;
        }).collect(Collectors.toList());


        return categorysList;
    }

    /**
     * 查找第一级分类列表
     * @param matchCategorysList
     * @return
     */
    List<Categorys> findTopCategoryList(List<Categorys> matchCategorysList){
        return matchCategorysList.stream()
                .filter(category -> (category.getParentId() == null || ObjectUtils.isEmpty(category.getParentId())))
                .collect(Collectors.toList());
    }

    /**
     * 查找并设置下级分类列表
     * @param matchCategorysList
     * @param parentCaegory
     */
    void setChildren(List<Categorys> matchCategorysList, Categorys parentCaegory){
        List<Categorys> childCategorysList = matchCategorysList.stream()
                .filter(category -> (category.getParentId() != null  && category.getParentId().equals(parentCaegory.getId())))
                .collect(Collectors.toList());

        if(childCategorysList != null && childCategorysList.size() > 0){
            parentCaegory.setChildren(childCategorysList);

            for(Categorys categorys : childCategorysList){
                setChildren(matchCategorysList, categorys);
            }
        }
    }

}