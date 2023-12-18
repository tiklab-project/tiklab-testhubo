package io.thoughtware.teston.repository.service;

import io.thoughtware.privilege.role.model.PatchUser;
import io.thoughtware.teston.common.TestOnUnit;
import io.thoughtware.teston.repository.model.*;
import io.thoughtware.rpc.annotation.Exporter;
import io.thoughtware.teston.category.model.Category;
import io.thoughtware.teston.common.MessageTemplateConstant;
import io.thoughtware.teston.repository.dao.RepositoryDao;
import io.thoughtware.teston.repository.entity.RepositoryEntity;
import io.thoughtware.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.join.JoinTemplate;
import io.thoughtware.privilege.dmRole.service.DmRoleService;
import io.thoughtware.teston.category.model.CategoryQuery;
import io.thoughtware.teston.category.service.CategoryService;
import io.thoughtware.teston.support.environment.model.ApiEnv;
import io.thoughtware.teston.support.environment.service.ApiEnvService;
import io.thoughtware.user.dmUser.model.DmUser;
import io.thoughtware.user.dmUser.model.DmUserQuery;
import io.thoughtware.user.dmUser.service.DmUserService;
import io.thoughtware.eam.common.context.LoginContext;
import io.thoughtware.user.user.model.User;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.Collectors;

/**
* 仓库 服务
*/
@Service
@Exporter
public class RepositoryServiceImpl implements RepositoryService {

    @Autowired
    RepositoryDao repositoryDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    CategoryService categoryService;

    @Autowired
    DmUserService dmUserService;
    
    @Autowired
    RepositoryFollowService repositoryFollowService;

    @Autowired
    RepositoryRecentService repositoryRecentService;

    @Autowired
    DmRoleService dmRoleService;

    @Autowired
    TestOnUnit testOnUnit;


    @Autowired
    ApiEnvService apiEnvService;

    @Value("${base.url:null}")
    String baseUrl;

    @Override
    public String createRepository(@NotNull @Valid Repository repository) {
        String userId = LoginContext.getLoginId();

        //创建项目
        RepositoryEntity repositoryEntity = BeanMapper.map(repository, RepositoryEntity.class);
        repositoryEntity.setUserId(userId);
        String repositoryId = repositoryDao.createRepository(repositoryEntity);

        //初始化分组（默认分组）
        Category category = new Category();
        Repository rep = new Repository();
        rep.setId(repositoryId);
        category.setRepository(rep);
        category.setName("默认分组");
        categoryService.createCategory(category);

        //初始化一个mock
        ApiEnv apiEnv = new ApiEnv();
        apiEnv.setRepositoryId(repositoryId);
        apiEnv.setName("Mock");
        String mockUrl = baseUrl+"/mockx/"+repositoryId;
        apiEnv.setPreUrl(mockUrl);
        apiEnvService.createApiEnv(apiEnv);

        //初始化项目权限
        initProjectDmRole(repository.getUserList(),repositoryId);

        Map<String,String> map = new HashMap<>();
        map.put("name",repository.getName());
        map.put("repositoryId",repositoryId);
        map.put("link","/repository/detail/${repositoryId}");
        //日志
        testOnUnit.log(MessageTemplateConstant.LOG_TYPE_CREATE_ID,"repository",map);
        //消息
        testOnUnit.message(map);

        return repositoryId;

    }

    @Override
    public void updateRepository(@NotNull @Valid Repository repository) {
        RepositoryEntity repositoryEntity = BeanMapper.map(repository, RepositoryEntity.class);

        repositoryDao.updateRepository(repositoryEntity);

        //日志
        Map<String,String> map = new HashMap<>();
        map.put("name",repository.getName());
        map.put("repositoryId",repository.getId());
        map.put("link","/repository/detail/${repositoryId}");
        testOnUnit.log(MessageTemplateConstant.LOG_TYPE_UPDATE_ID,"repository",map);
    }

    @Override
    public void deleteRepository(@NotNull String id) {
        repositoryDao.deleteRepository(id);
        List<Category> categoryList = categoryService.findCategoryList(new CategoryQuery().setRepositoryId(id));
        if(CollectionUtils.isNotEmpty(categoryList)){
            for(Category category : categoryList){
                categoryService.deleteCategory(category.getId());
            }
        }
        String loginId = LoginContext.getLoginId();
        //删除关注的
        RepositoryFollowQuery repositoryFollowQuery = new RepositoryFollowQuery();
        repositoryFollowQuery.setUserId(loginId);
        List<Repository> repositoryFollowList = repositoryFollowService.findRepositoryFollowList(repositoryFollowQuery);
        if(CollectionUtils.isNotEmpty(repositoryFollowList)){
            for(Repository repositoryFollow: repositoryFollowList){
                if(Objects.equals(repositoryFollow.getId(), id)){
                    repositoryFollowService.deleteRepositoryFollow(repositoryFollow.getId());
                }
            }
        }

        //删除最近
        RepositoryRecentQuery repositoryRecentQuery = new RepositoryRecentQuery();
        repositoryRecentQuery.setUserId(loginId);
        List<RepositoryRecent> recentList = repositoryRecentService.findRecentList(repositoryRecentQuery);
        if(CollectionUtils.isNotEmpty(recentList)){
            for(RepositoryRecent repositoryRecent : recentList){
                if(Objects.equals(repositoryRecent.getRepository().getId(),id)){
                    repositoryRecentService.deleteRepositoryRecent(repositoryRecent.getId());
                }
            }
        }

        //删除角色以及相关的关联表
        dmRoleService.deleteDmRoleByDomainId(id);

    }

    @Override
    public Repository findOne(String id) {
        RepositoryEntity repositoryEntity = repositoryDao.findRepository(id);

        Repository repository = BeanMapper.map(repositoryEntity, Repository.class);
        return repository;
    }

    @Override
    public List<Repository> findList(List<String> idList) {
        List<RepositoryEntity> repositoryEntityList =  repositoryDao.findRepositoryList(idList);

        List<Repository> repositoryList =  BeanMapper.mapList(repositoryEntityList,Repository.class);
        return repositoryList;
    }

    @Override
    public Repository findRepository(@NotNull String id) {
        Repository repository = findOne(id);

        joinTemplate.joinQuery(repository);
        return repository;
    }

    @Override
    public List<Repository> findAllRepository() {
        List<RepositoryEntity> repositoryEntityList =  repositoryDao.findAllRepository();

        List<Repository> repositoryList =  BeanMapper.mapList(repositoryEntityList,Repository.class);

        joinTemplate.joinQuery(repositoryList);
        return repositoryList;
    }

    @Override
    public List<Repository> findRepositoryList(RepositoryQuery repositoryQuery) {
        List<RepositoryEntity> repositoryEntityList = repositoryDao.findRepositoryList(repositoryQuery);
        List<Repository> repositoryList = BeanMapper.mapList(repositoryEntityList,Repository.class);

        //关注
        // 获取所有关注的 Repository 的 ID 列表
        List<String> followRepositoryIds = getFollowRepositoryIds();
        // 设置是否关注
        for(Repository repository: repositoryList){
            if(followRepositoryIds.contains(repository.getId())){
                repository.setIsFollow(1);
            } else {
                repository.setIsFollow(0);
            }
        }


        joinTemplate.joinQuery(repositoryList);

        return repositoryList;
    }

    @Override
    public Pagination<Repository> findRepositoryPage(RepositoryQuery repositoryQuery) {
        Pagination<RepositoryEntity>  pagination = repositoryDao.findRepositoryPage(repositoryQuery);

        List<Repository> repositoryList = BeanMapper.mapList(pagination.getDataList(),Repository.class);

        joinTemplate.joinQuery(repositoryList);

        return PaginationBuilder.build(pagination,repositoryList);
    }

    @Override
    public List<Repository> findRepositoryJoinList(RepositoryQuery repositoryQuery) {
        RepositoryQuery processQuery = new RepositoryQuery();
        processQuery.setOrderParams(repositoryQuery.getOrderParams());
        processQuery.setName(repositoryQuery.getName());
        List<Repository> repositoryList = findRepositoryList(processQuery);

        ArrayList<Repository> repositoryArrayList = new ArrayList<>();

        String userId = repositoryQuery.getUserId();

        for(Repository repository : repositoryList){

            //查询dmuser list
            DmUserQuery dmUserQuery = new DmUserQuery();
            dmUserQuery.setUserId(userId);
            dmUserQuery.setDomainId(repository.getId());
            List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);

            if(dmUserList==null||dmUserList.size()==0){
                continue;
            }

            for(DmUser dmUser: dmUserList){
                if(Objects.equals(dmUser.getUser().getId(), userId)){
                    repositoryArrayList.add(repository);
                }
            }
        }

        // 获取所有关注的 Repository 的 ID 列表
        List<String> followRepositoryIds = getFollowRepositoryIds();
        // 设置是否关注
        for(Repository repository: repositoryArrayList){
            if(followRepositoryIds.contains(repository.getId())){
                repository.setIsFollow(1);
            } else {
                repository.setIsFollow(0);
            }
        }

        joinTemplate.joinQuery(repositoryArrayList);

        return repositoryArrayList;
    }



    private List<String> getFollowRepositoryIds(){
        RepositoryFollowQuery repositoryFollowQuery = new RepositoryFollowQuery();
        repositoryFollowQuery.setUserId(LoginContext.getLoginId());
        List<Repository> repositoryFollowList = repositoryFollowService.findRepositoryFollowList(repositoryFollowQuery);

        return repositoryFollowList.stream()
                .map(repository -> repository.getId())
                .collect(Collectors.toList());

    }

    /**
     * 创建项目权限
     * @param userList
     * @param repositoryId
     */
    public void initProjectDmRole(List<PatchUser> userList, String repositoryId) {
        List<PatchUser> patchUsers = new ArrayList<>();

        boolean has111111 = false;

        for (PatchUser patchUserItem : userList) {
            String masterId = patchUserItem.getId();

            if (!masterId.equals("111111")) {
                // 初始化创建者
                PatchUser patchUser = new PatchUser();
                DmUser dmUser = new DmUser();
                dmUser.setDomainId(repositoryId);
                User user = new User();
                user.setId(masterId);
                dmUser.setUser(user);
                patchUser.setId(masterId);
                patchUser.setAdminRole(true);
                patchUsers.add(patchUser);
            } else {
                has111111 = true;
            }
        }

        // 如果列表中没有 "111111"，再添加默认用户
        if (!has111111) {
            PatchUser patchUser1 = new PatchUser();
            DmUser dmUser1 = new DmUser();
            dmUser1.setDomainId(repositoryId);
            User user1 = new User();
            user1.setId("111111");
            dmUser1.setUser(user1);
            patchUser1.setId("111111");
            patchUser1.setAdminRole(true);
            patchUsers.add(patchUser1);
        }

        dmRoleService.initPatchDmRole(repositoryId, patchUsers, "teston");
    }



}