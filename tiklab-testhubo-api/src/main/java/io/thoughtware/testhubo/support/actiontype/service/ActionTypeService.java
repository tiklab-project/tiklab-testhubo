package io.thoughtware.testhubo.support.actiontype.service;

import io.thoughtware.testhubo.support.actiontype.model.ActionType;
import io.thoughtware.testhubo.support.actiontype.model.ActionTypeQuery;
import io.thoughtware.core.page.Pagination;

import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* app、web中操作类型 接口服务
*/
@JoinProvider(model = ActionType.class)
public interface ActionTypeService {

    /**
    * 创建操作类型
    * @param actionType
    * @return
    */
    String createActionType(@NotNull @Valid ActionType actionType);

    /**
    * 更新操作类型
    * @param actionType
    */
    void updateActionType(@NotNull @Valid ActionType actionType);

    /**
    * 删除操作类型
    * @param id
    */
    void deleteActionType(@NotNull String id);

    @FindOne
    ActionType findOne(@NotNull String id);

    @FindList
    List<ActionType> findList(List<String> idList);

    /**
    * 根据id查找操作类型
    * @param id
    * @return
    */
    ActionType findActionType(@NotNull String id);

    /**
    * 查找所有操作类型
    * @return
    */
    @FindAll
    List<ActionType> findAllActionType();

    /**
    * 根据查询参数查询操作类型列表
    * @param actionTypeQuery
    * @return
    */
    List<ActionType> findActionTypeList(ActionTypeQuery actionTypeQuery);

    /**
    * 根据查询参数按分页查询操作类型
    * @param actionTypeQuery
    * @return
    */
    Pagination<ActionType> findActionTypePage(ActionTypeQuery actionTypeQuery);

}