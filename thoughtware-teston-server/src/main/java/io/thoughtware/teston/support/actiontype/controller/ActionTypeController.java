package io.thoughtware.teston.support.actiontype.controller;

import io.thoughtware.teston.support.actiontype.model.ActionType;
import io.thoughtware.teston.support.actiontype.model.ActionTypeQuery;
import io.thoughtware.teston.support.actiontype.service.ActionTypeService;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * app、web中操作类型 控制器
 */
@RestController
@RequestMapping("/actionType")
@Api(name = "ActionTypeController",desc = "测试步骤操作方法的词项字典管理")
public class ActionTypeController {

    private static Logger logger = LoggerFactory.getLogger(ActionTypeController.class);

    @Autowired
    private ActionTypeService actionTypeService;

    @RequestMapping(path="/createActionType",method = RequestMethod.POST)
    @ApiMethod(name = "createActionType",desc = "创建操作类型")
    @ApiParam(name = "actionType",desc = "actionType",required = true)
    public Result<String> createActionType(@RequestBody @NotNull @Valid ActionType actionType){
        String id = actionTypeService.createActionType(actionType);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateActionType",method = RequestMethod.POST)
    @ApiMethod(name = "updateActionType",desc = "更新操作类型")
    @ApiParam(name = "actionType",desc = "actionType",required = true)
    public Result<Void> updateActionType(@RequestBody @NotNull @Valid ActionType actionType){
        actionTypeService.updateActionType(actionType);

        return Result.ok();
    }

    @RequestMapping(path="/deleteActionType",method = RequestMethod.POST)
    @ApiMethod(name = "deleteActionType",desc = "删除操作类型")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteActionType(@NotNull String id){
        actionTypeService.deleteActionType(id);

        return Result.ok();
    }

    @RequestMapping(path="/findActionType",method = RequestMethod.POST)
    @ApiMethod(name = "findActionType",desc = "根据id查找操作类型")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<ActionType> findActionType(@NotNull String id){
        ActionType actionType = actionTypeService.findActionType(id);

        return Result.ok(actionType);
    }

    @RequestMapping(path="/findAllActionType",method = RequestMethod.POST)
    @ApiMethod(name = "findAllActionType",desc = "查询所有测试步骤操作方法的词项字典")
    public Result<List<ActionType>> findAllActionType(){
        List<ActionType> actionTypeList = actionTypeService.findAllActionType();

        return Result.ok(actionTypeList);
    }

    @RequestMapping(path = "/findActionTypeList",method = RequestMethod.POST)
    @ApiMethod(name = "findActionTypeList",desc = "根据查询参数查询操作类型列表")
    @ApiParam(name = "actionTypeQuery",desc = "actionTypeQuery",required = true)
    public Result<List<ActionType>> findActionTypeList(@RequestBody @Valid @NotNull ActionTypeQuery actionTypeQuery){
        List<ActionType> actionTypeList = actionTypeService.findActionTypeList(actionTypeQuery);

        return Result.ok(actionTypeList);
    }

    @RequestMapping(path = "/findActionTypePage",method = RequestMethod.POST)
    @ApiMethod(name = "findActionTypePage",desc = "根据查询参数按分页查询操作类型")
    @ApiParam(name = "actionTypeQuery",desc = "actionTypeQuery",required = true)
    public Result<Pagination<ActionType>> findActionTypePage(@RequestBody @Valid @NotNull ActionTypeQuery actionTypeQuery){
        Pagination<ActionType> pagination = actionTypeService.findActionTypePage(actionTypeQuery);

        return Result.ok(pagination);
    }

}
