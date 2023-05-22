package io.tiklab.teston.integratedpostin.postinurl.controller;

import io.tiklab.core.Result;
import io.tiklab.core.page.Pagination;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.teston.integratedpostin.postinurl.model.PostinUrl;
import io.tiklab.teston.integratedpostin.postinurl.model.PostinUrlQuery;
import io.tiklab.teston.integratedpostin.postinurl.service.PostinUrlService;
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
    @ApiParam(name = "postinUrl",desc = "postinUrl",required = true)
    public Result<String> createPostinUrl(@RequestBody @NotNull @Valid PostinUrl postinUrl){
        String id = postinUrlService.createPostinUrl(postinUrl);

        return Result.ok(id);
    }

    @RequestMapping(path="/updatePostinUrl",method = RequestMethod.POST)
    @ApiMethod(name = "updatePostinUrl",desc = "更新PostinUrl配置")
    @ApiParam(name = "postinUrl",desc = "postinUrl",required = true)
    public Result<Void> updatePostinUrl(@RequestBody @NotNull @Valid PostinUrl postinUrl){
        postinUrlService.updatePostinUrl(postinUrl);

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
    public Result<PostinUrl> findPostinUrl(@NotNull String id){
        PostinUrl postinUrl = postinUrlService.findPostinUrl(id);

        return Result.ok(postinUrl);
    }

    @RequestMapping(path="/findAllPostinUrl",method = RequestMethod.POST)
    @ApiMethod(name = "findAllPostinUrl",desc = "查找所有PostinUrl配置")
    public Result<List<PostinUrl>> findAllPostinUrl(){
        List<PostinUrl> postinUrlList = postinUrlService.findAllPostinUrl();

        return Result.ok(postinUrlList);
    }

    @RequestMapping(path = "/findPostinUrlList",method = RequestMethod.POST)
    @ApiMethod(name = "findPostinUrlList",desc = "根据查询参数查询PostinUrl配置列表")
    @ApiParam(name = "postinUrlQuery",desc = "postinUrlQuery",required = true)
    public Result<List<PostinUrl>> findPostinUrlList(@RequestBody @Valid @NotNull PostinUrlQuery postinUrlQuery){
        List<PostinUrl> postinUrlList = postinUrlService.findPostinUrlList(postinUrlQuery);

        return Result.ok(postinUrlList);
    }

    @RequestMapping(path = "/findPostinUrlPage",method = RequestMethod.POST)
    @ApiMethod(name = "findPostinUrlPage",desc = "根据查询参数按分页查询PostinUrl配置")
    @ApiParam(name = "postinUrlQuery",desc = "postinUrlQuery",required = true)
    public Result<Pagination<PostinUrl>> findPostinUrlPage(@RequestBody @Valid @NotNull PostinUrlQuery postinUrlQuery){
        Pagination<PostinUrl> pagination = postinUrlService.findPostinUrlPage(postinUrlQuery);

        return Result.ok(pagination);
    }

}
