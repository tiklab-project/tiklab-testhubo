package io.thoughtware.teston.support.environment.service;

import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.teston.support.environment.dao.ApiEnvDao;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.teston.support.environment.entity.ApiEnvEntity;
import io.thoughtware.teston.support.environment.model.ApiEnv;
import io.thoughtware.teston.support.environment.model.ApiEnvQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口环境 服务
*/
@Service
@Exporter
public class ApiEnvServiceImpl implements ApiEnvService {

    @Autowired
    ApiEnvDao apiEnvDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createApiEnv(@NotNull @Valid ApiEnv apiEnv) {
        ApiEnvEntity apiEnvEntity = BeanMapper.map(apiEnv, ApiEnvEntity.class);

        return apiEnvDao.createApiEnv(apiEnvEntity);
    }

    @Override
    public void updateApiEnv(@NotNull @Valid ApiEnv apiEnv) {
        ApiEnvEntity apiEnvEntity = BeanMapper.map(apiEnv, ApiEnvEntity.class);

        apiEnvDao.updateApiEnv(apiEnvEntity);
    }

    @Override
    public void deleteApiEnv(@NotNull String id) {
        apiEnvDao.deleteApiEnv(id);
    }

    @Override
    public ApiEnv findOne(String id) {
        ApiEnvEntity apiEnvEntity = apiEnvDao.findApiEnv(id);

        ApiEnv apiEnv = BeanMapper.map(apiEnvEntity, ApiEnv.class);
        return apiEnv;
    }

    @Override
    public List<ApiEnv> findList(List<String> idList) {
        List<ApiEnvEntity> apiEnvEntityList =  apiEnvDao.findApiEnvList(idList);

        List<ApiEnv> apiEnvList =  BeanMapper.mapList(apiEnvEntityList, ApiEnv.class);
        return apiEnvList;
    }

    @Override
    public ApiEnv findApiEnv(@NotNull String id) {
        ApiEnv apiEnv = findOne(id);

        joinTemplate.joinQuery(apiEnv);
        return apiEnv;
    }

    @Override
    public List<ApiEnv> findAllApiEnv() {
        List<ApiEnvEntity> apiEnvEntityList =  apiEnvDao.findAllApiEnv();

        List<ApiEnv> apiEnvList =  BeanMapper.mapList(apiEnvEntityList, ApiEnv.class);

        joinTemplate.joinQuery(apiEnvList);
        return apiEnvList;
    }

    @Override
    public List<ApiEnv> findApiEnvList(ApiEnvQuery apiEnvQuery) {

        List<ApiEnvEntity> apiEnvEntityList = apiEnvDao.findApiEnvList(apiEnvQuery);

        List<ApiEnv> apiEnvList = BeanMapper.mapList(apiEnvEntityList, ApiEnv.class);

        joinTemplate.joinQuery(apiEnvList);

        return apiEnvList;
    }

    @Override
    public Pagination<ApiEnv> findApiEnvPage(ApiEnvQuery apiEnvQuery) {

        Pagination<ApiEnvEntity>  pagination = apiEnvDao.findApiEnvPage(apiEnvQuery);

        List<ApiEnv> apiEnvList = BeanMapper.mapList(pagination.getDataList(), ApiEnv.class);

        joinTemplate.joinQuery(apiEnvList);

        return PaginationBuilder.build(pagination, apiEnvList);
    }


}