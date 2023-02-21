package net.tiklab.teston.testjob.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.testjob.dao.QuartzMasterDao;
import net.tiklab.teston.testjob.dao.QuartzTaskDao;
import net.tiklab.teston.testjob.dao.QuartzTestcaseDao;
import net.tiklab.teston.testjob.entity.QuartzMasterEntity;
import net.tiklab.teston.testjob.entity.QuartzTaskEntity;
import net.tiklab.teston.testjob.util.QuartzManager;
import net.tiklab.teston.testjob.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
* QuartzMasterServiceImpl
*/
@Service
public class QuartzMasterServiceImpl implements QuartzMasterService {

    @Autowired
    QuartzMasterDao quartzMasterDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    QuartzTaskDao quartzTaskDao;

    @Autowired
    QuartzTestcaseDao quartzTestcaseDao;

    @Autowired
    private QuartzManager quartzManager;

    @Override
    public String createQuartzMaster(@NotNull @Valid QuartzMaster quartzMaster) {
        //定时任务时间
//        List<QuartzTask> quartzTaskList = quartzMaster.getQuartzTaskList();
//
//        QuartzMasterEntity quartzMasterEntity = BeanMapper.map(quartzMaster, QuartzMasterEntity.class);
//        List<Integer> weekList = quartzMaster.getWeekList();
//        if (CollectionUtils.isNotEmpty(weekList)){
//            //将weekList以逗号隔开 转为string 存入数据库
//            String weeks = StringUtils.join(weekList, ",");
//            quartzMasterEntity.setWeeks(weeks);
//        }
//        //api接口定时任务
//        if("API".equals(quartzMaster.getQuartzType()))
//        quartzMasterEntity.setTaskClassUrl("net.tiklab.teston.quartz.task.TestCaseTask");
//        //webui测试定时任务
//        if ("WEB".equals(quartzMaster.getQuartzType())){
//            quartzMasterEntity.setTaskClassUrl("net.tiklab.teston.quartz.task.WebUnitCaseTask");
//        }
//        //APP测试定时任务
//        if ("APP".equals(quartzMaster.getQuartzType())){
//            quartzMasterEntity.setTaskClassUrl("net.tiklab.teston.quartz.task.AppTestCaseTask");
//        }
//        //添加的定时任务默认为启动状态
//        quartzMasterEntity.setState(1);
//
//        String quartzMasterId = quartzMasterDao.createQuartzMaster(quartzMasterEntity);
//
//        for (QuartzTask quartzTask:quartzTaskList){
//            Date transform=null;
//            //当定时任务为轮询的时候
//            if (quartzMaster.getType()==1){
//                 transform = endTime(quartzTask.getExecutionTime(), quartzMaster);
//            }else {
//                transform = transform(quartzTask.getExecutionTime());
//            }
//
//            quartzTask.setEndTime(transform); //定时任务时间
//            quartzTask.setCronExpression(getCron(transform,quartzMaster));  //添加时间表达式
//            //添加定时任务主表id
//            QuartzMaster Master = new QuartzMaster();
//            Master.setId(quartzMasterId);
//            quartzTask.setQuartzMaster(Master);
//            QuartzTaskEntity quartzTaskEntity = BeanMapper.map(quartzTask, QuartzTaskEntity.class);
//            //保存定时任务时间
//            String quartzTaskId = quartzTaskDao.createQuartzTask(quartzTaskEntity);
//            addJob(quartzTaskId,quartzMasterId,quartzMasterEntity.getTaskClassUrl(),getCron(transform,quartzMaster),quartzMaster.getUserId());
//
//        }

//        return quartzMasterId;
        return null;
    }

    @Override
    public void update(@NotNull @Valid QuartzMaster quartzMaster) {
        QuartzMasterEntity quartzMasterEntity = BeanMapper.map(quartzMaster, QuartzMasterEntity.class);
        quartzMasterDao.updateQuartzMaster(quartzMasterEntity);
    }

    @Override
    public void updateQuartzMaster(@NotNull @Valid QuartzMaster quartzMaster) {

//        List<QuartzTask> quartzTaskList = quartzMaster.getQuartzTaskList();
//        List<QuartzTaskEntity> quartzTaskEntity = BeanMapper.mapList(quartzTaskList, QuartzTaskEntity.class);
//        //修改定时任务的执行时间
//        if (CollectionUtils.isNotEmpty(quartzTaskList)){
//            //数据库中之前添加的时间
//            List<QuartzTask> quartzList = findQuartzList(quartzMaster);
//            for (QuartzTask quartzTask:quartzList){
//                deleteJob(quartzTask.getId(),quartzMaster.getId(),quartzMaster.getUserId());
//            }
//            DeleteCondition deleteCondition = DeleteBuilders.createDelete(QuartzTaskEntity.class)
//                    .eq("quartzMasterId", quartzMaster.getId())
//                    .get();
//            quartzTaskDao.deleteQuartzTask(deleteCondition);
//
//            if (CollectionUtils.isNotEmpty(quartzTaskEntity)){
//                String taskClassUrl = quartzMasterDao.findQuartzMaster(quartzMaster.getId()).getTaskClassUrl();
//                for (QuartzTaskEntity quartzTask:quartzTaskEntity){
//                    Date transform = null;
//                    //时间转换
//                    if (quartzMaster.getType()==1){
//                        transform = endTime(quartzTask.getExecutionTime(), quartzMaster);
//
//                    }else {
//                        transform = transform(quartzTask.getExecutionTime());
//                    }
//                    quartzTask.setEndTime(transform);
//                    quartzTask.setCronExpression(getCron(transform,quartzMaster));
//                    quartzTask.setQuartzMasterId(quartzMaster.getId());
//                    String quartzTaskId = quartzTaskDao.createQuartzTask(quartzTask);
//
//                    addJob(quartzTaskId,quartzMaster.getId(),taskClassUrl,getCron(transform,quartzMaster),quartzMaster.getUserId());
//
//                }
//            }
//        }
//        QuartzMasterEntity quartzMasterEntity = BeanMapper.map(quartzMaster, QuartzMasterEntity.class);
//        if (CollectionUtils.isNotEmpty(quartzMaster.getWeekList())){
//            String weeks = StringUtils.join(quartzMaster.getWeekList(), ",");
//            quartzMasterEntity.setWeeks(weeks);
//        }else {
//            quartzMasterEntity.setWeeks("");
//        }
//        quartzMasterDao.updateQuartzMaster(quartzMasterEntity);
    }

    @Override
    public void deleteQuartzMaster(@NotNull String id) {
        quartzMasterDao.deleteQuartzMaster(id);
    }

    @Override
    public QuartzMaster findOne(String id) {
        QuartzMasterEntity quartzMasterEntity = quartzMasterDao.findQuartzMaster(id);
        QuartzMaster quartzMaster = BeanMapper.map(quartzMasterEntity, QuartzMaster.class);

        List<QuartzTask> quartzList = findQuartzList(quartzMaster);
        quartzMaster.setQuartzTaskList(quartzList);
        return quartzMaster;
    }

    @Override
    public List<QuartzMaster> findList(List<String> idList) {
        List<QuartzMasterEntity> quartzMasterEntityList =  quartzMasterDao.findQuartzMasterList(idList);

        List<QuartzMaster> quartzMasterList =  BeanMapper.mapList(quartzMasterEntityList,QuartzMaster.class);
        return quartzMasterList;
    }

    @Override
    public QuartzMaster findQuartzMaster(@NotNull String id) {

        QuartzMaster quartzMaster = findOne(id);
        String weeks = quartzMaster.getWeeks();
        if (!ObjectUtils.isEmpty(weeks)){
            String[] items = weeks.split(",");
            List<Integer> appIdList = Stream.of(items).map(Integer::parseInt).collect(Collectors.toList());
            quartzMaster.setWeekList(appIdList);
        }
        joinTemplate.joinQuery(quartzMaster);
        return quartzMaster;
    }

    @Override
    public List<QuartzMaster> findAllQuartzMaster() {
        List<QuartzMasterEntity> quartzMasterEntityList =  quartzMasterDao.findAllQuartzMaster();

        List<QuartzMaster> quartzMasterList =  BeanMapper.mapList(quartzMasterEntityList,QuartzMaster.class);

        joinTemplate.joinQuery(quartzMasterList);
        return quartzMasterList;
    }

    @Override
    public List<QuartzMaster> findQuartzMasterList(QuartzMasterQuery quartzMasterQuery) {
        List<QuartzMasterEntity> quartzMasterEntityList = quartzMasterDao.findQuartzMasterList(quartzMasterQuery);

        List<QuartzMaster> quartzMasterList = BeanMapper.mapList(quartzMasterEntityList,QuartzMaster.class);

        joinTemplate.joinQuery(quartzMasterList);

        return quartzMasterList;
    }

    @Override
    public Pagination<QuartzMaster> findQuartzMasterPage(QuartzMasterQuery quartzMasterQuery) {

//        Pagination<QuartzMasterEntity>  pagination = quartzMasterDao.findQuartzMasterPage(quartzMasterQuery);
//
//        List<QuartzMaster> quartzMasterList = BeanMapper.mapList(pagination.getDataList(),QuartzMaster.class);
//
//        List<QuartzMaster> collect = quartzMasterList.stream().map(quartzMaster -> {
//            //查询定时任务时间
//            List<QuartzTask> quartzList = findQuartzList(quartzMaster);
//            //定时任务下的定时任务时间
//            List<Date> date = quartzList.stream().map(QuartzTask::getEndTime).collect(Collectors.toList());
//            //list转字符串
//            String join = StringUtils.join(date, ",");
//
//            quartzMaster.setQuartzTaskList(quartzList);
//            quartzMaster.setExecutionTimes(join);
//            //查询定时任务的用例
//            List<QuartzTestcaseEntity> quartzTestcaseList = quartzTestcaseDao.findQuartzTestcaseList(new QuartzTestcaseQuery().setQuartzMasterId(quartzMaster.getId()));
//            if (CollectionUtils.isNotEmpty(quartzTestcaseList)){
//                quartzMaster.setCaseNumber(quartzTestcaseList.size());
//            }
//            return quartzMaster;
//        }).collect(Collectors.toList());
//        joinTemplate.joinQuery(collect);
//
//        return PaginationBuilder.build(pagination,collect);
        return null;
    }

    /**
     * 查询定时任务时间
     * @param
     */
    public   List<QuartzTask>  findQuartzList(QuartzMaster quartzMaster){
        //查询定时任务的时间
        List<QuartzTaskEntity> quartzTaskList = quartzTaskDao.findQuartzTaskList(new QuartzTaskQuery().setQuartzMasterId(quartzMaster.getId()));
        List<QuartzTask> quartzTasks = BeanMapper.mapList(quartzTaskList, QuartzTask.class);

        return quartzTasks;
    }


    /**
     * 找出循环最后一天的年月日
     * @param
     */
    public Date  endTime(String executionTime,QuartzMaster quartzMaster){
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String lastName=null;
        if("day".equals(quartzMaster.getPeriod())){
            Date date = new Date();
            String format = f.format(date);
            String substring = format.substring(0, format.length() - 6);
             lastName = substring + " " + executionTime;
        }
        if ("week".equals(quartzMaster.getPeriod())){
            Calendar cld = Calendar.getInstance(Locale.CHINA);
            cld.setFirstDayOfWeek(Calendar.MONDAY);//以周一为首日
            cld.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//周末
            String format = f.format(cld.getTime());
            String substring = format.substring(0, format.length() - 6);
            lastName = substring + " " + executionTime;
        }

        Integer cycleIndex = quartzMaster.getCycleIndex();  //执行次数
        Integer number=0;
        if ("day".equals(quartzMaster.getPeriod())){
            number= cycleIndex;
        }
        if ("week".equals(quartzMaster.getPeriod())){
            number=cycleIndex *  7;
        }

        Date parse=null;
        try {
            if (number==1){
                 parse = f.parse(lastName);
            }else {
                parse = new Date(f.parse(lastName).getTime() + 24 * 3600 * 1000 * (number-1));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    /**
     * 转换时间为cron表达式
     * @param
     */
    public static String getCron(Date date,QuartzMaster quartzMaster) {
        String dtFormat=null;
        if (quartzMaster.getType()==0){
            dtFormat= "ss mm HH dd MM ? yyyy";
        }
        //为循环执行
        if (quartzMaster.getType()==1){
            //按天执行
            if ("day".equals(quartzMaster.getPeriod())){
                dtFormat= "ss mm HH * * ?";
            }
            //按周执行
            if ("week".equals(quartzMaster.getPeriod())){
                 dtFormat = "ss mm HH ? * ";
                List<Integer> weekList = quartzMaster.getWeekList();
               for (int i=0;i<weekList.size();i++){
                   Integer week = weekList.get(i);
                   if (i==weekList.size()-1){
                       dtFormat = dtFormat+week;
                   }else {
                       dtFormat = dtFormat+week+",";
                   }
               }
            }
        }

        if (date == null) {
            return "";
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 添加定时任务工作
     * @param
     */
    public void addJob(String quartzTaskId,String quartzMasterId,
                     String taskClassUrl,String endTimeCron,String userId){
        String quartzTaskIdName=quartzTaskId+"_"+userId+"_"+quartzMasterId;
        quartzManager.addJob(quartzTaskIdName,taskClassUrl,endTimeCron,null);
    }

    /**
     * 删除定时任务工作
     * @param
     */
    public void  deleteJob(String quartzTaskId,String quartzMasterId,String userId){
        String jobName=quartzTaskId+"_"+userId+"_"+quartzMasterId;
        quartzManager.deleteJob(jobName, null);
    }
    /**
     * 修改定时任务工作
     * @param
     */
    public void  updateJob(String quartzTaskId,String quartzMasterId,
                           String taskClassUrl,String userId){
        String quartzTaskIdName=quartzTaskId+"_"+userId+"_"+quartzMasterId;
        quartzManager.updateJob(quartzTaskIdName, taskClassUrl, null);

    }
    /**
     * 时间转换  string转date
     * @param
     */
    public Date transform(String executionTime){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date parses=null;
        try {
            parses = simpleDateFormat.parse(executionTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parses;
    }

    /**
     * 时间转换  date转date
     * @param
     */
    public String transformString(Date date){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String format = simpleDateFormat.format(date);
        return format;
    }


}