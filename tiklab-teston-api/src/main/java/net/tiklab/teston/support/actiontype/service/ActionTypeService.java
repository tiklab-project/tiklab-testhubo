package net.tiklab.teston.support.actiontype.service;

import net.tiklab.core.page.Pagination;

import net.tiklab.join.annotation.FindAll;
import net.tiklab.join.annotation.FindList;
import net.tiklab.join.annotation.FindOne;
import net.tiklab.join.annotation.JoinProvider;
import net.tiklab.teston.support.actiontype.model.ActionType;
import net.tiklab.teston.support.actiontype.model.ActionTypeQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* ActionTypeService
*/
@JoinProvider(model = ActionType.class)
public interface ActionTypeService {

    /**
    * 创建
    * @param actionType
    * @return
    */
    String createActionType(@NotNull @Valid ActionType actionType);

    /**
    * 更新
    * @param actionType
    */
    void updateActionType(@NotNull @Valid ActionType actionType);

    /**
    * 删除
    * @param id
    */
    void deleteActionType(@NotNull String id);

    @FindOne
    ActionType findOne(@NotNull String id);

    @FindList
    List<ActionType> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    ActionType findActionType(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    @FindAll
    List<ActionType> findAllActionType();

    /**
    * 查询列表
    * @param actionTypeQuery
    * @return
    */
    List<ActionType> findActionTypeList(ActionTypeQuery actionTypeQuery);

    /**
    * 按分页查询
    * @param actionTypeQuery
    * @return
    */
    Pagination<ActionType> findActionTypePage(ActionTypeQuery actionTypeQuery);

}