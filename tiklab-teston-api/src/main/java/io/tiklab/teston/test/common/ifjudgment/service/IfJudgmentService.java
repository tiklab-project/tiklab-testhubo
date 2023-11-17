package io.tiklab.teston.test.common.ifjudgment.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.join.annotation.FindAll;
import io.tiklab.join.annotation.FindList;
import io.tiklab.join.annotation.FindOne;
import io.tiklab.join.annotation.JoinProvider;
import io.tiklab.teston.test.common.ifjudgment.model.IfJudgment;
import io.tiklab.teston.test.common.ifjudgment.model.IfJudgmentQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* if判断 服务接口
*/
@JoinProvider(model = IfJudgment.class)
public interface IfJudgmentService {

    /**
    * 创建if判断
    * @param ifJudgment
    * @return
    */
    String createIfJudgment(@NotNull @Valid IfJudgment ifJudgment);

    /**
    * 更新if判断
    * @param ifJudgment
    */
    void updateIfJudgment(@NotNull @Valid IfJudgment ifJudgment);

    /**
    * 删除if判断
    * @param id
    */
    void deleteIfJudgment(@NotNull String id);

    @FindOne
    IfJudgment findOne(@NotNull String id);

    /**
    * 根据id查找if判断
    * @param id
    * @return
    */
    IfJudgment findIfJudgment(@NotNull String id);

    /**
    * 根据查询参数查询if判断列表
    * @param ifJudgmentQuery
    * @return
    */
    List<IfJudgment> findIfJudgmentList(IfJudgmentQuery ifJudgmentQuery);



}