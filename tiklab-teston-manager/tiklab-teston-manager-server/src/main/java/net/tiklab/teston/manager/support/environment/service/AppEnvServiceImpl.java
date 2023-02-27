package net.tiklab.teston.manager.support.environment.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.teston.manager.support.environment.dao.AppEnvDao;
import net.tiklab.teston.manager.support.environment.entity.AppEnvEntity;
import net.tiklab.teston.manager.support.environment.model.AppEnv;
import net.tiklab.teston.manager.support.environment.model.AppEnvQuery;

import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
* AppEnvServiceImpl
*/
@Service
public class AppEnvServiceImpl implements AppEnvService {

    @Autowired
    AppEnvDao appEnvDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAppEnv(@NotNull @Valid AppEnv appEnv) {
        AppEnvEntity appEnvEntity = BeanMapper.map(appEnv, AppEnvEntity.class);

        return appEnvDao.createAppEnv(appEnvEntity);
    }

    @Override
    public void updateAppEnv(@NotNull @Valid AppEnv appEnv) {
        AppEnvEntity appEnvEntity = BeanMapper.map(appEnv, AppEnvEntity.class);

        appEnvDao.updateAppEnv(appEnvEntity);
    }

    @Override
    public void deleteAppEnv(@NotNull String id) {
        appEnvDao.deleteAppEnv(id);
    }

    @Override
    public AppEnv findOne(String id) {
        AppEnvEntity appEnvEntity = appEnvDao.findAppEnv(id);

        AppEnv appEnv = BeanMapper.map(appEnvEntity, AppEnv.class);
        return appEnv;
    }

    @Override
    public List<AppEnv> findList(List<String> idList) {
        List<AppEnvEntity> appEnvEntityList =  appEnvDao.findAppEnvList(idList);

        List<AppEnv> appEnvList =  BeanMapper.mapList(appEnvEntityList,AppEnv.class);
        return appEnvList;
    }

    @Override
    public AppEnv findAppEnv(@NotNull String id) {
        AppEnv appEnv = findOne(id);

        joinTemplate.joinQuery(appEnv);

        return appEnv;
    }

    @Override
    public List<AppEnv> findAllAppEnv() {
        List<AppEnvEntity> appEnvEntityList =  appEnvDao.findAllAppEnv();

        List<AppEnv> appEnvList =  BeanMapper.mapList(appEnvEntityList,AppEnv.class);

        joinTemplate.joinQuery(appEnvList);

        return appEnvList;
    }

    @Override
    public List<AppEnv> findAppEnvList(AppEnvQuery appEnvQuery) {
        List<AppEnvEntity> appEnvEntityList = appEnvDao.findAppEnvList(appEnvQuery);

        List<AppEnv> appEnvList = BeanMapper.mapList(appEnvEntityList,AppEnv.class);

        joinTemplate.joinQuery(appEnvList);

        return appEnvList;
    }

    @Override
    public Pagination<AppEnv> findAppEnvPage(AppEnvQuery appEnvQuery) {
        Pagination<AppEnvEntity>  pagination = appEnvDao.findAppEnvPage(appEnvQuery);

        List<AppEnv> appEnvList = BeanMapper.mapList(pagination.getDataList(),AppEnv.class);

        joinTemplate.joinQuery(appEnvList);

        return PaginationBuilder.build(pagination,appEnvList);
    }
}