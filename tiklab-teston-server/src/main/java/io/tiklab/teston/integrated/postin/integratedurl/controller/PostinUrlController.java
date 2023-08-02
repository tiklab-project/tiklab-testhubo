package io.tiklab.teston.integrated.postin.integratedurl.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.teston.integrated.postin.integratedurl.model.IntegratedUrl;
import io.tiklab.teston.integrated.postin.integratedurl.model.IntegratedUrlQuery;
import io.tiklab.teston.integrated.postin.integratedurl.service.PostinUrlService;
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
 * PostinUrl配置 控制器
 */
@RestController
@RequestMapping("/postinUrl")
@Api(name = "PostinUrlController",desc = "PostinUrl配置 管理")
public class PostinUrlController {

    private static Logger logger = LoggerFactory.getLogger(PostinUrlController.class);

    @Autowired
    private PostinUrlService postinUrlService;

    @RequestMapping(path="/createPostinUrl",method = RequestMethod.POST)
    @ApiMethod(name = "createPostinUrl",desc = "创建PostinUrl配置")
    @ApiParam(name = "integratedUrl",desc = "integratedUrl",required = true)
    public Result<String> createPostinUrl(@RequestBody @NotNull @Valid IntegratedUrl integratedUrl){
        String id = postinUrlService.createPostinUrl(integratedUrl);

        return Result.ok(id);
    }

    @RequestMapping(path="/updatePostinUrl",method = RequestMethod.POST)
    @ApiMethod(name = "updatePostinUrl",desc = "更新PostinUrl配置")
    @ApiParam(name = "integratedUrl",desc = "integratedUrl",required = true)
    public Result<Void> updatePostinUrl(@RequestBody @NotNull @Valid IntegratedUrl integratedUrl){
        postinUrlService.updatePostinUrl(integratedUrl);

        return Result.ok();
    }

    @RequestMapping(path="/deletePostinUrl",method = RequestMethod.POST)
    @ApiMethod(name = "deletePostinUrl",desc = "删除PostinUrl配置")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deletePostinUrl(@NotNull String id){
        postinUrlService.deletePostinUrl(id);

        return Result.ok();
    }

    @RequestMapping(path="/findPostinUrl",method = RequestMethod.POST)
    @ApiMethod(name = "findPostinUrl",desc = "根据id查找PostinUrl配置")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<IntegratedUrl> findPostinUrl(@NotNull String id){
        IntegratedUrl integratedUrl = postinUrlService.findPostinUrl(id);

        return Result.ok(integratedUrl);
    }

    @RequestMapping(path="/findAllPostinUrl",method = RequestMethod.POST)
    @ApiMethod(name = "findAllPostinUrl",desc = "查找所有PostinUrl配置")
    public Result<List<IntegratedUrl>> findAllPostinUrl(){
        List<IntegratedUrl> integratedUrlList = postinUrlService.findAllPostinUrl();

        return Result.ok(integratedUrlList);
    }

    @RequestMapping(path = "/findPostinUrlList",method = RequestMethod.POST)
    @ApiMethod(name = "findPostinUrlList",desc = "根据查询参数查询PostinUrl配置列表")
    @ApiParam(name = "integratedUrlQuery",desc = "integratedUrlQuery",required = true)
    public Result<List<IntegratedUrl>> findPostinUrlList(@RequestBody @Valid @NotNull IntegratedUrlQuery integratedUrlQuery){
        List<IntegratedUrl> integratedUrlList = postinUrlService.findPostinUrlList(integratedUrlQuery);

        return Result.ok(integratedUrlList);
    }

    @RequestMapping(path = "/findPostinUrlPage",method = RequestMethod.POST)
    @ApiMethod(name = "findPostinUrlPage",desc = "根据查询参数按分页查询PostinUrl配置")
    @ApiParam(name = "integratedUrlQuery",desc = "integratedUrlQuery",required = true)
    public Result<Pagination<IntegratedUrl>> findPostinUrlPage(@RequestBody @Valid @NotNull IntegratedUrlQuery integratedUrlQuery){
        Pagination<IntegratedUrl> pagination = postinUrlService.findPostinUrlPage(integratedUrlQuery);

        return Result.ok(pagination);
    }

}
