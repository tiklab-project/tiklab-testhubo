package io.tiklab.teston.category.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.teston.category.entity.CategorysEntity;

import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.teston.category.model.CategoryQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 目录 数据访问
 */
@Repository
public class CategoryDao{

    private static Logger logger = LoggerFactory.getLogger(CategoryDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建目录
     * @param categorysEntity
     * @return
     */
    public String createCategory(CategorysEntity categorysEntity) {
        return jpaTemplate.save(categorysEntity,String.class);
    }

    /**
     * 更新目录
     * @param categorysEntity
     */
    public void updateCategory(CategorysEntity categorysEntity){
        jpaTemplate.update(categorysEntity);
    }

    /**
     * 删除目录
     * @param id
     */
    public void deleteCategory(String id){
        jpaTemplate.delete(CategorysEntity.class,id);
    }

    public void deleteCategory(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找目录
     * @param id
     * @return
     */
    public CategorysEntity findCategory(String id){
        return jpaTemplate.findOne(CategorysEntity.class,id);
    }

    /**
    * 查找所有目录
    * @return
    */
    public List<CategorysEntity> findAllCategory() {
        return jpaTemplate.findAll(CategorysEntity.class);
    }

    public List<CategorysEntity> findCategoryList(List<String> idList) {
        return jpaTemplate.findList(CategorysEntity.class,idList);
    }

    /**
     * 查询目录列表
     * @param categoryQuery
     * @return
     */
    public List<CategorysEntity> findCategoryList(CategoryQuery categoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(CategorysEntity.class)
                .eq("repositoryId", categoryQuery.getRepositoryId())
                .like("name", categoryQuery.getName())
                .orders(categoryQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, CategorysEntity.class);
    }

    /**
     * 按分页查询目录
     * @param categoryQuery
     * @return
     */
    public Pagination<CategorysEntity> findCategoryPage(CategoryQuery categoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(CategorysEntity.class)
                .eq("repositoryId", categoryQuery.getRepositoryId())
                .like("name", categoryQuery.getName())
                .pagination(categoryQuery.getPageParam())
                .orders(categoryQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, CategorysEntity.class);
    }


}