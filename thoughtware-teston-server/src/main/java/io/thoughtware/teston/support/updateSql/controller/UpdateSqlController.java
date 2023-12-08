package io.thoughtware.teston.support.updateSql.controller;

import io.thoughtware.teston.support.updateSql.UpdateSqlService;
import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * 更新sql 控制器
 */
@RestController
@RequestMapping("/sql")
@Api(name = "UpdateSqlController",desc = "更新sql管理")
public class UpdateSqlController {

    @Autowired
    UpdateSqlService updateSqlService;

    @RequestMapping(path="/updateSql",method = RequestMethod.POST)
    @ApiMethod(name = "updateSql",desc = "更新sql")
    @ApiParam(name = "updateSql",desc = "updateSql")
    public Result<Void> updateSql(){
        updateSqlService.updateSql();

        return Result.ok();
    }
}
