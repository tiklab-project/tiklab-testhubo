package io.thoughtware.teston.instance.service;

import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.teston.common.MagicValue;
import io.thoughtware.teston.instance.dao.InstanceDao;
import io.thoughtware.teston.instance.entity.InstanceEntity;
import io.thoughtware.teston.instance.model.Instance;
import io.thoughtware.teston.instance.model.InstanceQuery;
import io.thoughtware.user.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* 公共实例 服务
*/
@Service
public class InstanceServiceImpl implements InstanceService {

    @Autowired
    InstanceDao instanceDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createInstance(@NotNull @Valid Instance instance) {

        instance.setCreateTime(new Timestamp(System.currentTimeMillis()));
        User user = new User();
        user.setId(LoginContext.getLoginId());
        instance.setCreateUser(user);
        InstanceEntity instanceEntity = BeanMapper.map(instance, InstanceEntity.class);

        return instanceDao.createInstance(instanceEntity);
    }

    @Override
    public void updateInstance(@NotNull @Valid Instance instance) {
        InstanceEntity instanceEntity = BeanMapper.map(instance, InstanceEntity.class);

        instanceDao.updateInstance(instanceEntity);
    }

    @Override
    public void deleteInstance(@NotNull String id) {
        instanceDao.deleteInstance(id);
    }

    @Override
    public Instance findOne(String id) {
        InstanceEntity instanceEntity = instanceDao.findInstance(id);

        Instance instance = BeanMapper.map(instanceEntity, Instance.class);
        return instance;
    }

    @Override
    public List<Instance> findList(List<String> idList) {
        List<InstanceEntity> instanceEntityList =  instanceDao.findInstanceList(idList);

        List<Instance> instanceList =  BeanMapper.mapList(instanceEntityList, Instance.class);
        return instanceList;
    }

    @Override
    public Instance findInstance(@NotNull String id) {
        Instance instance = findOne(id);

        joinTemplate.joinQuery(instance);
        return instance;
    }

    @Override
    public List<Instance> findAllInstance() {
        List<InstanceEntity> instanceEntityList =  instanceDao.findAllInstance();

        List<Instance> instanceList =  BeanMapper.mapList(instanceEntityList, Instance.class);

        joinTemplate.joinQuery(instanceList);
        return instanceList;
    }

    @Override
    public List<Instance> findInstanceList(InstanceQuery instanceQuery) {
        List<InstanceEntity> instanceEntityList = instanceDao.findInstanceList(instanceQuery);

        List<Instance> instanceList = BeanMapper.mapList(instanceEntityList, Instance.class);

        joinTemplate.joinQuery(instanceList);

        return instanceList;
    }

    @Override
    public Pagination<Instance> findInstancePage(InstanceQuery instanceQuery) {

        Pagination<InstanceEntity>  pagination = instanceDao.findInstancePage(instanceQuery);

        List<Instance> instanceList = BeanMapper.mapList(pagination.getDataList(), Instance.class);

        joinTemplate.joinQuery(instanceList);

        return PaginationBuilder.build(pagination, instanceList);
    }



}