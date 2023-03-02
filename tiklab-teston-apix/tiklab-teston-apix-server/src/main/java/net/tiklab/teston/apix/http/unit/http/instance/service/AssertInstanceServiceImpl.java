package net.tiklab.teston.apix.http.unit.http.instance.service;

import net.tiklab.core.page.PaginationBuilder;


import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.apix.http.unit.http.instance.dao.AssertInstanceDao;
import net.tiklab.teston.apix.http.unit.http.instance.entity.AssertInstanceEntity;
import net.tiklab.teston.apix.http.unit.instance.model.AssertInstance;
import net.tiklab.teston.apix.http.unit.instance.model.AssertInstanceQuery;
import net.tiklab.teston.apix.http.unit.instance.service.AssertInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 断言实例 服务
*/
@Service
public class AssertInstanceServiceImpl implements AssertInstanceService {

    @Autowired
    AssertInstanceDao assertInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAssertInstance(@NotNull @Valid AssertInstance assertInstance) {
        AssertInstanceEntity assertInstanceEntity = BeanMapper.map(assertInstance, AssertInstanceEntity.class);

        return assertInstanceDao.createAssertInstance(assertInstanceEntity);
    }

    @Override
    public void updateAssertInstance(@NotNull @Valid AssertInstance assertInstance) {
        AssertInstanceEntity assertInstanceEntity = BeanMapper.map(assertInstance, AssertInstanceEntity.class);

        assertInstanceDao.updateAssertInstance(assertInstanceEntity);
    }

    @Override
    public void deleteAssertInstance(@NotNull String id) {
        assertInstanceDao.deleteAssertInstance(id);
    }



    @Override
    public AssertInstance findOne(String id) {
        AssertInstanceEntity assertInstanceEntity = assertInstanceDao.findAssertInstance(id);

        AssertInstance assertInstance = BeanMapper.map(assertInstanceEntity, AssertInstance.class);
        return assertInstance;
    }

    @Override
    public List<AssertInstance> findList(List<String> idList) {
        List<AssertInstanceEntity> assertInstanceEntityList =  assertInstanceDao.findAssertInstanceList(idList);

        List<AssertInstance> assertInstanceList =  BeanMapper.mapList(assertInstanceEntityList,AssertInstance.class);
        return assertInstanceList;
    }

    @Override
    public AssertInstance findAssertInstance(@NotNull String id) {
        AssertInstance assertInstance = findOne(id);

        joinTemplate.joinQuery(assertInstance);
        return assertInstance;
    }

    @Override
    public List<AssertInstance> findAllAssertInstance() {
        List<AssertInstanceEntity> assertInstanceEntityList =  assertInstanceDao.findAllAssertInstance();

        List<AssertInstance> assertInstanceList =  BeanMapper.mapList(assertInstanceEntityList,AssertInstance.class);

        joinTemplate.joinQuery(assertInstanceList);
        return assertInstanceList;
    }

    @Override
    public List<AssertInstance> findAssertInstanceList(AssertInstanceQuery assertInstanceQuery) {
        List<AssertInstanceEntity> assertInstanceEntityList = assertInstanceDao.findAssertInstanceList(assertInstanceQuery);

        List<AssertInstance> assertInstanceList = BeanMapper.mapList(assertInstanceEntityList,AssertInstance.class);

        joinTemplate.joinQuery(assertInstanceList);

        return assertInstanceList;
    }

    @Override
    public Pagination<AssertInstance> findAssertInstancePage(AssertInstanceQuery assertInstanceQuery) {

        Pagination<AssertInstanceEntity>  pagination = assertInstanceDao.findAssertInstancePage(assertInstanceQuery);

        List<AssertInstance> assertInstanceList = BeanMapper.mapList(pagination.getDataList(),AssertInstance.class);

        joinTemplate.joinQuery(assertInstanceList);

        return PaginationBuilder.build(pagination,assertInstanceList);
    }
}