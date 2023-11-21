package io.tiklab.teston.test.common.ifjudgment.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.common.ifjudgment.dao.IfVariableInstanceDao;
import io.tiklab.teston.test.common.ifjudgment.entity.IfVariableInstanceEntity;
import io.tiklab.teston.test.common.ifjudgment.model.IfVariableInstance;
import io.tiklab.teston.test.common.ifjudgment.model.IfVariableInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 功能用例下步骤历史 服务
*/
@Service
public class IfVariableInstanceServiceImpl implements IfVariableInstanceService {

    @Autowired
    IfVariableInstanceDao ifVariableInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;


    @Override
    public String createIfVariableInstance(@NotNull @Valid IfVariableInstance ifVariableInstance) {
        IfVariableInstanceEntity ifVariableInstanceEntity = BeanMapper.map(ifVariableInstance, IfVariableInstanceEntity.class);

        String id = ifVariableInstanceDao.createIfVariableInstance(ifVariableInstanceEntity);

        return id;
    }

    @Override
    public void updateIfVariableInstance(@NotNull @Valid IfVariableInstance ifVariableInstance) {
        IfVariableInstanceEntity ifVariableInstanceEntity = BeanMapper.map(ifVariableInstance, IfVariableInstanceEntity.class);
        ifVariableInstanceDao.updateIfVariableInstance(ifVariableInstanceEntity);

    }

    @Override
    public void deleteIfVariableInstance(@NotNull String id) {

        ifVariableInstanceDao.deleteIfVariableInstance(id);
    }

    @Override
    public IfVariableInstance findOne(String id) {
        IfVariableInstanceEntity ifVariableInstanceEntity = ifVariableInstanceDao.findIfVariableInstance(id);

        IfVariableInstance ifVariableInstance = BeanMapper.map(ifVariableInstanceEntity, IfVariableInstance.class);
        return ifVariableInstance;
    }

    @Override
    public IfVariableInstance findIfVariableInstance(@NotNull String id) {
        IfVariableInstance ifVariableInstance = findOne(id);
        joinTemplate.joinQuery(ifVariableInstance);

        return ifVariableInstance;
    }

    @Override
    public List<IfVariableInstance> findIfVariableInstanceList(IfVariableInstanceQuery ifVariableInstanceQuery) {
        List<IfVariableInstanceEntity> ifVariableInstanceEntityList = ifVariableInstanceDao.findIfVariableInstanceList(ifVariableInstanceQuery);
        List<IfVariableInstance> ifVariableInstanceList = BeanMapper.mapList(ifVariableInstanceEntityList, IfVariableInstance.class);
        joinTemplate.joinQuery(ifVariableInstanceList);

        return ifVariableInstanceList;
    }


}