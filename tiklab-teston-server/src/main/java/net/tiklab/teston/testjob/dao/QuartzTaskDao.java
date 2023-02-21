package net.tiklab.teston.testjob.dao;

import net.tiklab.core.page.Pagination;
import net.tiklab.dal.jpa.criterial.condition.DeleteCondition;
import net.tiklab.teston.testjob.entity.QuartzTaskEntity;
import net.tiklab.teston.testjob.model.QuartzTask;
import net.tiklab.teston.testjob.model.QuartzTaskQuery;
import net.tiklab.dal.jpa.JpaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * QuartzTaskDao
 */
@Repository
public class QuartzTaskDao{

    private static Logger logger = LoggerFactory.getLogger(QuartzTaskDao.class);

    @Autowired
    JpaTemplate jpaTemplate;

    /**
     * 创建
     * @param quartzTaskEntity
     * @return
     */
    public String createQuartzTask(QuartzTaskEntity quartzTaskEntity) {
        return jpaTemplate.save(quartzTaskEntity,String.class);
    }

    /**
     * 更新
     * @param quartzTaskEntity
     */
    public void updateQuartzTask(QuartzTaskEntity quartzTaskEntity){
        jpaTemplate.update(quartzTaskEntity);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteQuartzTask(String id){
        jpaTemplate.delete(QuartzTaskEntity.class,id);
    }

    public void deleteQuartzTask(DeleteCondition deleteCondition){
        jpaTemplate.delete(deleteCondition);
    }

    /**
     * 查找
     * @param id
     * @return
     */
    public QuartzTaskEntity findQuartzTask(String id){
        return jpaTemplate.findOne(QuartzTaskEntity.class,id);
    }

    /**
    * findAllQuartzTask
    * @return
    */
    public List<QuartzTaskEntity> findAllQuartzTask() {
        return jpaTemplate.findAll(QuartzTaskEntity.class);
    }

    public List<QuartzTaskEntity> findQuartzTaskList(List<String> idList) {
        return jpaTemplate.findList(QuartzTaskEntity.class,idList);
    }

    public List<QuartzTaskEntity> findQuartzTaskList(QuartzTaskQuery quartzTaskQuery) {
        List<QuartzTaskEntity> list = jpaTemplate.findList(quartzTaskQuery, QuartzTaskEntity.class);
        return list;
    }

    public Pagination<QuartzTaskEntity> findQuartzTaskPage(QuartzTaskQuery quartzTaskQuery) {
        return jpaTemplate.findPage(quartzTaskQuery, QuartzTaskEntity.class);
    }


    public  List<QuartzTask> findQuary() {
//        String sql="select * from teston_quartz_task qt LEFT JOIN teston_quartz_master qm  ON qt.quartz_master_id=qm.id where qm.state=1 AND  end_time >=now()";
//        List<QuartzTask> query = getJdbcTemplate().query(sql, new BeanPropertyRowMapper(QuartzTask.class));
        return null;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jpaTemplate.getJdbcTemplate();
    }
}