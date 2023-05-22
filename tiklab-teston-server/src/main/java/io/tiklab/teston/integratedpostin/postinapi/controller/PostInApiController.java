package io.tiklab.teston.integratedpostin.postinapi.controller;

import io.tiklab.core.Result;
import io.tiklab.postin.annotation.Api;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.postin.api.apix.model.ApixQuery;
import io.tiklab.postin.api.http.definition.model.HttpApi;
import io.tiklab.teston.integratedpostin.postinapi.PostInApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 接口单元用例 控制器
 */
@RestController
@RequestMapping("/postInApi")
@Api(name = "PostInApiController",desc = "api单元测试用例管理")
public class PostInApiController {

    private static Logger logger = LoggerFactory.getLogger(PostInApiController.class);

    @Autowired
    private PostInApiService postInApiService;
//
//    @RequestMapping(path="/createPostInApi",method = RequestMethod.POST)
//    @ApiMethod(name = "createPostInApi",desc = "创建接口单元用例")
//    @ApiParam(name = "path",desc = "path",required = true)
//    public Result<String> createPostInApi(@RequestBody @NotNull @Valid PostInApi postInApi){
//        String id = postInApiService.createPostInApi(postInApi);
//
//        return Result.ok(id);
//    }


    @RequestMapping(path = "/findPostInApiList",method = RequestMethod.POST)
    @ApiMethod(name = "findPostInApiList",desc = "根据查询参数查询接口单元列表")
    @ApiParam(name = "postInApiQuery",desc = "postInApiQuery",required = true)
    public Result<List<HttpApi>> findPostInApiList(@RequestBody @Valid  ApixQuery apixQuery ){
        List<HttpApi> postInApiList = postInApiService.findPostInApiList(apixQuery);

        return Result.ok(postInApiList);
    }



}
