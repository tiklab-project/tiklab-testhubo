package net.tiklab.teston.test.api.http.unit.http.cases.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.teston.test.api.http.unit.cases.model.JsonResponse;
import net.tiklab.teston.test.api.http.unit.cases.model.JsonResponseQuery;
import net.tiklab.teston.test.api.http.unit.cases.service.JsonResponseService;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
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
 * JsonResponseController
 */
@RestController
@RequestMapping("/jsonResponse")
@Api(name = "JsonResponseController",desc = "接口用例步骤JsonResponseController")
public class JsonResponseController {

    private static Logger logger = LoggerFactory.getLogger(JsonResponseController.class);

    @Autowired
    private JsonResponseService jsonResponseService;

    @RequestMapping(path="/createJsonResponse",method = RequestMethod.POST)
    @ApiMethod(name = "createJsonResponse",desc = "createJsonResponse")
    @ApiParam(name = "jsonResponse",desc = "jsonResponse",required = true)
    public Result<String> createJsonResponse(@RequestBody @NotNull @Valid JsonResponse jsonResponse){
        String id = jsonResponseService.createJsonResponse(jsonResponse);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateJsonResponse",method = RequestMethod.POST)
    @ApiMethod(name = "updateJsonResponse",desc = "updateJsonResponse")
    @ApiParam(name = "jsonResponse",desc = "jsonResponse",required = true)
    public Result<Void> updateJsonResponse(@RequestBody @NotNull @Valid JsonResponse jsonResponse){
        jsonResponseService.updateJsonResponse(jsonResponse);

        return Result.ok();
    }

    @RequestMapping(path="/deleteJsonResponse",method = RequestMethod.POST)
    @ApiMethod(name = "deleteJsonResponse",desc = "deleteJsonResponse")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteJsonResponse(@NotNull String id){
        jsonResponseService.deleteJsonResponse(id);

        return Result.ok();
    }

    @RequestMapping(path="/findJsonResponse",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonResponse",desc = "findJsonResponse")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<JsonResponse> findJsonResponse(@NotNull String id){
        JsonResponse jsonResponse = jsonResponseService.findJsonResponse(id);

        return Result.ok(jsonResponse);
    }

    @RequestMapping(path="/findAllJsonResponse",method = RequestMethod.POST)
    @ApiMethod(name = "findAllJsonResponse",desc = "findAllJsonResponse")
    public Result<List<JsonResponse>> findAllJsonResponse(){
        List<JsonResponse> jsonResponseList = jsonResponseService.findAllJsonResponse();

        return Result.ok(jsonResponseList);
    }

    @RequestMapping(path = "/findJsonResponseList",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonResponseList",desc = "findJsonResponseList")
    @ApiParam(name = "jsonResponseQuery",desc = "jsonResponseQuery",required = true)
    public Result<List<JsonResponse>> findJsonResponseList(@RequestBody @Valid @NotNull JsonResponseQuery jsonResponseQuery){
        List<JsonResponse> jsonResponseList = jsonResponseService.findJsonResponseList(jsonResponseQuery);

        return Result.ok(jsonResponseList);
    }

    @RequestMapping(path = "/findJsonResponsePage",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonResponsePage",desc = "findJsonResponsePage")
    @ApiParam(name = "jsonResponseQuery",desc = "jsonResponseQuery",required = true)
    public Result<Pagination<JsonResponse>> findJsonResponsePage(@RequestBody @Valid @NotNull JsonResponseQuery jsonResponseQuery){
        Pagination<JsonResponse> pagination = jsonResponseService.findJsonResponsePage(jsonResponseQuery);

        return Result.ok(pagination);
    }

    @RequestMapping(path = "/findJsonResponseListTree",method = RequestMethod.POST)
    @ApiMethod(name = "findJsonResponseListTree",desc = "根据查询对象查询json响应结果列表树")
    @ApiParam(name = "jsonResponseQuery",desc = "查询对象",required = true)
    public Result<List<JsonResponse>> findJsonResponseListTree(@RequestBody @Valid @NotNull JsonResponseQuery jsonResponseQuery){
        List<JsonResponse> jsonResponseList = jsonResponseService.findJsonResponseListTree(jsonResponseQuery);

        return Result.ok(jsonResponseList);
    }

}
