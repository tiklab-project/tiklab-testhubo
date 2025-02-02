package io.tiklab.testhubo.instance.dao;

import io.tiklab.core.page.Pagination;
import io.tiklab.dal.jpa.JpaTemplate;
import io.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import io.tiklab.dal.jpa.criterial.condition.QueryCondition;
import io.tiklab.dal.jpa.criterial.conditionbuilder.QueryBuilders;
import io.tiklab.testhubo.instance.entity.InstanceEntity;
import io.tiklab.testhubo.instance.model.InstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公共实例 数据访问
 */
@Repository
public class InstanceDao {

    private static Logger logger = LoggerFactory.getLogger(InstanceDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建公共实例
     */
    public String createInstance(InstanceEntity instanceEntity) {
        return jpaTemplate.save(instanceEntity,String.class);
    }

    /**
     * 更新公共实例
     * @param instanceEntity
     */
    public void updateInstance(InstanceEntity instanceEntity){
        jpaTemplate.update(instanceEntity);
    }

    /**
     * 删除公共实例
     * @param id
     */
    public void deleteInstance(String id){
        jpaTemplate.delete(InstanceEntity.class,id);
    }

    public void deleteInstance(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 根据id查找公共实例
     * @param id
     * @return
     */
    public InstanceEntity findInstance(String id){
        return jpaTemplate.findOne(InstanceEntity.class,id);
    }


    /**
    * 查找所有公共实例
    * @return
    */
    public List<InstanceEntity> findAllInstance() {
        return jpaTemplate.findAll(InstanceEntity.class);
    }

    public List<InstanceEntity> findInstanceList(List<String> idList) {
        return jpaTemplate.findList(InstanceEntity.class,idList);
    }

    public InstanceEntity findRecentInstance(String belongId){
        try {
            String sql = "SELECT * FROM teston_instance WHERE belong_id = ? ORDER BY create_time DESC LIMIT 1";

            InstanceEntity instanceEntity = jpaTemplate.getJdbcTemplate()
                    .queryForObject(sql, new Object[]{belongId}, new BeanPropertyRowMapper<>(InstanceEntity.class));

            return instanceEntity;
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            return null;
        }

    }


    /**
     * 查询公共实例列表
     * @param instanceQuery
     * @return
     */
    public List<InstanceEntity> findInstanceList(InstanceQuery instanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(InstanceEntity.class)
                .eq("repositoryId",instanceQuery.getRepositoryId())
                .eq("belongId",instanceQuery.getBelongId())
                .eq("type",instanceQuery.getType())
                .eq("createUser",instanceQuery.getCreateUser())
                .like("name",instanceQuery.getName())
                .in("type",instanceQuery.getTypeList())
                .orders(instanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findList(queryCondition, InstanceEntity.class);
    }

    /**
     * 按分页查询公共实例
     * @param instanceQuery
     * @return
     */
    public Pagination<InstanceEntity> findInstancePage(InstanceQuery instanceQuery) {
        QueryCondition queryCondition = QueryBuilders.createQuery(InstanceEntity.class)
                .eq("repositoryId",instanceQuery.getRepositoryId())
                .eq("belongId",instanceQuery.getBelongId())
                .eq("type",instanceQuery.getType())
                .eq("createUser",instanceQuery.getCreateUser())
                .eq("status",instanceQuery.getStatus())
                .like("name",instanceQuery.getName())
                .in("type",instanceQuery.getTypeList())
                .pagination(instanceQuery.getPageParam())
                .orders(instanceQuery.getOrderParams())
                .get();
        return jpaTemplate.findPage(queryCondition, InstanceEntity.class);
    }

    /**
     * 获取最近一次执行次数
     * @param belongId
     * @return
     */
    public int getRecentExecuteNum(String belongId) {
        try {
            String exeSql = "SELECT COALESCE(execute_number, 0) AS execute_number FROM teston_instance WHERE belong_id = '" + belongId+ "' ORDER BY execute_number DESC LIMIT 1;";
            return jpaTemplate.getJdbcTemplate().queryForObject(exeSql, new Object[]{}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    /**
     * 获取最近一次执行历史id
     * @param belongId
     * @return
     */
    public String getRecentExecuteInstanceId(String belongId) {
        try {
            String exeSql = "SELECT id FROM teston_instance WHERE belong_id =  '" + belongId + "'  ORDER BY execute_number DESC LIMIT 1;";
            return jpaTemplate.getJdbcTemplate().queryForObject(exeSql, new Object[]{}, String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int findInstanceNum(String belongId) {
        String numSql = "Select count(1) as total from teston_instance where belong_id = '" + belongId+ "'";
        Integer num = jpaTemplate.getJdbcTemplate().queryForObject(numSql, new Object[]{}, Integer.class);

        return num;
    }

    public int findInstanceNumByrepositoryId(String repositoryId) {
        String numSql = "Select count(1) as total from teston_instance where repository_id = '" + repositoryId+ "'";
        Integer num = jpaTemplate.getJdbcTemplate().queryForObject(numSql, new Object[]{}, Integer.class);

        return num;
    }
}