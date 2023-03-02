package net.tiklab.teston.apix.http.unit.http.instance.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.apix.http.unit.instance.model.RequestInstance;
import net.tiklab.teston.apix.http.unit.instance.model.RequestInstanceQuery;
import net.tiklab.teston.apix.http.unit.instance.service.RequestInstanceService;
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
 * 请求数据实例 控制器
 */
@RestController
@RequestMapping("/requestInstance")
@Api(name = "RequestInstanceController",desc = "测试实例——请求管理")
public class RequestInstanceController {

    private static Logger logger = LoggerFactory.getLogger(RequestInstanceController.class);

    @Autowired
    private RequestInstanceService requestInstanceService;

    @RequestMapping(path="/createRequestInstance",method = RequestMethod.POST)
    @ApiMethod(name = "createRequestInstance",desc = "创建请求数据实例")
    @ApiParam(name = "requestInstance",desc = "requestInstance",required = true)
    public Result<String> createRequestInstance(@RequestBody @NotNull @Valid RequestInstance requestInstance){
        String id = requestInstanceService.createRequestInstance(requestInstance);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateRequestInstance",method = RequestMethod.POST)
    @ApiMethod(name = "updateRequestInstance",desc = "更新请求数据实例")
    @ApiParam(name = "requestInstance",desc = "requestInstance",required = true)
    public Result<Void> updateRequestInstance(@RequestBody @NotNull @Valid RequestInstance requestInstance){
        requestInstanceService.updateRequestInstance(requestInstance);

        return Result.ok();
    }

    @RequestMapping(path="/deleteRequestInstance",method = RequestMethod.POST)
    @ApiMethod(name = "deleteRequestInstance",desc = "删除请求数据实例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteRequestInstance(@NotNull String id){
        requestInstanceService.deleteRequestInstance(id);

        return Result.ok();
    }

    @RequestMapping(path="/findRequestInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestInstance",desc = "查找请求数据实例")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<RequestInstance> findRequestInstance(@NotNull String id){
        RequestInstance requestInstance = requestInstanceService.findRequestInstance(id);

        return Result.ok(requestInstance);
    }

    @RequestMapping(path="/findAllRequestInstance",method = RequestMethod.POST)
    @ApiMethod(name = "findAllRequestInstance",desc = "查找所有请求数据实例")
    public Result<List<RequestInstance>> findAllRequestInstance(){
        List<RequestInstance> requestInstanceList = requestInstanceService.findAllRequestInstance();

        return Result.ok(requestInstanceList);
    }

    @RequestMapping(path = "/findRequestInstanceList",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestInstanceList",desc = "根据查询参数查询请求数据实例列表")
    @ApiParam(name = "requestInstanceQuery",desc = "requestInstanceQuery",required = true)
    public Result<List<RequestInstance>> findRequestInstanceList(@RequestBody @Valid @NotNull RequestInstanceQuery requestInstanceQuery){
        List<RequestInstance> requestInstanceList = requestInstanceService.findRequestInstanceList(requestInstanceQuery);

        return Result.ok(requestInstanceList);
    }

    @RequestMapping(path = "/findRequestInstancePage",method = RequestMethod.POST)
    @ApiMethod(name = "findRequestInstancePage",desc = "根据查询参数按分页查询请求数据实例")
    @ApiParam(name = "requestInstanceQuery",desc = "requestInstanceQuery",required = true)
    public Result<Pagination<RequestInstance>> findRequestInstancePage(@RequestBody @Valid @NotNull RequestInstanceQuery requestInstanceQuery){
        Pagination<RequestInstance> pagination = requestInstanceService.findRequestInstancePage(requestInstanceQuery);

        return Result.ok(pagination);
    }

}
