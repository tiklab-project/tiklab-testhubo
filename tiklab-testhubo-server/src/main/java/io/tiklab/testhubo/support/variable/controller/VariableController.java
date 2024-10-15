package io.tiklab.testhubo.support.variable.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.testhubo.support.variable.model.Variable;
import io.tiklab.testhubo.support.variable.model.VariableQuery;
import io.tiklab.testhubo.support.variable.service.VariableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 场景变量 控制器
 */
@RestController
@RequestMapping("/variable")
@Api(name = "VariableController",desc = "场景变量 管理")
public class VariableController {

    private static Logger logger = LoggerFactory.getLogger(VariableController.class);

    @Autowired
    private VariableService variableService;

    @RequestMapping(path="/createVariable",method = RequestMethod.POST)
    @ApiMethod(name = "createVariable",desc = "创建场景变量")
    @ApiParam(name = "variable",desc = "variable",required = true)
    public Result<String> createVariable(@RequestBody @NotNull @Valid Variable variable){
        String id = variableService.createVariable(variable);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateVariable",method = RequestMethod.POST)
    @ApiMethod(name = "updateVariable",desc = "更新场景变量")
    @ApiParam(name = "variable",desc = "variable",required = true)
    public Result<Void> updateVariable(@RequestBody @NotNull @Valid Variable variable){
        variableService.updateVariable(variable);

        return Result.ok();
    }

    @RequestMapping(path="/deleteVariable",method = RequestMethod.POST)
    @ApiMethod(name = "deleteVariable",desc = "删除场景变量")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteVariable(@NotNull String id){
        variableService.deleteVariable(id);

        return Result.ok();
    }

    @RequestMapping(path="/findVariable",method = RequestMethod.POST)
    @ApiMethod(name = "findVariable",desc = "根据id查找场景变量")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Variable> findVariable(@NotNull String id){
        Variable variable = variableService.findVariable(id);

        return Result.ok(variable);
    }

    @RequestMapping(path="/findAllVariable",method = RequestMethod.POST)
    @ApiMethod(name = "findAllVariable",desc = "查找所有场景变量")
    public Result<List<Variable>> findAllVariable(){
        List<Variable> variableList = variableService.findAllVariable();

        return Result.ok(variableList);
    }

    @RequestMapping(path = "/findVariableList",method = RequestMethod.POST)
    @ApiMethod(name = "findVariableList",desc = "根据查询参数查询场景变量列表")
    @ApiParam(name = "variableQuery",desc = "variableQuery",required = true)
    public Result<List<Variable>> findVariableList(@RequestBody @Valid @NotNull VariableQuery variableQuery){
        List<Variable> variableList = variableService.findVariableList(variableQuery);

        return Result.ok(variableList);
    }

    @RequestMapping(path = "/findVariablePage",method = RequestMethod.POST)
    @ApiMethod(name = "findVariablePage",desc = "根据查询参数按分页查询场景变量")
    @ApiParam(name = "variableQuery",desc = "variableQuery",required = true)
    public Result<Pagination<Variable>> findVariablePage(@RequestBody @Valid @NotNull VariableQuery variableQuery){
        Pagination<Variable> pagination = variableService.findVariablePage(variableQuery);

        return Result.ok(pagination);
    }

}
