package io.thoughtware.testrubo.integrated.integratedurl.controller;

import io.thoughtware.testrubo.integrated.integratedurl.model.IntegratedUrl;
import io.thoughtware.testrubo.integrated.integratedurl.model.IntegratedUrlQuery;
import io.thoughtware.testrubo.integrated.integratedurl.service.IntegratedUrlService;
import io.thoughtware.core.Result;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
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
 * IntegratedUrl配置 控制器
 */
@RestController
@RequestMapping("/integratedUrl")
@Api(name = "IntegratedUrlController",desc = "IntegratedUrl配置 管理")
public class IntegratedUrlController {

    private static Logger logger = LoggerFactory.getLogger(IntegratedUrlController.class);

    @Autowired
    private IntegratedUrlService integratedUrlService;

    @RequestMapping(path="/createIntegratedUrl",method = RequestMethod.POST)
    @ApiMethod(name = "createIntegratedUrl",desc = "创建IntegratedUrl配置")
    @ApiParam(name = "integratedUrl",desc = "integratedUrl",required = true)
    public Result<String> createIntegratedUrl(@RequestBody @NotNull @Valid IntegratedUrl integratedUrl){
        String id = integratedUrlService.createIntegratedUrl(integratedUrl);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateIntegratedUrl",method = RequestMethod.POST)
    @ApiMethod(name = "updateIntegratedUrl",desc = "更新IntegratedUrl配置")
    @ApiParam(name = "integratedUrl",desc = "integratedUrl",required = true)
    public Result<Void> updateIntegratedUrl(@RequestBody @NotNull @Valid IntegratedUrl integratedUrl){
        integratedUrlService.updateIntegratedUrl(integratedUrl);

        return Result.ok();
    }

    @RequestMapping(path="/deleteIntegratedUrl",method = RequestMethod.POST)
    @ApiMethod(name = "deleteIntegratedUrl",desc = "删除IntegratedUrl配置")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteIntegratedUrl(@NotNull String id){
        integratedUrlService.deleteIntegratedUrl(id);

        return Result.ok();
    }

    @RequestMapping(path="/findIntegratedUrl",method = RequestMethod.POST)
    @ApiMethod(name = "findIntegratedUrl",desc = "根据id查找IntegratedUrl配置")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<IntegratedUrl> findIntegratedUrl(@NotNull String id){
        IntegratedUrl integratedUrl = integratedUrlService.findIntegratedUrl(id);

        return Result.ok(integratedUrl);
    }

    @RequestMapping(path="/findAllIntegratedUrl",method = RequestMethod.POST)
    @ApiMethod(name = "findAllIntegratedUrl",desc = "查找所有IntegratedUrl配置")
    public Result<List<IntegratedUrl>> findAllIntegratedUrl(){
        List<IntegratedUrl> integratedUrlList = integratedUrlService.findAllIntegratedUrl();

        return Result.ok(integratedUrlList);
    }

    @RequestMapping(path = "/findIntegratedUrlList",method = RequestMethod.POST)
    @ApiMethod(name = "findIntegratedUrlList",desc = "根据查询参数查询IntegratedUrl配置列表")
    @ApiParam(name = "integratedUrlQuery",desc = "integratedUrlQuery",required = true)
    public Result<List<IntegratedUrl>> findIntegratedUrlList(@RequestBody @Valid @NotNull IntegratedUrlQuery integratedUrlQuery){
        List<IntegratedUrl> integratedUrlList = integratedUrlService.findIntegratedUrlList(integratedUrlQuery);

        return Result.ok(integratedUrlList);
    }

    @RequestMapping(path = "/findIntegratedUrlPage",method = RequestMethod.POST)
    @ApiMethod(name = "findIntegratedUrlPage",desc = "根据查询参数按分页查询IntegratedUrl配置")
    @ApiParam(name = "integratedUrlQuery",desc = "integratedUrlQuery",required = true)
    public Result<Pagination<IntegratedUrl>> findIntegratedUrlPage(@RequestBody @Valid @NotNull IntegratedUrlQuery integratedUrlQuery){
        Pagination<IntegratedUrl> pagination = integratedUrlService.findIntegratedUrlPage(integratedUrlQuery);

        return Result.ok(pagination);
    }

}
