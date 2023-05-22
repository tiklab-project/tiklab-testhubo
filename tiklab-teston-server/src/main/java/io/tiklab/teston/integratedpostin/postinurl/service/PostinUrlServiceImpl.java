package io.tiklab.teston.integratedpostin.postinurl.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.integratedpostin.postinurl.dao.PostinUrlDao;
import io.tiklab.teston.integratedpostin.postinurl.entity.PostinUrlEntity;
import io.tiklab.teston.integratedpostin.postinurl.model.PostinUrl;
import io.tiklab.teston.integratedpostin.postinurl.model.PostinUrlQuery;
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
public class PostinUrlServiceImpl implements PostinUrlService {

    @Autowired
    PostinUrlDao postinUrlDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createPostinUrl(@NotNull @Valid PostinUrl postinUrl) {
        PostinUrlEntity postinUrlEntity = BeanMapper.map(postinUrl, PostinUrlEntity.class);

        postinUrlEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));


        return postinUrlDao.createPostinUrl(postinUrlEntity);
    }

    @Override
    public void updatePostinUrl(@NotNull @Valid PostinUrl postinUrl) {
        PostinUrlEntity postinUrlEntity = BeanMapper.map(postinUrl, PostinUrlEntity.class);

        postinUrlDao.updatePostinUrl(postinUrlEntity);
    }

    @Override
    public void deletePostinUrl(@NotNull String id) {
        postinUrlDao.deletePostinUrl(id);
    }

    @Override
    public PostinUrl findOne(String id) {
        PostinUrlEntity postinUrlEntity = postinUrlDao.findPostinUrl(id);

        PostinUrl postinUrl = BeanMapper.map(postinUrlEntity, PostinUrl.class);
        return postinUrl;
    }

    @Override
    public List<PostinUrl> findList(List<String> idList) {
        List<PostinUrlEntity> postinUrlEntityList =  postinUrlDao.findPostinUrlList(idList);

        List<PostinUrl> postinUrlList =  BeanMapper.mapList(postinUrlEntityList,PostinUrl.class);
        return postinUrlList;
    }

    @Override
    public PostinUrl findPostinUrl(@NotNull String id) {
        PostinUrl postinUrl = findOne(id);

        joinTemplate.joinQuery(postinUrl);

        return postinUrl;
    }

    @Override
    public List<PostinUrl> findAllPostinUrl() {
        List<PostinUrlEntity> postinUrlEntityList =  postinUrlDao.findAllPostinUrl();

        List<PostinUrl> postinUrlList =  BeanMapper.mapList(postinUrlEntityList,PostinUrl.class);

        joinTemplate.joinQuery(postinUrlList);

        return postinUrlList;
    }

    @Override
    public List<PostinUrl> findPostinUrlList(PostinUrlQuery postinUrlQuery) {
        List<PostinUrlEntity> postinUrlEntityList = postinUrlDao.findPostinUrlList(postinUrlQuery);

        List<PostinUrl> postinUrlList = BeanMapper.mapList(postinUrlEntityList,PostinUrl.class);

        joinTemplate.joinQuery(postinUrlList);

        return postinUrlList;
    }

    @Override
    public Pagination<PostinUrl> findPostinUrlPage(PostinUrlQuery postinUrlQuery) {
        Pagination<PostinUrlEntity>  pagination = postinUrlDao.findPostinUrlPage(postinUrlQuery);

        List<PostinUrl> postinUrlList = BeanMapper.mapList(pagination.getDataList(),PostinUrl.class);

        joinTemplate.joinQuery(postinUrlList);

        return PaginationBuilder.build(pagination,postinUrlList);
    }
}