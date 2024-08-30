package io.thoughtware.testrubo.category.dao;

import io.thoughtware.testrubo.category.entity.CategoryEntity;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.dal.jpa.criterial.condition.DeleteCondition;
import io.thoughtware.dal.jpa.criterial.condition.QueryCondition;
import io.thoughtware.dal.jpa.criterial.conditionbuilder.QueryBuilders;

import io.thoughtware.dal.jpa.JpaTemplate;
import io.thoughtware.testrubo.category.model.CategoryQuery;
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
     * @param categoryEntity
     * @return
     */
    public String createCategory(CategoryEntity categoryEntity) {
        return jpaTemplate.save(categoryEntity,String.class);
    }

    /**
     * 更新目录
     * @param categoryEntity
     */
    public void updateCategory(CategoryEntity categoryEntity){
        jpaTemplate.update(categoryEntity);
    }

    /**
     * 删除目录
     * @param id
     */
    public void deleteCategory(String id){
        jpaTemplate.delete(CategoryEntity.class,id);
    }

    public void deleteCategory(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找目录
     * @param id
     * @return
     */
    public CategoryEntity findCategory(String id){
        return jpaTemplate.findOne(CategoryEntity.class,id);
    }


    /**
     * 查询总数
     * @param repositoryId
     * @return
     */
    public int findCategoryNum(String repositoryId) {
        String categorySql = "Select count(1) as total from teston_category where repository_id = '" + repositoryId+ "'";
        Integer categoryTotal = jpaTemplate.getJdbcTemplate().queryForObject(categorySql, new Object[]{}, Integer.class);

        return categoryTotal;
    }



    /**
    * 查找所有目录
    * @return
    */
    public List<CategoryEntity> findAllCategory() {
        return jpaTemplate.findAll(CategoryEntity.class);
    }

    public List<CategoryEntity> findCategoryList(List<String> idList) {
        return jpaTemplate.findList(CategoryEntity.class,idList);
    }

    /**
     * 查询目录列表
     * @param categoryQuery
     * @return
     */
    public List<CategoryEntity> findCategoryList(CategoryQuery categoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(CategoryEntity.class)
                .eq("repositoryId", categoryQuery.getRepositoryId())
                .like("name", categoryQuery.getName())
                .orders(categoryQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, CategoryEntity.class);
    }

    /**
     * 按分页查询目录
     * @param categoryQuery
     * @return
     */
    public Pagination<CategoryEntity> findCategoryPage(CategoryQuery categoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(CategoryEntity.class)
                .eq("repositoryId", categoryQuery.getRepositoryId())
                .like("name", categoryQuery.getName())
                .pagination(categoryQuery.getPageParam())
                .orders(categoryQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, CategoryEntity.class);
    }


}