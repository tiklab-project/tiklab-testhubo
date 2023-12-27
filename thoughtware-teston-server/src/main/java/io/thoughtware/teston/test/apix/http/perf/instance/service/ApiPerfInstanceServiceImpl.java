package io.thoughtware.teston.test.apix.http.perf.instance.service;

import io.thoughtware.core.page.PaginationBuilder;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;

import io.thoughtware.teston.test.apix.http.perf.instance.entity.ApiPerfInstanceEntity;
import io.thoughtware.teston.test.apix.http.perf.instance.model.ApiPerfInstance;
import io.thoughtware.teston.test.apix.http.perf.instance.model.ApiPerfInstanceQuery;
import io.thoughtware.teston.test.apix.http.perf.instance.dao.ApiPerfInstanceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* 接口性能历史实例 服务
*/
@Service
public class ApiPerfInstanceServiceImpl implements ApiPerfInstanceService {

    @Autowired
    ApiPerfInstanceDao apiPerfInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createApiPerfInstance(@NotNull @Valid ApiPerfInstance perfInstance) {
        ApiPerfInstanceEntity perfInstanceEntity = BeanMapper.map(perfInstance, ApiPerfInstanceEntity.class);

        perfInstanceEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return apiPerfInstanceDao.createApiPerfInstance(perfInstanceEntity);
    }

    @Override
    public void updateApiPerfInstance(@NotNull @Valid ApiPerfInstance perfInstance) {
        ApiPerfInstanceEntity perfInstanceEntity = BeanMapper.map(perfInstance, ApiPerfInstanceEntity.class);

        apiPerfInstanceDao.updateApiPerfInstance(perfInstanceEntity);
    }

    @Override
    public void deleteApiPerfInstance(@NotNull String id) {
        apiPerfInstanceDao.deleteApiPerfInstance(id);
    }

    @Override
    public ApiPerfInstance findOne(String id) {
        ApiPerfInstanceEntity perfInstanceEntity = apiPerfInstanceDao.findApiPerfInstance(id);

        ApiPerfInstance perfInstance = BeanMapper.map(perfInstanceEntity, ApiPerfInstance.class);
        return perfInstance;
    }

    @Override
    public List<ApiPerfInstance> findList(List<String> idList) {
        List<ApiPerfInstanceEntity> perfInstanceEntities =  apiPerfInstanceDao.findApiPerfInstanceList(idList);

        List<ApiPerfInstance> perfInstanceList =  BeanMapper.mapList(perfInstanceEntities, ApiPerfInstance.class);
        return perfInstanceList;
    }

    @Override
    public ApiPerfInstance findApiPerfInstance(@NotNull String id) {
        ApiPerfInstance perfInstance = findOne(id);

        joinTemplate.joinQuery(perfInstance);



        return perfInstance;
    }

    @Override
    public List<ApiPerfInstance> findAllApiPerfInstance() {
        List<ApiPerfInstanceEntity> perfInstanceEntities =  apiPerfInstanceDao.findAllApiPerfInstance();

        List<ApiPerfInstance> perfInstanceList =  BeanMapper.mapList(perfInstanceEntities, ApiPerfInstance.class);

        joinTemplate.joinQuery(perfInstanceList);
        return perfInstanceList;
    }

    @Override
    public List<ApiPerfInstance> findApiPerfInstanceList(ApiPerfInstanceQuery performanceInstanceQuery) {
        List<ApiPerfInstanceEntity> perfInstanceEntities = apiPerfInstanceDao.findApiPerfInstanceList(performanceInstanceQuery);

        List<ApiPerfInstance> perfInstanceList = BeanMapper.mapList(perfInstanceEntities, ApiPerfInstance.class);

        joinTemplate.joinQuery(perfInstanceList);

        return perfInstanceList;
    }

    @Override
    public Pagination<ApiPerfInstance> findApiPerfInstancePage(ApiPerfInstanceQuery performanceInstanceQuery) {
        Pagination<ApiPerfInstanceEntity>  pagination = apiPerfInstanceDao.findApiPerfInstancePage(performanceInstanceQuery);

        List<ApiPerfInstance> perfInstanceList = BeanMapper.mapList(pagination.getDataList(), ApiPerfInstance.class);

        joinTemplate.joinQuery(perfInstanceList);

        return PaginationBuilder.build(pagination,perfInstanceList);
    }
}