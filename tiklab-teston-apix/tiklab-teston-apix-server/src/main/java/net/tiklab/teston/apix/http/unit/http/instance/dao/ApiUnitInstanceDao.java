package net.tiklab.teston.apix.http.unit.http.instance.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.dal.jpa.criterial.condition.QueryCondition;
import net.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import net.tiklab.dal.jpa.JpaTemplate;
import net.tiklab.teston.apix.http.unit.instance.model.ApiUnitInstanceQuery;
import net.tiklab.teston.apix.http.unit.http.instance.entity.ApiUnitInstanceEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TestInstanceDao
 */
@Repository
public class ApiUnitInstanceDao {

    private static Logger logger = LoggerFactory.getLogger(ApiUnitInstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param testStepInstanceEntity
     * @return
     */
    public String createApiUnitInstance(ApiUnitInstanceEntity testStepInstanceEntity) {
        return jpaTemplate.save(testStepInstanceEntity,String.class);
    }

    /**
     * 更新
     * @param testStepInstanceEntity
     */
    public void updateApiUnitInstance(ApiUnitInstanceEntity testStepInstanceEntity){
        jpaTemplate.update(testStepInstanceEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteApiUnitInstance(String id){
        jpaTemplate.delete(ApiUnitInstanceEntity.class,id);
    }

    public void deleteApiUnitInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public ApiUnitInstanceEntity findApiUnitInstance(String id){
        return jpaTemplate.findOne(ApiUnitInstanceEntity.class,id);
    }

    /**
    * findAllApiUnitInstance
    * @return
    */
    public List<ApiUnitInstanceEntity> findAllApiUnitInstance() {
        return jpaTemplate.findAll(ApiUnitInstanceEntity.class);
    }

    public List<ApiUnitInstanceEntity> findApiUnitInstanceList(List<String> idList) {
        return jpaTemplate.findList(ApiUnitInstanceEntity.class,idList);
    }

    public List<ApiUnitInstanceEntity> findApiUnitInstanceList(ApiUnitInstanceQuery apiUnitInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiUnitInstanceEntity.class)
                .eq("apiUnitId", apiUnitInstanceQuery.getApiUnitId())
                .orders(apiUnitInstanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, ApiUnitInstanceEntity.class);
    }

    public Pagination<ApiUnitInstanceEntity> findApiUnitInstancePage(ApiUnitInstanceQuery apiUnitInstanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(ApiUnitInstanceEntity.class)
                .eq("apiUnitId", apiUnitInstanceQuery.getApiUnitId())
                .orders(apiUnitInstanceQuery.getOrderParams())
                .pagination(apiUnitInstanceQuery.getPageParam())
                .get();
        return jpaTemplate.findPage(queryCondition, ApiUnitInstanceEntity.class);
    }

    /**
     * 通过list查询
     * @return
     */
    public List<ApiUnitInstanceEntity> findApiUnitInstanceByList(List<String> collect) {
       String sql = "select * from jest_test_instance t where id in (:collect)";
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("collect", collect);
        NamedParameterJdbcTemplate jdbc = new NamedParameterJdbcTemplate(getJdbcTemplate());
        List<ApiUnitInstanceEntity> testInstanceList = jdbc.query(sql, paramMap, new BeanPropertyRowMapper(ApiUnitInstanceEntity.class));
        return testInstanceList;

    }

    public JdbcTemplate getJdbcTemplate() {
        return jpaTemplate.getJdbcTemplate();
    }
}