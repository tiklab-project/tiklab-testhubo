package net.tiklab.teston.apptest.perftest.service;

import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.apptest.perftest.dao.AppPerfInstanceDao;
import net.tiklab.teston.apptest.perftest.entity.AppPerfInstanceEntity;
import net.tiklab.teston.apptest.perftest.model.AppPerfInstance;
import net.tiklab.teston.apptest.perftest.model.AppPerfInstanceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* AppPerfInstanceServiceImpl
*/
@Service
public class AppPerfInstanceServiceImpl implements AppPerfInstanceService {

    @Autowired
    AppPerfInstanceDao appPerfInstanceDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createAppPerfInstance(@NotNull @Valid AppPerfInstance appPerfInstance) {
        AppPerfInstanceEntity appPerfInstanceEntity = BeanMapper.map(appPerfInstance, AppPerfInstanceEntity.class);

        appPerfInstanceEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return appPerfInstanceDao.createAppPerfInstance(appPerfInstanceEntity);
    }

    @Override
    public void updateAppPerfInstance(@NotNull @Valid AppPerfInstance appPerfInstance) {
        AppPerfInstanceEntity appPerfInstanceEntity = BeanMapper.map(appPerfInstance, AppPerfInstanceEntity.class);

        appPerfInstanceDao.updateAppPerfInstance(appPerfInstanceEntity);
    }

    @Override
    public void deleteAppPerfInstance(@NotNull String id) {
        appPerfInstanceDao.deleteAppPerfInstance(id);
    }

    @Override
    public AppPerfInstance findOne(String id) {
        AppPerfInstanceEntity appPerfInstanceEntity = appPerfInstanceDao.findAppPerfInstance(id);

        AppPerfInstance appPerfInstance = BeanMapper.map(appPerfInstanceEntity, AppPerfInstance.class);
        return appPerfInstance;
    }

    @Override
    public List<AppPerfInstance> findList(List<String> idList) {
        List<AppPerfInstanceEntity> appPerfInstanceEntities =  appPerfInstanceDao.findAppPerfInstanceList(idList);

        List<AppPerfInstance> appPerfInstanceList =  BeanMapper.mapList(appPerfInstanceEntities, AppPerfInstance.class);
        return appPerfInstanceList;
    }

    @Override
    public AppPerfInstance findAppPerfInstance(@NotNull String id) {
        AppPerfInstance appPerfInstance = findOne(id);

        joinTemplate.joinQuery(appPerfInstance);
        return appPerfInstance;
    }

    @Override
    public List<AppPerfInstance> findAllAppPerfInstance() {
        List<AppPerfInstanceEntity> appPerfInstanceEntities =  appPerfInstanceDao.findAllAppPerfInstance();

        List<AppPerfInstance> appPerfInstanceList =  BeanMapper.mapList(appPerfInstanceEntities, AppPerfInstance.class);

        joinTemplate.joinQuery(appPerfInstanceList);
        return appPerfInstanceList;
    }

    @Override
    public List<AppPerfInstance> findAppPerfInstanceList(AppPerfInstanceQuery appPerfInstanceQuery) {
        List<AppPerfInstanceEntity> appPerfInstanceEntities = appPerfInstanceDao.findAppPerfInstanceList(appPerfInstanceQuery);

        List<AppPerfInstance> appPerfInstanceList = BeanMapper.mapList(appPerfInstanceEntities, AppPerfInstance.class);

        joinTemplate.joinQuery(appPerfInstanceList);

        return appPerfInstanceList;
    }

    @Override
    public Pagination<AppPerfInstance> findAppPerfInstancePage(AppPerfInstanceQuery appPerfInstanceQuery) {
        Pagination<AppPerfInstanceEntity>  pagination = appPerfInstanceDao.findAppPerfInstancePage(appPerfInstanceQuery);

        List<AppPerfInstance> appPerfInstanceList = BeanMapper.mapList(pagination.getDataList(), AppPerfInstance.class);

        joinTemplate.joinQuery(appPerfInstanceList);

        return PaginationBuilder.build(pagination,appPerfInstanceList);
    }
}