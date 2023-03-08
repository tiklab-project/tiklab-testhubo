package io.tiklab.teston.support.actiontype.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.core.Result;
import io.tiklab.teston.support.actiontype.entity.LocationEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *元素选择策略定位器 控制器
 */
@RestController
@RequestMapping("/location")
@Api(name = "LocationController",desc = "元素选择策略定位器管理")
public class LocationController {

    @RequestMapping(path="/findAllLocation",method = RequestMethod.POST)
    @ApiMethod(name = "findAllActionType",desc = "查询所有测试步骤操作方法的词项字典")
    public Result< LocationEnum[]> findAllLocation(){
        LocationEnum[] values = LocationEnum.values();
       return Result.ok(values);
    }
}
