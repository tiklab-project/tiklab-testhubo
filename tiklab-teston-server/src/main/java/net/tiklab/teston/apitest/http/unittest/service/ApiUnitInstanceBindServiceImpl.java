package net.tiklab.teston.apitest.http.unittest.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.apitest.http.unittest.dao.ApiUnitInstanceBindDao;
import net.tiklab.teston.apitest.http.unittest.entity.ApiUnitInstanceBindEntity;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitInstanceBind;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitInstanceBindQuery;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
* ApiUnitInstanceBindServiceImpl
*/
@Service
public class ApiUnitInstanceBindServiceImpl implements ApiUnitInstanceBindService {

    @Autowired
    ApiUnitInstanceBindDao apiUnitInstanceBindDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createApiUnitInstanceBind(@NotNull @Valid ApiUnitInstanceBind apiUnitInstanceBind) {
        ApiUnitInstanceBindEntity apiUnitInstanceBindEntity = BeanMapper.map(apiUnitInstanceBind, ApiUnitInstanceBindEntity.class);
        apiUnitInstanceBindEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return apiUnitInstanceBindDao.createApiUnitInstanceBind(apiUnitInstanceBindEntity);
    }

    @Override
    public void updateApiUnitInstanceBind(@NotNull @Valid ApiUnitInstanceBind apiUnitInstanceBind) {
        ApiUnitInstanceBindEntity apiUnitInstanceBindEntity = BeanMapper.map(apiUnitInstanceBind, ApiUnitInstanceBindEntity.class);

        apiUnitInstanceBindDao.updateApiUnitInstanceBind(apiUnitInstanceBindEntity);
    }

    @Override
    public void deleteApiUnitInstanceBind(@NotNull String id) {
        apiUnitInstanceBindDao.deleteApiUnitInstanceBind(id);
    }

    @Override
    public ApiUnitInstanceBind findOne(String id) {
        ApiUnitInstanceBindEntity apiUnitInstanceBindEntity = apiUnitInstanceBindDao.findApiUnitInstanceBind(id);

        ApiUnitInstanceBind apiUnitInstanceBind = BeanMapper.map(apiUnitInstanceBindEntity, ApiUnitInstanceBind.class);
        return apiUnitInstanceBind;
    }

    @Override
    public List<ApiUnitInstanceBind> findList(List<String> idList) {
        List<ApiUnitInstanceBindEntity> apiUnitInstanceBindEntityList =  apiUnitInstanceBindDao.findApiUnitInstanceBindList(idList);

        List<ApiUnitInstanceBind> apiUnitInstanceBindList =  BeanMapper.mapList(apiUnitInstanceBindEntityList,ApiUnitInstanceBind.class);
        return apiUnitInstanceBindList;
    }

    @Override
    public ApiUnitInstanceBind findApiUnitInstanceBind(@NotNull String id) {
        ApiUnitInstanceBind apiUnitInstanceBind = findOne(id);

        joinTemplate.joinQuery(apiUnitInstanceBind);

        return apiUnitInstanceBind;
    }

    @Override
    public List<ApiUnitInstanceBind> findAllApiUnitInstanceBind() {
        List<ApiUnitInstanceBindEntity> apiUnitInstanceBindEntityList =  apiUnitInstanceBindDao.findAllApiUnitInstanceBind();

        List<ApiUnitInstanceBind> apiUnitInstanceBindList =  BeanMapper.mapList(apiUnitInstanceBindEntityList,ApiUnitInstanceBind.class);

        joinTemplate.joinQuery(apiUnitInstanceBindList);

        return apiUnitInstanceBindList;
    }

    @Override
    public List<ApiUnitInstanceBind> findApiUnitInstanceBindList(ApiUnitInstanceBindQuery apiUnitInstanceBindQuery) {
        List<ApiUnitInstanceBindEntity> apiUnitInstanceBindEntityList = apiUnitInstanceBindDao.findApiUnitInstanceBindList(apiUnitInstanceBindQuery);

        List<ApiUnitInstanceBind> apiUnitInstanceBindList = BeanMapper.mapList(apiUnitInstanceBindEntityList,ApiUnitInstanceBind.class);

        joinTemplate.joinQuery(apiUnitInstanceBindList);

        return apiUnitInstanceBindList;
    }

    @Override
    public Pagination<ApiUnitInstanceBind> findApiUnitInstanceBindPage(ApiUnitInstanceBindQuery apiUnitInstanceBindQuery) {
        Pagination<ApiUnitInstanceBindEntity>  pagination = apiUnitInstanceBindDao.findApiUnitInstanceBindPage(apiUnitInstanceBindQuery);

        List<ApiUnitInstanceBind> apiUnitInstanceBindList = BeanMapper.mapList(pagination.getDataList(),ApiUnitInstanceBind.class);

        joinTemplate.joinQuery(apiUnitInstanceBindList);

        return PaginationBuilder.build(pagination,apiUnitInstanceBindList);
    }
}