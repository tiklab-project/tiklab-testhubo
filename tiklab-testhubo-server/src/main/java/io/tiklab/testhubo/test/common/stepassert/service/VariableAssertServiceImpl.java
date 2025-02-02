package io.tiklab.testhubo.test.common.stepassert.service;

import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.testhubo.test.common.stepassert.model.VariableAssert;
import io.tiklab.testhubo.test.common.stepassert.dao.VariableAssertDao;
import io.tiklab.testhubo.test.common.stepassert.entity.VariableAssertEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
* 变量断言 服务
*/
@Service
public class VariableAssertServiceImpl implements VariableAssertService {

    @Autowired
    VariableAssertDao variableAssertDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createVariableAssert(@NotNull @Valid VariableAssert variableAssert) {
        VariableAssertEntity variableAssertEntity = BeanMapper.map(variableAssert, VariableAssertEntity.class);

        return variableAssertDao.createVariableAssert(variableAssertEntity);
    }

    @Override
    public void updateVariableAssert(@NotNull @Valid VariableAssert variableAssert) {

        VariableAssertEntity variableAssertEntity = BeanMapper.map(variableAssert, VariableAssertEntity.class);

        variableAssertDao.updateVariableAssert(variableAssertEntity);
    }

    @Override
    public void deleteVariableAssert(@NotNull String id) {

        variableAssertDao.deleteVariableAssert(id);
    }

    @Override
    public VariableAssert findOne(String id) {
        VariableAssertEntity variableAssertEntity = variableAssertDao.findVariableAssert(id);

        VariableAssert variableAssert = BeanMapper.map(variableAssertEntity, VariableAssert.class);
        return variableAssert;
    }

    @Override
    public VariableAssert findVariableAssert(@NotNull String id) {
        VariableAssert variableAssert = findOne(id);

        joinTemplate.joinQuery(variableAssert);

        return variableAssert;
    }


}