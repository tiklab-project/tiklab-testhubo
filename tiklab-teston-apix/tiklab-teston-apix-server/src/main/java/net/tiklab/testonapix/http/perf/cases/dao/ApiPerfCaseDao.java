package net.tiklab.testonapix.http.perf.cases.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;

import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.testonapix.http.perf.cases.entity.ApiPerfCaseEntity;
import net.tiklab.testonapix.http.perf.cases.model.ApiPerfCaseQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ApiPerfCaseDao
 */
@Repository
public class ApiPerfCaseDao {

    private static Logger logger = LoggerFactory.getLogger(ApiPerfCaseDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param apiPerfCaseEntity
     * @return
     */
    public String createApiPerfCase(ApiPerfCaseEntity apiPerfCaseEntity) {
        return jpaTemplate.save(apiPerfCaseEntity,String.class);
    }

    /**
     * 更新
     * @param apiPerfCaseEntity
     */
    public void updateApiPerfCase(ApiPerfCaseEntity apiPerfCaseEntity){
        jpaTemplate.update(apiPerfCaseEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteApiPerfCase(String id){
        jpaTemplate.delete(ApiPerfCaseEntity.class,id);
    }

    public void deleteApiPerfCase(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ApiPerfCaseEntity findApiPerfCase(String id){
        return jpaTemplate.findOne(ApiPerfCaseEntity.class,id);
    }

    /**
    * findAllApiPerfCase
    * @return
    */
    public List<ApiPerfCaseEntity> findAllApiPerfCase() {
        return jpaTemplate.findAll(ApiPerfCaseEntity.class);
    }

    public List<ApiPerfCaseEntity> findApiPerfCaseList(List<String> idList) {
        return jpaTemplate.findList(ApiPerfCaseEntity.class,idList);
    }

    public List<ApiPerfCaseEntity> findApiPerfCaseList(ApiPerfCaseQuery apiPerfCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfCaseEntity.class)
                .eq("testCaseId",apiPerfCaseQuery.getTestCaseId())
                .orders(apiPerfCaseQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiPerfCaseEntity.class);
    }

    public Pagination<ApiPerfCaseEntity> findApiPerfCasePage(ApiPerfCaseQuery apiPerfCaseQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiPerfCaseEntity.class)
                .eq("testCaseId",apiPerfCaseQuery.getTestCaseId())
                .orders(apiPerfCaseQuery.getOrderParams())
                .pagination(apiPerfCaseQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiPerfCaseEntity.class);
    }
}