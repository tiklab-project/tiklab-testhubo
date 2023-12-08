package io.thoughtware.teston.integrated.postin.postinapi.controller;

import io.thoughtware.teston.integrated.postin.postinapi.model.Apix;
import io.thoughtware.teston.integrated.postin.postinapi.model.PostInApiToCase;
import io.thoughtware.teston.integrated.postin.postinapi.service.PostInApiService;
import io.thoughtware.core.Result;
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
 * postin接口转用例 控制器
 */
@RestController
@RequestMapping("/postInApi")
@Api(name = "PostInApiController",desc = "postin接口转用例")
public class PostInApiController {

    private static Logger logger = LoggerFactory.getLogger(PostInApiController.class);

    @Autowired
    private PostInApiService postInApiService;

    @RequestMapping(path="/createPostInApiToCase",method = RequestMethod.POST)
    @ApiMethod(name = "createPostInApiToCase",desc = "postin接口转用例")
    @ApiParam(name = "postInApiToCase",desc = "postInApiToCase",required = true)
    public Result<Void> createPostInApiToCase(@RequestBody @NotNull @Valid PostInApiToCase postInApiToCase){
         postInApiService.createPostInApiToCase(postInApiToCase);

        return Result.ok();
    }


    @RequestMapping(path = "/findPostInApiList",method = RequestMethod.POST)
    @ApiMethod(name = "findPostInApiList",desc = "根据查询仓库关联的空间查询接口")
    @ApiParam(name = "repositoryId",desc = "repositoryId")
    public Result<List<Apix>> findPostInApiList(@NotNull  String repositoryId ){
        List<Apix> postInApiList = postInApiService.findPostInApiList(repositoryId);

        return Result.ok(postInApiList);
    }



}
