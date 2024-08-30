package io.thoughtware.testrubo.test.apix.http.unit.cases.controller;

import io.thoughtware.testrubo.test.apix.http.unit.cases.model.FormUrlEncodedUnit;
import io.thoughtware.testrubo.test.apix.http.unit.cases.model.FormUrlencodedUnitQuery;
import io.thoughtware.testrubo.test.apix.http.unit.cases.service.FormUrlencodedService;
import io.thoughtware.postin.annotation.Api;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.Result;
import io.thoughtware.postin.annotation.ApiMethod;
import io.thoughtware.postin.annotation.ApiParam;
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
    public Result<String> createFormUrlencoded(@RequestBody @NotNull @Valid FormUrlEncodedUnit formUrlencodedUnit){
        String id = formUrlencodedService.createFormUrlencoded(formUrlencodedUnit);

        return Result.ok(id);
    }

    @RequestMapping(path="/updateFormUrlencoded",method = RequestMethod.POST)
    @ApiMethod(name = "updateFormUrlencoded",desc = "更新form-urlencoded ")
    @ApiParam(name = "formUrlencoded",desc = "formUrlencoded",required = true)
    public Result<Void> updateFormUrlencoded(@RequestBody @NotNull @Valid FormUrlEncodedUnit formUrlencodedUnit){
        formUrlencodedService.updateFormUrlencoded(formUrlencodedUnit);

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
    public Result<FormUrlEncodedUnit> findFormUrlencoded(@NotNull String id){
        FormUrlEncodedUnit formUrlencodedUnit = formUrlencodedService.findFormUrlencoded(id);

        return Result.ok(formUrlencodedUnit);
    }

    @RequestMapping(path="/findAllFormUrlencoded",method = RequestMethod.POST)
    @ApiMethod(name = "findAllFormUrlencoded",desc = "查找所有form-urlencoded")
    public Result<List<FormUrlEncodedUnit>> findAllFormUrlencoded(){
        List<FormUrlEncodedUnit> formUrlEncodedUnitList = formUrlencodedService.findAllFormUrlencoded();

        return Result.ok(formUrlEncodedUnitList);
    }

    @RequestMapping(path = "/findFormUrlencodedList",method = RequestMethod.POST)
    @ApiMethod(name = "findFormUrlencodedList",desc = "查询form-urlencoded 列表")
    @ApiParam(name = "formUrlencodedQuery",desc = "formUrlencodedQuery",required = true)
    public Result<List<FormUrlEncodedUnit>> findFormUrlencodedList(@RequestBody @Valid @NotNull FormUrlencodedUnitQuery formUrlencodedUnitQuery){
        List<FormUrlEncodedUnit> formUrlEncodedUnitList = formUrlencodedService.findFormUrlencodedList(formUrlencodedUnitQuery);

        return Result.ok(formUrlEncodedUnitList);
    }

    @RequestMapping(path = "/findFormUrlencodedPage",method = RequestMethod.POST)
    @ApiMethod(name = "findFormUrlencodedPage",desc = "按分页查询form-urlencoded ")
    @ApiParam(name = "formUrlencodedQuery",desc = "formUrlencodedQuery",required = true)
    public Result<Pagination<FormUrlEncodedUnit>> findFormUrlencodedPage(@RequestBody @Valid @NotNull FormUrlencodedUnitQuery formUrlencodedUnitQuery){
        Pagination<FormUrlEncodedUnit> pagination = formUrlencodedService.findFormUrlencodedPage(formUrlencodedUnitQuery);

        return Result.ok(pagination);
    }

}
