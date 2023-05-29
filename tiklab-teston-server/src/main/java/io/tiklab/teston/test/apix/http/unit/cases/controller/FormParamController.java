package io.tiklab.teston.test.apix.http.unit.cases.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.teston.test.apix.http.unit.cases.model.FormParams;
import io.tiklab.teston.test.apix.http.unit.cases.model.FormParamQuery;
import io.tiklab.teston.test.apix.http.unit.cases.service.FormParamService;
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
 * form-data 控制器
 */
@RestController
@RequestMapping("/formParam")
@Api(name = "FormParamController",desc = "接口用例步骤form 参数管理")
public class FormParamController {

    private static Logger logger = LoggerFactory.getLogger(FormParamController.class);

    @Autowired
    private FormParamService formParamService;

    @RequestMapping(path="/createFormParam",method = RequestMethod.POST)
    @ApiMethod(name = "createFormParam",desc = "创建form-data")
    @ApiParam(name = "formParam",desc = "formParam",required = true)
    public Result<String> createFormParam(@RequestBody @NotNull @Valid FormParams formParams){
        String id = formParamService.createFormParam(formParams);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateFormParam",method = RequestMethod.POST)
    @ApiMethod(name = "updateFormParam",desc = "更新form-data")
    @ApiParam(name = "formParam",desc = "formParam",required = true)
    public Result<Void> updateFormParam(@RequestBody @NotNull @Valid FormParams formParams){
        formParamService.updateFormParam(formParams);

        return Result.ok();
    }

    @RequestMapping(path="/deleteFormParam",method = RequestMethod.POST)
    @ApiMethod(name = "deleteFormParam",desc = "删除form-data")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteFormParam(@NotNull String id){
        formParamService.deleteFormParam(id);

        return Result.ok();
    }

    @RequestMapping(path="/findFormParam",method = RequestMethod.POST)
    @ApiMethod(name = "findFormParam",desc = "根据id查找form-data")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<FormParams> findFormParam(@NotNull String id){
        FormParams formParams = formParamService.findFormParam(id);

        return Result.ok(formParams);
    }

    @RequestMapping(path="/findAllFormParam",method = RequestMethod.POST)
    @ApiMethod(name = "findAllFormParam",desc = "查找所有form-data")
    public Result<List<FormParams>> findAllFormParam(){
        List<FormParams> formParamsList = formParamService.findAllFormParam();

        return Result.ok(formParamsList);
    }

    @RequestMapping(path = "/findFormParamList",method = RequestMethod.POST)
    @ApiMethod(name = "findFormParamList",desc = "根据查询参数查询form-data列表")
    @ApiParam(name = "formParamQuery",desc = "formParamQuery",required = true)
    public Result<List<FormParams>> findFormParamList(@RequestBody @Valid @NotNull FormParamQuery formParamQuery){
        List<FormParams> formParamsList = formParamService.findFormParamList(formParamQuery);

        return Result.ok(formParamsList);
    }

    @RequestMapping(path = "/findFormParamPage",method = RequestMethod.POST)
    @ApiMethod(name = "findFormParamPage",desc = "根据查询参数按分页查询form-data")
    @ApiParam(name = "formParamQuery",desc = "formParamQuery",required = true)
    public Result<Pagination<FormParams>> findFormParamPage(@RequestBody @Valid @NotNull FormParamQuery formParamQuery){
        Pagination<FormParams> pagination = formParamService.findFormParamPage(formParamQuery);

        return Result.ok(pagination);
    }

}