package net.tiklab.teston.testjob.service;

import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.teston.testjob.dao.QuartzTaskDao;
import net.tiklab.teston.testjob.entity.QuartzTaskEntity;
import net.tiklab.teston.testjob.model.QuartzTask;
import net.tiklab.teston.testjob.model.QuartzTaskQuery;

import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* QuartzTaskServiceImpl
*/
@Service
public class QuartzTaskServiceImpl implements QuartzTaskService {

    @Autowired
    QuartzTaskDao quartzTaskDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createQuartzTask(@NotNull @Valid QuartzTask quartzTask) {
        QuartzTaskEntity quartzTaskEntity = BeanMapper.map(quartzTask, QuartzTaskEntity.class);

        return quartzTaskDao.createQuartzTask(quartzTaskEntity);
    }

    @Override
    public void updateQuartzTask(@NotNull @Valid QuartzTask quartzTask) {

        QuartzTaskEntity quartzTaskEntity = BeanMapper.map(quartzTask, QuartzTaskEntity.class);

        quartzTaskDao.updateQuartzTask(quartzTaskEntity);
    }

    @Override
    public void deleteQuartzTask(@NotNull String id) {
        quartzTaskDao.deleteQuartzTask(id);
    }

    @Override
    public QuartzTask findOne(String id) {
        QuartzTaskEntity quartzTaskEntity = quartzTaskDao.findQuartzTask(id);

        QuartzTask quartzTask = BeanMapper.map(quartzTaskEntity, QuartzTask.class);
        return quartzTask;
    }

    @Override
    public List<QuartzTask> findList(List<String> idList) {
        List<QuartzTaskEntity> quartzTaskEntityList =  quartzTaskDao.findQuartzTaskList(idList);

        List<QuartzTask> quartzTaskList =  BeanMapper.mapList(quartzTaskEntityList,QuartzTask.class);
        return quartzTaskList;
    }

    @Override
    public QuartzTask findQuartzTask(@NotNull String id) {
        QuartzTask quartzTask = findOne(id);

        joinTemplate.joinQuery(quartzTask);
        return quartzTask;
    }

    @Override
    public List<QuartzTask> findAllQuartzTask() {
        List<QuartzTaskEntity> quartzTaskEntityList =  quartzTaskDao.findAllQuartzTask();

        List<QuartzTask> quartzTaskList =  BeanMapper.mapList(quartzTaskEntityList,QuartzTask.class);

        joinTemplate.joinQuery(quartzTaskList);
        return quartzTaskList;
    }

    @Override
    public List<QuartzTask> findQuartzTaskList(QuartzTaskQuery quartzTaskQuery) {
        List<QuartzTaskEntity> quartzTaskEntityList = quartzTaskDao.findQuartzTaskList(quartzTaskQuery);

        List<QuartzTask> quartzTaskList = BeanMapper.mapList(quartzTaskEntityList,QuartzTask.class);

        joinTemplate.joinQuery(quartzTaskList);

        return quartzTaskList;
    }

    @Override
    public Pagination<QuartzTask> findQuartzTaskPage(QuartzTaskQuery quartzTaskQuery) {

        Pagination<QuartzTaskEntity>  pagination = quartzTaskDao.findQuartzTaskPage(quartzTaskQuery);

        List<QuartzTask> quartzTaskList = BeanMapper.mapList(pagination.getDataList(),QuartzTask.class);

        joinTemplate.joinQuery(quartzTaskList);

        return PaginationBuilder.build(pagination,quartzTaskList);
    }

    @Override
    public List<QuartzTask> getCron() {
//        List<QuartzTask> quary = quartzTaskDao.findQuary();
//        joinTemplate.joinQuery(quary);
        return null;
    }
}