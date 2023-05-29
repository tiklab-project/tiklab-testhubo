package io.tiklab.teston.repository.dao;

import io.tiklab.teston.repository.entity.TestOnRepositoryEntity;
import io.tiklab.teston.repository.model.RepositoryQuery;
import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 仓库 数据访问
 */
@Repository
public class RepositoryDao{

    private static Logger logger = LoggerFactory.getLogger(RepositoryDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建仓库
     * @param testOnRepositoryEntity
     * @return
     */
    public String createRepository(TestOnRepositoryEntity testOnRepositoryEntity) {
        return jpaTemplate.save(testOnRepositoryEntity,String.class);
    }

    /**
     * 更新仓库
     * @param testOnRepositoryEntity
     */
    public void updateRepository(TestOnRepositoryEntity testOnRepositoryEntity){
        jpaTemplate.update(testOnRepositoryEntity);
    }

    /**
     * 删除仓库
     * @param id
     */
    public void deleteRepository(String id){
        jpaTemplate.delete(TestOnRepositoryEntity.class,id);
    }

    public void deleteRepository(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找仓库
     * @param id
     * @return
     */
    public TestOnRepositoryEntity findRepository(String id){
        return jpaTemplate.findOne(TestOnRepositoryEntity.class,id);
    }

    /**
    * 查找所有仓库
    * @return
    */
    public List<TestOnRepositoryEntity> findAllRepository() {
        return jpaTemplate.findAll(TestOnRepositoryEntity.class);
    }

    public List<TestOnRepositoryEntity> findRepositoryList(List<String> idList) {
        return jpaTemplate.findList(TestOnRepositoryEntity.class,idList);
    }

    /**
     * 根据查询参数查询仓库列表
     * @param repositoryQuery
     * @return
     */
    public List<TestOnRepositoryEntity> findRepositoryList(RepositoryQuery repositoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestOnRepositoryEntity.class)
                .eq("userId",repositoryQuery.getUserId())
                .like("name", repositoryQuery.getName())
                .orders(repositoryQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, TestOnRepositoryEntity.class);
    }

    /**
     * 根据查询参数按分页查询仓库
     * @param repositoryQuery
     * @return
     */
    public Pagination<TestOnRepositoryEntity> findRepositoryPage(RepositoryQuery repositoryQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(TestOnRepositoryEntity.class)
                .eq("userId",repositoryQuery.getUserId())
                .like("name", repositoryQuery.getName())
                .orders(repositoryQuery.getOrderParams())
                .pagination(repositoryQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, TestOnRepositoryEntity.class);
    }
}