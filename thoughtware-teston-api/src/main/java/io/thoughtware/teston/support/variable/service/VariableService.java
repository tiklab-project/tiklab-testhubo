package io.thoughtware.teston.support.variable.service;

import com.alibaba.fastjson.JSONObject;
import io.thoughtware.teston.support.variable.model.VariableQuery;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.join.annotation.FindAll;
import io.thoughtware.toolkit.join.annotation.FindList;
import io.thoughtware.toolkit.join.annotation.FindOne;
import io.thoughtware.toolkit.join.annotation.JoinProvider;
import io.thoughtware.teston.support.variable.model.Variable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 场景的环境变量 服务接口
*/
@JoinProvider(model = Variable.class)
public interface VariableService {

    /**
    * 创建场景的环境变量
    * @param variable
    * @return
    */
    String createVariable(@NotNull @Valid Variable variable);

    /**
    * 更新场景的环境变量
    * @param variable
    */
    void updateVariable(@NotNull @Valid Variable variable);

    /**
    * 删除场景的环境变量
    * @param id
    */
    void deleteVariable(@NotNull String id);
    @FindOne
    Variable findOne(@NotNull String id);
    @FindList
    List<Variable> findList(List<String> idList);

    /**
    * 根据id查找场景的环境变量
    * @param id
    * @return
    */
    Variable findVariable(@NotNull String id);

    /**
    * 查找所有场景的环境变量
    * @return
    */
    @FindAll
    List<Variable> findAllVariable();

    /**
    * 根据查询参数查询场景的环境变量列表
    * @param variableQuery
    * @return
    */
    List<Variable> findVariableList(VariableQuery variableQuery);

    /**
    * 根据查询参数按分页场景的环境变量
    * @param variableQuery
    * @return
    */
    Pagination<Variable> findVariablePage(VariableQuery variableQuery);

    /**
     * 获取变量存为JSONObject
     * @param belongId
     * @return
     */
     JSONObject getVariable (String belongId);
}