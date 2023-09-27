package io.tiklab.teston.support.variable.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.support.variable.dao.VariableDao;
import io.tiklab.teston.support.variable.entity.VariableEntity;
import io.tiklab.teston.support.variable.model.Variable;
import io.tiklab.teston.support.variable.model.VariableQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;


/**
* 场景变量 服务
*/
@Service
public class VariableServiceImpl implements VariableService {

    @Autowired
    VariableDao variableDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createVariable(@NotNull @Valid Variable variable) {
        VariableEntity variableEntity = BeanMapper.map(variable, VariableEntity.class);

        variableEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return variableDao.createVariable(variableEntity);
    }

    @Override
    public void updateVariable(@NotNull @Valid Variable variable) {
        VariableEntity variableEntity = BeanMapper.map(variable, VariableEntity.class);

        variableDao.updateVariable(variableEntity);
    }

    @Override
    public void deleteVariable(@NotNull String id) {
        variableDao.deleteVariable(id);
    }

    @Override
    public Variable findOne(String id) {
        VariableEntity variableEntity = variableDao.findVariable(id);

        Variable variable = BeanMapper.map(variableEntity, Variable.class);
        return variable;
    }

    @Override
    public List<Variable> findList(List<String> idList) {
        List<VariableEntity> variableEntityList =  variableDao.findVariableList(idList);

        List<Variable> variableList =  BeanMapper.mapList(variableEntityList,Variable.class);
        return variableList;
    }

    @Override
    public Variable findVariable(@NotNull String id) {
        Variable variable = findOne(id);

        joinTemplate.joinQuery(variable);

        return variable;
    }

    @Override
    public List<Variable> findAllVariable() {
        List<VariableEntity> variableEntityList =  variableDao.findAllVariable();

        List<Variable> variableList =  BeanMapper.mapList(variableEntityList,Variable.class);

        joinTemplate.joinQuery(variableList);

        return variableList;
    }

    @Override
    public List<Variable> findVariableList(VariableQuery variableQuery) {
        List<VariableEntity> variableEntityList = variableDao.findVariableList(variableQuery);

        List<Variable> variableList = BeanMapper.mapList(variableEntityList,Variable.class);

        joinTemplate.joinQuery(variableList);

        return variableList;
    }

    @Override
    public Pagination<Variable> findVariablePage(VariableQuery variableQuery) {
        Pagination<VariableEntity>  pagination = variableDao.findVariablePage(variableQuery);

        List<Variable> variableList = BeanMapper.mapList(pagination.getDataList(),Variable.class);

        joinTemplate.joinQuery(variableList);

        return PaginationBuilder.build(pagination,variableList);
    }



    /**
     * 获取变量
     */
    @Override
    public JSONObject getVariable (String belongId){
        JSONObject variableJson = new JSONObject();

        VariableQuery variableQuery = new VariableQuery();
        variableQuery.setBelongId(belongId);
        List<Variable> variableList = findVariableList(variableQuery);
        if(variableList!=null&&variableList.size()>0){
            for(Variable variable:variableList){
                variableJson.put(variable.getName(),variable.getValue());
            }
        }

        return variableJson;
    }
}