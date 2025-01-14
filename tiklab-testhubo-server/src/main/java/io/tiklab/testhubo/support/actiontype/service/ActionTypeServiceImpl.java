package io.tiklab.testhubo.support.actiontype.service;

import io.tiklab.testhubo.support.actiontype.model.ActionType;
import io.tiklab.testhubo.support.actiontype.model.ActionTypeQuery;
import io.tiklab.testhubo.support.actiontype.dao.ActionTypeDao;
import io.tiklab.testhubo.support.actiontype.entity.ActionTypeEntity;
import io.tiklab.core.page.PaginationBuilder;

import io.tiklab.core.page.Pagination;
import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* app、web中操作类型 服务
*/
@Service
public class ActionTypeServiceImpl implements ActionTypeService {

    @Autowired
    ActionTypeDao actionTypeDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createActionType(@NotNull @Valid ActionType actionType) {
        ActionTypeEntity actionTypeEntity = BeanMapper.map(actionType, ActionTypeEntity.class);

        return actionTypeDao.createActionType(actionTypeEntity);
    }

    @Override
    public void updateActionType(@NotNull @Valid ActionType actionType) {
        ActionTypeEntity actionTypeEntity = BeanMapper.map(actionType, ActionTypeEntity.class);

        actionTypeDao.updateActionType(actionTypeEntity);
    }

    @Override
    public void deleteActionType(@NotNull String id) {
        actionTypeDao.deleteActionType(id);
    }

    @Override
    public ActionType findOne(String id) {
        ActionTypeEntity actionTypeEntity = actionTypeDao.findActionType(id);

        ActionType actionType = BeanMapper.map(actionTypeEntity, ActionType.class);
        return actionType;
    }

    @Override
    public List<ActionType> findList(List<String> idList) {
        List<ActionTypeEntity> actionTypeEntityList =  actionTypeDao.findActionTypeList(idList);

        List<ActionType> actionTypeList =  BeanMapper.mapList(actionTypeEntityList,ActionType.class);
        return actionTypeList;
    }

    @Override
    public ActionType findActionType(@NotNull String id) {
        ActionType actionType = findOne(id);

        joinTemplate.joinQuery(actionType);
        return actionType;
    }

    @Override
    public List<ActionType> findAllActionType() {
        List<ActionTypeEntity> actionTypeEntityList =  actionTypeDao.findAllActionType();

        List<ActionType> actionTypeList =  BeanMapper.mapList(actionTypeEntityList,ActionType.class);

        joinTemplate.joinQuery(actionTypeList);
        return actionTypeList;
    }

    @Override
    public List<ActionType> findActionTypeList(ActionTypeQuery actionTypeQuery) {
        List<ActionTypeEntity> actionTypeEntityList = actionTypeDao.findActionTypeList(actionTypeQuery);

        List<ActionType> actionTypeList = BeanMapper.mapList(actionTypeEntityList,ActionType.class);

        joinTemplate.joinQuery(actionTypeList);

        return actionTypeList;
    }

    @Override
    public Pagination<ActionType> findActionTypePage(ActionTypeQuery actionTypeQuery) {

        Pagination<ActionTypeEntity>  pagination = actionTypeDao.findActionTypePage(actionTypeQuery);

        List<ActionType> actionTypeList = BeanMapper.mapList(pagination.getDataList(),ActionType.class);

        joinTemplate.joinQuery(actionTypeList);

        return PaginationBuilder.build(pagination,actionTypeList);
    }
}