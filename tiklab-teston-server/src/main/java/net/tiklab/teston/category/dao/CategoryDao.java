package net.tiklab.teston.category.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.teston.category.entity.CategoryEntity;
import net.tiklab.teston.category.model.CategoryQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CategoryDao
 */
@Repository
public class CategoryDao{

    private static Logger logger = LoggerFactory.getLogger(CategoryDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param categoryEntity
     * @return
     */
    public String createCategory(CategoryEntity categoryEntity) {
        return jpaTemplate.save(categoryEntity,String.class);
    }

    /**
     * 更新
     * @param categoryEntity
     */
    public void updateCategory(CategoryEntity categoryEntity){
        jpaTemplate.update(categoryEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteCategory(String id){
        jpaTemplate.delete(CategoryEntity.class,id);
    }

    public void deleteCategory(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public CategoryEntity findCategory(String id){
        return jpaTemplate.findOne(CategoryEntity.class,id);
    }

    /**
    * findAllCategory
    * @return
    */
    public List<CategoryEntity> findAllCategory() {
        return jpaTemplate.findAll(CategoryEntity.class);
    }

    public List<CategoryEntity> findCategoryList(List<String> idList) {
        return jpaTemplate.findList(CategoryEntity.class,idList);
    }

    public List<CategoryEntity> findCategoryList(CategoryQuery categoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(CategoryEntity.class)
                .eq("repositoryId", categoryQuery.getRepositoryId())
                .like("name", categoryQuery.getName())
                .orders(categoryQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, CategoryEntity.class);
    }

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