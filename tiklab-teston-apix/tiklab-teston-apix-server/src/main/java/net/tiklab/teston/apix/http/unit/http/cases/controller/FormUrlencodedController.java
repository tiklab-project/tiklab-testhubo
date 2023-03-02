package net.tiklab.teston.apix.http.unit.http.cases.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.teston.apix.http.unit.cases.model.FormUrlencoded;
import net.tiklab.teston.apix.http.unit.cases.model.FormUrlencodedQuery;
import net.tiklab.teston.apix.http.unit.cases.service.FormUrlencodedService;
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
    public Result<String> createFormUrlencoded(@RequestBody @NotNull @Valid FormUrlencoded formUrlencoded){
        String id = formUrlencodedService.createFormUrlencoded(formUrlencoded);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateFormUrlencoded",method = RequestMethod.POST)
    @ApiMethod(name = "updateFormUrlencoded",desc = "更新form-urlencoded ")
    @ApiParam(name = "formUrlencoded",desc = "formUrlencoded",required = true)
    public Result<Void> updateFormUrlencoded(@RequestBody @NotNull @Valid FormUrlencoded formUrlencoded){
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
    public Result<FormUrlencoded> findFormUrlencoded(@NotNull String id){
        FormUrlencoded formUrlencoded = formUrlencodedService.findFormUrlencoded(id);

        return Result.ok(formUrlencoded);
    }

    @RequestMapping(path="/findAllFormUrlencoded",method = RequestMethod.POST)
    @ApiMethod(name = "findAllFormUrlencoded",desc = "查找所有form-urlencoded")
    public Result<List<FormUrlencoded>> findAllFormUrlencoded(){
        List<FormUrlencoded> formUrlencodedList = formUrlencodedService.findAllFormUrlencoded();

        return Result.ok(formUrlencodedList);
    }

    @RequestMapping(path = "/findFormUrlencodedList",method = RequestMethod.POST)
    @ApiMethod(name = "findFormUrlencodedList",desc = "查询form-urlencoded 列表")
    @ApiParam(name = "formUrlencodedQuery",desc = "formUrlencodedQuery",required = true)
    public Result<List<FormUrlencoded>> findFormUrlencodedList(@RequestBody @Valid @NotNull FormUrlencodedQuery formUrlencodedQuery){
        List<FormUrlencoded> formUrlencodedList = formUrlencodedService.findFormUrlencodedList(formUrlencodedQuery);

        return Result.ok(formUrlencodedList);
    }

    @RequestMapping(path = "/findFormUrlencodedPage",method = RequestMethod.POST)
    @ApiMethod(name = "findFormUrlencodedPage",desc = "按分页查询form-urlencoded ")
    @ApiParam(name = "formUrlencodedQuery",desc = "formUrlencodedQuery",required = true)
    public Result<Pagination<FormUrlencoded>> findFormUrlencodedPage(@RequestBody @Valid @NotNull FormUrlencodedQuery formUrlencodedQuery){
        Pagination<FormUrlencoded> pagination = formUrlencodedService.findFormUrlencodedPage(formUrlencodedQuery);

        return Result.ok(pagination);
    }

}
