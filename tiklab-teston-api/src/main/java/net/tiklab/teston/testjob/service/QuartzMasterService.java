package net.tiklab.teston.testjob.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.testjob.model.QuartzMaster;
import net.tiklab.teston.testjob.model.QuartzMasterQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* QuartzMasterService
*/
@JoinProvider(model = QuartzMaster.class)
public interface QuartzMasterService {

    /**
    * 创建
    * @param quartzMaster
    * @return
    */
    String createQuartzMaster(@NotNull @Valid QuartzMaster quartzMaster);

    /**
    * 更新
    * @param quartzMaster
    */
    void updateQuartzMaster(@NotNull @Valid QuartzMaster quartzMaster);


    /**
     * 普通更新
     * @param quartzMaster
     */
    void update(@NotNull @Valid QuartzMaster quartzMaster);

    /**
    * 删除
    * @param id
    */
    void deleteQuartzMaster(@NotNull String id);

    @FindOne
    QuartzMaster findOne(@NotNull String id);

    @FindList
    List<QuartzMaster> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    QuartzMaster findQuartzMaster(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<QuartzMaster> findAllQuartzMaster();

    /**
    * 查询列表
    * @param quartzMasterQuery
    * @return
    */
    List<QuartzMaster> findQuartzMasterList(QuartzMasterQuery quartzMasterQuery);

    /**
    * 按分页查询
    * @param quartzMasterQuery
    * @return
    */
    Pagination<QuartzMaster> findQuartzMasterPage(QuartzMasterQuery quartzMasterQuery);

}