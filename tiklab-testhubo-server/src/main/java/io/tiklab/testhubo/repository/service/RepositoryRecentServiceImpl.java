package io.tiklab.testhubo.repository.service;

import io.tiklab.testhubo.repository.model.Repository;
import io.tiklab.testhubo.repository.model.RepositoryRecent;
import io.tiklab.testhubo.repository.model.RepositoryRecentQuery;
import io.tiklab.testhubo.repository.dao.RepositoryRecentDao;
import io.tiklab.testhubo.repository.entity.RepositoryRecentEntity;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;

import io.tiklab.toolkit.beans.BeanMapper;
import io.tiklab.toolkit.join.JoinTemplate;
import io.tiklab.testhubo.testplan.cases.service.TestPlanService;
import io.tiklab.user.dmUser.model.DmUser;
import io.tiklab.user.dmUser.model.DmUserQuery;
import io.tiklab.user.dmUser.service.DmUserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
* 最近访问仓库 服务
*/
@Service
public class RepositoryRecentServiceImpl implements RepositoryRecentService {

    @Autowired
    RepositoryRecentDao repositoryRecentDao;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    TestPlanService testPlanService;

    @Autowired
    DmUserService dmUserService;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRepositoryRecent(@NotNull @Valid RepositoryRecent repositoryRecent) {
        RepositoryRecentEntity repositoryRecentEntity = BeanMapper.map(repositoryRecent, RepositoryRecentEntity.class);

        return repositoryRecentDao.createRepositoryRecent(repositoryRecentEntity);
    }

    @Override
    public void updateRepositoryRecent(@NotNull @Valid RepositoryRecent repositoryRecent) {
        RepositoryRecentEntity repositoryRecentEntity = BeanMapper.map(repositoryRecent, RepositoryRecentEntity.class);

        repositoryRecentDao.updateRepositoryRecent(repositoryRecentEntity);
    }

    @Override
    public void deleteRepositoryRecent(@NotNull String id) {
        repositoryRecentDao.deleteRepositoryRecent(id);
    }

    @Override
    public RepositoryRecent findOne(String id) {
        RepositoryRecentEntity repositoryRecentEntity = repositoryRecentDao.findRepositoryRecent(id);

        RepositoryRecent repositoryRecent = BeanMapper.map(repositoryRecentEntity, RepositoryRecent.class);
        return repositoryRecent;
    }

    @Override
    public List<RepositoryRecent> findList(List<String> idList) {
        List<RepositoryRecentEntity> repositoryRecentEntityList =  repositoryRecentDao.findRepositoryRecentList(idList);

        List<RepositoryRecent> repositoryRecentList =  BeanMapper.mapList(repositoryRecentEntityList,RepositoryRecent.class);
        return repositoryRecentList;
    }

    @Override
    public RepositoryRecent findRepositoryRecent(@NotNull String id) {
        RepositoryRecent repositoryRecent = findOne(id);

        joinTemplate.joinQuery(repositoryRecent);

        return repositoryRecent;
    }

    @Override
    public List<RepositoryRecent> findAllRepositoryRecent() {
        List<RepositoryRecentEntity> repositoryRecentEntityList =  repositoryRecentDao.findAllRepositoryRecent();

        List<RepositoryRecent> repositoryRecentList =  BeanMapper.mapList(repositoryRecentEntityList,RepositoryRecent.class);

        joinTemplate.joinQuery(repositoryRecentList);

        return repositoryRecentList;
    }

    @Override
    public List<Repository> findRepositoryRecentList(RepositoryRecentQuery repositoryRecentQuery) {
        List<RepositoryRecentEntity> repositoryRecentEntityList = repositoryRecentDao.findRepositoryRecentList(repositoryRecentQuery);
        List<RepositoryRecent> repositoryRecentList = BeanMapper.mapList(repositoryRecentEntityList,RepositoryRecent.class);

        ArrayList<Repository> repList = new ArrayList<>();
        for(RepositoryRecent repositoryRecent:repositoryRecentList){
            String repositoryId = repositoryRecent.getRepository().getId();
            Repository repository = repositoryService.findRepository(repositoryId);

            //获取测试计划总数
            int testPlanNum = testPlanService.findTestPlanNum(repositoryId);
            repository.setPlanNum(testPlanNum);

            //获取成员总数
            int memberNum =0;
            DmUserQuery dmUserQuery = new DmUserQuery();
            dmUserQuery.setDomainId(repositoryId);
            List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);
            memberNum=dmUserList.size();
            repository.setMemberNum(memberNum);

            repList.add(repository);
        }

        joinTemplate.joinQuery(repList);

        return repList;
    }

    @Override
    public List<RepositoryRecent> findRecentList(RepositoryRecentQuery repositoryRecentQuery) {
        List<RepositoryRecentEntity> repositoryRecentEntityList = repositoryRecentDao.findRepositoryRecentList(repositoryRecentQuery);
        List<RepositoryRecent> repositoryRecentList = BeanMapper.mapList(repositoryRecentEntityList,RepositoryRecent.class);

        joinTemplate.joinQuery(repositoryRecentList);

        return repositoryRecentList;
    }

    @Override
    public Pagination<RepositoryRecent> findRepositoryRecentPage(RepositoryRecentQuery repositoryRecentQuery) {
        Pagination<RepositoryRecentEntity>  pagination = repositoryRecentDao.findRepositoryRecentPage(repositoryRecentQuery);

        List<RepositoryRecent> repositoryRecentList = BeanMapper.mapList(pagination.getDataList(),RepositoryRecent.class);

        joinTemplate.joinQuery(repositoryRecentList);

        return PaginationBuilder.build(pagination,repositoryRecentList);
    }

    @Override
    public void repositoryRecent(RepositoryRecent repositoryRecent) {
        RepositoryRecentQuery repositoryRecentQuery = new RepositoryRecentQuery();
        repositoryRecentQuery.setRepositoryId(repositoryRecent.getRepository().getId());
        repositoryRecentQuery.setUserId(repositoryRecent.getUserId());

        List<RepositoryRecentEntity> repositoryRecentEntityList = repositoryRecentDao.findRepositoryRecentList(repositoryRecentQuery);
        List<RepositoryRecent> repositoryRecentList = BeanMapper.mapList(repositoryRecentEntityList, RepositoryRecent.class);

        if(CollectionUtils.isNotEmpty(repositoryRecentList)&&repositoryRecentList.size()>0){
            RepositoryRecent repositoryRecent1 = repositoryRecentList.get(0);

            repositoryRecent.setId(repositoryRecent1.getId());
            repositoryRecent.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            updateRepositoryRecent(repositoryRecent);
        }else {
            repositoryRecent.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            createRepositoryRecent(repositoryRecent);
        }

    }


}