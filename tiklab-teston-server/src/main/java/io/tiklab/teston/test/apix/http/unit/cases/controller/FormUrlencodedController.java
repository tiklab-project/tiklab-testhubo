package io.tiklab.teston.test.apix.http.unit.cases.controller;

import io.tiklab.postin.annotation.Api;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.Result;
import io.tiklab.postin.annotation.ApiMethod;
import io.tiklab.postin.annotation.ApiParam;
import io.tiklab.teston.test.apix.http.unit.cases.model.FormUrlEncoded;
import io.tiklab.teston.test.apix.http.unit.cases.model.FormUrlencodedQuery;
import io.tiklab.teston.test.apix.http.unit.cases.service.FormUrlencodedService;
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
 * form-urlencoded 控制器
 */
@RestController
@RequestMapping("/formUrlencoded")
@Api(name = "FormUrlencodedController",desc = "form-urlencoded 参数管理")
public class FormUrlencodedController {

    private static Logger logger = LoggerFactory.getLogger(FormUrlencodedController.class);

    @Autowired
    private FormUrlencodedService formUrlencodedService;

    @RequestMapping(path="/createFormUrlencoded",method = RequestMethod.POST)
    @ApiMethod(name = "createFormUrlencoded",desc = "创建form-urlencoded ")
    @ApiParam(name = "formUrlencoded",desc = "formUrlencoded",required = true)
    public Result<String> createFormUrlencoded(@RequestBody @NotNull @Valid FormUrlEncoded formUrlencoded){
        String id = formUrlencodedService.createFormUrlencoded(formUrlencoded);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateFormUrlencoded",method = RequestMethod.POST)
    @ApiMethod(name = "updateFormUrlencoded",desc = "更新form-urlencoded ")
    @ApiParam(name = "formUrlencoded",desc = "formUrlencoded",required = true)
    public Result<Void> updateFormUrlencoded(@RequestBody @NotNull @Valid FormUrlEncoded formUrlencoded){
        formUrlencodedService.updateFormUrlencoded(formUrlencoded);

        return Result.ok();
    }

    @RequestMapping(path="/deleteFormUrlencoded",method = RequestMethod.POST)
    @ApiMethod(name = "deleteFormUrlencoded",desc = "删除form-urlencoded ")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deleteFormUrlencoded(@NotNull String id){
        formUrlencodedService.deleteFormUrlencoded(id);

        return Result.ok();
    }

    @RequestMapping(path="/findFormUrlencoded",method = RequestMethod.POST)
    @ApiMethod(name = "findFormUrlencoded",desc = "根据id查找form-urlencoded")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<FormUrlEncoded> findFormUrlencoded(@NotNull String id){
        FormUrlEncoded formUrlencoded = formUrlencodedService.findFormUrlencoded(id);

        return Result.ok(formUrlencoded);
    }

    @RequestMapping(path="/findAllFormUrlencoded",method = RequestMethod.POST)
    @ApiMethod(name = "findAllFormUrlencoded",desc = "查找所有form-urlencoded")
    public Result<List<FormUrlEncoded>> findAllFormUrlencoded(){
        List<FormUrlEncoded> formUrlEncodedList = formUrlencodedService.findAllFormUrlencoded();

        return Result.ok(formUrlEncodedList);
    }

    @RequestMapping(path = "/findFormUrlencodedList",method = RequestMethod.POST)
    @ApiMethod(name = "findFormUrlencodedList",desc = "查询form-urlencoded 列表")
    @ApiParam(name = "formUrlencodedQuery",desc = "formUrlencodedQuery",required = true)
    public Result<List<FormUrlEncoded>> findFormUrlencodedList(@RequestBody @Valid @NotNull FormUrlencodedQuery formUrlencodedQuery){
        List<FormUrlEncoded> formUrlEncodedList = formUrlencodedService.findFormUrlencodedList(formUrlencodedQuery);

        return Result.ok(formUrlEncodedList);
    }

    @RequestMapping(path = "/findFormUrlencodedPage",method = RequestMethod.POST)
    @ApiMethod(name = "findFormUrlencodedPage",desc = "按分页查询form-urlencoded ")
    @ApiParam(name = "formUrlencodedQuery",desc = "formUrlencodedQuery",required = true)
    public Result<Pagination<FormUrlEncoded>> findFormUrlencodedPage(@RequestBody @Valid @NotNull FormUrlencodedQuery formUrlencodedQuery){
        Pagination<FormUrlEncoded> pagination = formUrlencodedService.findFormUrlencodedPage(formUrlencodedQuery);

        return Result.ok(pagination);
    }

}
