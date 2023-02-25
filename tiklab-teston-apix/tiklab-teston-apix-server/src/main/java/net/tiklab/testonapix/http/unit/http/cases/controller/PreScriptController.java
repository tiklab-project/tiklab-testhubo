package net.tiklab.testonapix.http.unit.http.cases.controller;

import net.tiklab.postin.annotation.Api;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.Result;
import net.tiklab.postin.annotation.ApiMethod;
import net.tiklab.postin.annotation.ApiParam;
import net.tiklab.testonapix.http.unit.cases.model.PreScript;
import net.tiklab.testonapix.http.unit.cases.model.PreScriptQuery;
import net.tiklab.testonapix.http.unit.cases.service.PreScriptService;
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
 * PreScriptController
 */
@RestController
@RequestMapping("/preScript")
@Api(name = "PreScriptController",desc = "接口用例步骤PreScriptController")
public class PreScriptController {

    private static Logger logger = LoggerFactory.getLogger(PreScriptController.class);

    @Autowired
    private PreScriptService preScriptService;

    @RequestMapping(path="/createPreScript",method = RequestMethod.POST)
    @ApiMethod(name = "createPreScript",desc = "createPreScript")
    @ApiParam(name = "preScript",desc = "preScript",required = true)
    public Result<String> createPreScript(@RequestBody @NotNull @Valid PreScript preScript){
        String id = preScriptService.createPreScript(preScript);

        return Result.ok(id);
    }

    @RequestMapping(path="/updatePreScript",method = RequestMethod.POST)
    @ApiMethod(name = "updatePreScript",desc = "updatePreScript")
    @ApiParam(name = "preScript",desc = "preScript",required = true)
    public Result<Void> updatePreScript(@RequestBody @NotNull @Valid PreScript preScript){
        preScriptService.updatePreScript(preScript);

        return Result.ok();
    }

    @RequestMapping(path="/deletePreScript",method = RequestMethod.POST)
    @ApiMethod(name = "deletePreScript",desc = "deletePreScript")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<Void> deletePreScript(@NotNull String id){
        preScriptService.deletePreScript(id);

        return Result.ok();
    }

    @RequestMapping(path="/findPreScript",method = RequestMethod.POST)
    @ApiMethod(name = "findPreScript",desc = "findPreScript")
    @ApiParam(name = "id",desc = "id",required = true)
    public Result<PreScript> findPreScript(@NotNull String id){
        PreScript preScript = preScriptService.findPreScript(id);

        return Result.ok(preScript);
    }

    @RequestMapping(path="/findAllPreScript",method = RequestMethod.POST)
    @ApiMethod(name = "findAllPreScript",desc = "findAllPreScript")
    public Result<List<PreScript>> findAllPreScript(){
        List<PreScript> preScriptList = preScriptService.findAllPreScript();

        return Result.ok(preScriptList);
    }

    @RequestMapping(path = "/findPreScriptList",method = RequestMethod.POST)
    @ApiMethod(name = "findPreScriptList",desc = "findPreScriptList")
    @ApiParam(name = "preScriptQuery",desc = "preScriptQuery",required = true)
    public Result<List<PreScript>> findPreScriptList(@RequestBody @Valid @NotNull PreScriptQuery preScriptQuery){
        List<PreScript> preScriptList = preScriptService.findPreScriptList(preScriptQuery);

        return Result.ok(preScriptList);
    }

    @RequestMapping(path = "/findPreScriptPage",method = RequestMethod.POST)
    @ApiMethod(name = "findPreScriptPage",desc = "findPreScriptPage")
    @ApiParam(name = "preScriptQuery",desc = "preScriptQuery",required = true)
    public Result<Pagination<PreScript>> findPreScriptPage(@RequestBody @Valid @NotNull PreScriptQuery preScriptQuery){
        Pagination<PreScript> pagination = preScriptService.findPreScriptPage(preScriptQuery);

        return Result.ok(pagination);
    }

}
