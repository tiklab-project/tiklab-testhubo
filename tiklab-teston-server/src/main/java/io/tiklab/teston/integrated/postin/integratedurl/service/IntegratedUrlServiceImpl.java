package io.tiklab.teston.integrated.postin.integratedurl.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.integrated.postin.integratedurl.dao.PostinUrlDao;
import io.tiklab.teston.integrated.postin.integratedurl.entity.IntegratedUrlEntity;
import io.tiklab.teston.integrated.postin.integratedurl.model.IntegratedUrl;
import io.tiklab.teston.integrated.postin.integratedurl.model.IntegratedUrlQuery;
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
public class IntegratedUrlServiceImpl implements PostinUrlService {

    @Autowired
    PostinUrlDao postinUrlDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createPostinUrl(@NotNull @Valid IntegratedUrl integratedUrl) {
        IntegratedUrlEntity integratedUrlEntity = BeanMapper.map(integratedUrl, IntegratedUrlEntity.class);

        integratedUrlEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));


        return postinUrlDao.createPostinUrl(integratedUrlEntity);
    }

    @Override
    public void updatePostinUrl(@NotNull @Valid IntegratedUrl integratedUrl) {
        IntegratedUrlEntity integratedUrlEntity = BeanMapper.map(integratedUrl, IntegratedUrlEntity.class);

        postinUrlDao.updatePostinUrl(integratedUrlEntity);
    }

    @Override
    public void deletePostinUrl(@NotNull String id) {
        postinUrlDao.deletePostinUrl(id);
    }

    @Override
    public IntegratedUrl findOne(String id) {
        IntegratedUrlEntity integratedUrlEntity = postinUrlDao.findPostinUrl(id);

        IntegratedUrl integratedUrl = BeanMapper.map(integratedUrlEntity, IntegratedUrl.class);
        return integratedUrl;
    }

    @Override
    public List<IntegratedUrl> findList(List<String> idList) {
        List<IntegratedUrlEntity> integratedUrlEntityList =  postinUrlDao.findPostinUrlList(idList);

        List<IntegratedUrl> integratedUrlList =  BeanMapper.mapList(integratedUrlEntityList, IntegratedUrl.class);
        return integratedUrlList;
    }

    @Override
    public IntegratedUrl findPostinUrl(@NotNull String id) {
        IntegratedUrl integratedUrl = findOne(id);

        joinTemplate.joinQuery(integratedUrl);

        return integratedUrl;
    }

    @Override
    public List<IntegratedUrl> findAllPostinUrl() {
        List<IntegratedUrlEntity> integratedUrlEntityList =  postinUrlDao.findAllPostinUrl();

        List<IntegratedUrl> integratedUrlList =  BeanMapper.mapList(integratedUrlEntityList, IntegratedUrl.class);

        joinTemplate.joinQuery(integratedUrlList);

        return integratedUrlList;
    }

    @Override
    public List<IntegratedUrl> findPostinUrlList(IntegratedUrlQuery integratedUrlQuery) {
        List<IntegratedUrlEntity> integratedUrlEntityList = postinUrlDao.findPostinUrlList(integratedUrlQuery);

        List<IntegratedUrl> integratedUrlList = BeanMapper.mapList(integratedUrlEntityList, IntegratedUrl.class);

        joinTemplate.joinQuery(integratedUrlList);

        return integratedUrlList;
    }

    @Override
    public Pagination<IntegratedUrl> findPostinUrlPage(IntegratedUrlQuery integratedUrlQuery) {
        Pagination<IntegratedUrlEntity>  pagination = postinUrlDao.findPostinUrlPage(integratedUrlQuery);

        List<IntegratedUrl> integratedUrlList = BeanMapper.mapList(pagination.getDataList(), IntegratedUrl.class);

        joinTemplate.joinQuery(integratedUrlList);

        return PaginationBuilder.build(pagination, integratedUrlList);
    }
}