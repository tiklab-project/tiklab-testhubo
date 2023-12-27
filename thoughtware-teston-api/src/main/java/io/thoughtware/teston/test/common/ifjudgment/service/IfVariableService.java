package io.thoughtware.teston.test.common.ifjudgment.service;

import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;
import io.thoughtware.teston.test.common.ifjudgment.model.IfVariable;
import io.thoughtware.teston.test.common.ifjudgment.model.IfVariableQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* if值比较 服务接口
*/
@JoinProvider(model = IfVariable.class)
public interface IfVariableService {

    /**
    * 创建if值比较
    * @param ifVariable
    * @return
    */
    String createIfVariable(@NotNull @Valid IfVariable ifVariable);

    /**
    * 更新if值比较
    * @param ifVariable
    */
    void updateIfVariable(@NotNull @Valid IfVariable ifVariable);

    /**
    * 删除if值比较
    * @param id
    */
    void deleteIfVariable(@NotNull String id);

    @FindOne
    IfVariable findOne(@NotNull String id);

    /**
    * 根据id查找if值比较
    * @param id
    * @return
    */
    IfVariable findIfVariable(@NotNull String id);

    /**
    * 根据查询参数查询if值比较列表
    * @param ifVariableQuery
    * @return
    */
    List<IfVariable> findIfVariableList(IfVariableQuery ifVariableQuery);


}