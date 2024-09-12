package io.thoughtware.testhubo.repository.service;

import io.thoughtware.testhubo.repository.dao.RepositoryFollowDao;
import io.thoughtware.testhubo.repository.entity.RepositoryFollowEntity;
import io.thoughtware.testhubo.repository.model.Repository;
import io.thoughtware.testhubo.repository.model.RepositoryFollow;
import io.thoughtware.testhubo.repository.model.RepositoryFollowQuery;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.eam.common.context.LoginContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
* 仓库关注 服务
*/
@Service
public class RepositoryFollowServiceImpl implements RepositoryFollowService {

    @Autowired
    RepositoryFollowDao repositoryFollowDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    RepositoryService repositoryService;

    @Override
    public String createRepositoryFollow(@NotNull @Valid RepositoryFollow repositoryFollow) {
        String userId = LoginContext.getLoginId();
        RepositoryFollowEntity repositoryFollowEntity = BeanMapper.map(repositoryFollow, RepositoryFollowEntity.class);
        repositoryFollowEntity.setUserId(userId);
        repositoryFollowEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return repositoryFollowDao.createRepositoryFollow(repositoryFollowEntity);
    }

    @Override
    public void updateRepositoryFollow(@NotNull @Valid RepositoryFollow repositoryFollow) {
        RepositoryFollowEntity repositoryFollowEntity = BeanMapper.map(repositoryFollow, RepositoryFollowEntity.class);

        repositoryFollowDao.updateRepositoryFollow(repositoryFollowEntity);
    }

    @Override
    public void deleteRepositoryFollow(@NotNull String id) {
        RepositoryFollowQuery repositoryFollowQuery = new RepositoryFollowQuery();
        repositoryFollowQuery.setUserId(LoginContext.getLoginId());
        List<RepositoryFollowEntity> repositoryFollowEntityList = repositoryFollowDao.findRepositoryFollowList(repositoryFollowQuery);
        List<RepositoryFollow> repositoryFollowList = BeanMapper.mapList(repositoryFollowEntityList,RepositoryFollow.class);
        joinTemplate.joinQuery(repositoryFollowList);

        String followId = null;
        for(RepositoryFollow repositoryFollow: repositoryFollowList){
            if(Objects.equals(repositoryFollow.getRepositoryId(),id)){
                followId=repositoryFollow.getId();
            }
        }

        repositoryFollowDao.deleteRepositoryFollow(followId);
    }

    @Override
    public RepositoryFollow findOne(String id) {
        RepositoryFollowEntity repositoryFollowEntity = repositoryFollowDao.findRepositoryFollow(id);

        RepositoryFollow repositoryFollow = BeanMapper.map(repositoryFollowEntity, RepositoryFollow.class);
        return repositoryFollow;
    }

    @Override
    public List<RepositoryFollow> findList(List<String> idList) {
        List<RepositoryFollowEntity> repositoryFollowEntityList =  repositoryFollowDao.findRepositoryFollowList(idList);

        List<RepositoryFollow> repositoryFollowList =  BeanMapper.mapList(repositoryFollowEntityList,RepositoryFollow.class);
        return repositoryFollowList;
    }

    @Override
    public RepositoryFollow findRepositoryFollow(@NotNull String id) {
        RepositoryFollow repositoryFollow = findOne(id);

        joinTemplate.joinQuery(repositoryFollow);

        return repositoryFollow;
    }

    @Override
    public List<RepositoryFollow> findAllRepositoryFollow() {
        List<RepositoryFollowEntity> repositoryFollowEntityList =  repositoryFollowDao.findAllRepositoryFollow();

        List<RepositoryFollow> repositoryFollowList =  BeanMapper.mapList(repositoryFollowEntityList,RepositoryFollow.class);

        joinTemplate.joinQuery(repositoryFollowList);

        return repositoryFollowList;
    }

    @Override
    public List<Repository> findRepositoryFollowList(RepositoryFollowQuery repositoryFollowQuery) {
        List<RepositoryFollowEntity> repositoryFollowEntityList = repositoryFollowDao.findRepositoryFollowList(repositoryFollowQuery);
        List<RepositoryFollow> repositoryFollowList = BeanMapper.mapList(repositoryFollowEntityList,RepositoryFollow.class);
        joinTemplate.joinQuery(repositoryFollowList);

        ArrayList<Repository> arrayList = new ArrayList<>();
        for(RepositoryFollow repositoryFollow:repositoryFollowList){
            Repository repository = repositoryService.findRepository(repositoryFollow.getRepositoryId());
            repository.setIsFollow(1);

            arrayList.add(repository);
        }

        return arrayList;
    }

    @Override
    public Pagination<RepositoryFollow> findRepositoryFollowPage(RepositoryFollowQuery repositoryFollowQuery) {
        Pagination<RepositoryFollowEntity>  pagination = repositoryFollowDao.findRepositoryFollowPage(repositoryFollowQuery);

        List<RepositoryFollow> repositoryFollowList = BeanMapper.mapList(pagination.getDataList(),RepositoryFollow.class);

        joinTemplate.joinQuery(repositoryFollowList);

        return PaginationBuilder.build(pagination,repositoryFollowList);
    }
}