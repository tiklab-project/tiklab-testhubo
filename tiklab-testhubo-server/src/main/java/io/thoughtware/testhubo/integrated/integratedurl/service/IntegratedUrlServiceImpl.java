package io.thoughtware.testhubo.integrated.integratedurl.service;

import io.thoughtware.testhubo.integrated.integratedurl.model.IntegratedUrl;
import io.thoughtware.testhubo.integrated.integratedurl.model.IntegratedUrlQuery;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.testhubo.integrated.integratedurl.dao.IntegratedUrlDao;
import io.thoughtware.testhubo.integrated.integratedurl.entity.IntegratedUrlEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;


/**
* agent配置 服务
*/
@Service
public class IntegratedUrlServiceImpl implements IntegratedUrlService {

    @Autowired
    IntegratedUrlDao postinUrlDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createIntegratedUrl(@NotNull @Valid IntegratedUrl integratedUrl) {
        IntegratedUrlEntity integratedUrlEntity = BeanMapper.map(integratedUrl, IntegratedUrlEntity.class);

        integratedUrlEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));


        return postinUrlDao.createIntegratedUrl(integratedUrlEntity);
    }

    @Override
    public void updateIntegratedUrl(@NotNull @Valid IntegratedUrl integratedUrl) {
        IntegratedUrlEntity integratedUrlEntity = BeanMapper.map(integratedUrl, IntegratedUrlEntity.class);

        postinUrlDao.updateIntegratedUrl(integratedUrlEntity);
    }

    @Override
    public void deleteIntegratedUrl(@NotNull String id) {
        postinUrlDao.deleteIntegratedUrl(id);
    }

    @Override
    public IntegratedUrl findOne(String id) {
        IntegratedUrlEntity integratedUrlEntity = postinUrlDao.findIntegratedUrl(id);

        IntegratedUrl integratedUrl = BeanMapper.map(integratedUrlEntity, IntegratedUrl.class);
        return integratedUrl;
    }

    @Override
    public List<IntegratedUrl> findList(List<String> idList) {
        List<IntegratedUrlEntity> integratedUrlEntityList =  postinUrlDao.findIntegratedUrlList(idList);

        List<IntegratedUrl> integratedUrlList =  BeanMapper.mapList(integratedUrlEntityList, IntegratedUrl.class);
        return integratedUrlList;
    }

    @Override
    public IntegratedUrl findIntegratedUrl(@NotNull String id) {
        IntegratedUrl integratedUrl = findOne(id);

        joinTemplate.joinQuery(integratedUrl);

        return integratedUrl;
    }

    @Override
    public List<IntegratedUrl> findAllIntegratedUrl() {
        List<IntegratedUrlEntity> integratedUrlEntityList =  postinUrlDao.findAllIntegratedUrl();

        List<IntegratedUrl> integratedUrlList =  BeanMapper.mapList(integratedUrlEntityList, IntegratedUrl.class);

        joinTemplate.joinQuery(integratedUrlList);

        return integratedUrlList;
    }

    @Override
    public List<IntegratedUrl> findIntegratedUrlList(IntegratedUrlQuery integratedUrlQuery) {
        List<IntegratedUrlEntity> integratedUrlEntityList = postinUrlDao.findIntegratedUrlList(integratedUrlQuery);

        List<IntegratedUrl> integratedUrlList = BeanMapper.mapList(integratedUrlEntityList, IntegratedUrl.class);

        joinTemplate.joinQuery(integratedUrlList);

        return integratedUrlList;
    }

    @Override
    public Pagination<IntegratedUrl> findIntegratedUrlPage(IntegratedUrlQuery integratedUrlQuery) {
        Pagination<IntegratedUrlEntity>  pagination = postinUrlDao.findIntegratedUrlPage(integratedUrlQuery);

        List<IntegratedUrl> integratedUrlList = BeanMapper.mapList(pagination.getDataList(), IntegratedUrl.class);

        joinTemplate.joinQuery(integratedUrlList);

        return PaginationBuilder.build(pagination, integratedUrlList);
    }

    @Override
    public String getProductUrl(IntegratedUrlQuery integratedUrlQuery) {
        List<IntegratedUrl> integratedUrlList = findIntegratedUrlList(integratedUrlQuery);
        return integratedUrlList.get(0).getUrl();
    }


}