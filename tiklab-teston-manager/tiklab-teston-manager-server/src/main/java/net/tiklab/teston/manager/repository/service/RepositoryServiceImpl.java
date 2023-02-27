package net.tiklab.teston.manager.repository.service;

import com.alibaba.fastjson.JSONObject;
import net.tiklab.beans.BeanMapper;
import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.join.JoinTemplate;
import net.tiklab.logging.modal.LoggingType;
import net.tiklab.logging.service.LoggingTypeService;
import net.tiklab.message.message.model.SendMessageNotice;
import net.tiklab.message.message.service.SendMessageNoticeService;
import net.tiklab.privilege.roleDmRole.service.DmRoleService;
import net.tiklab.teston.manager.category.model.Category;
import net.tiklab.teston.manager.category.model.CategoryQuery;
import net.tiklab.teston.manager.category.service.CategoryService;
import net.tiklab.teston.manager.common.LogUnit;
import net.tiklab.teston.manager.common.TestOnUnit;
import net.tiklab.teston.manager.repository.dao.RepositoryDao;
import net.tiklab.teston.manager.repository.entity.RepositoryEntity;
import net.tiklab.teston.manager.repository.model.*;
import net.tiklab.teston.manager.testplan.model.TestPlan;
import net.tiklab.teston.manager.testplan.model.TestPlanQuery;
import net.tiklab.teston.manager.testplan.service.TestPlanService;
import net.tiklab.user.dmUser.model.DmUser;
import net.tiklab.user.dmUser.model.DmUserQuery;
import net.tiklab.user.dmUser.service.DmUserService;
import net.tiklab.user.user.model.User;
import net.tiklab.user.user.service.UserService;
import net.tiklab.utils.context.LoginContext;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

import static net.tiklab.teston.manager.common.MessageTemplateConstant.LOG_TYPE_CREATE_ID;
import static net.tiklab.teston.manager.common.MessageTemplateConstant.LOG_TYPE_UPDATE_ID;

/**
* RepositoryServiceImpl
*/
@Service
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
    UserService userService;

    @Autowired
    DmRoleService dmRoleService;

    @Autowired
    TestOnUnit testOnUnit;

    @Autowired
    LoggingTypeService loggingTypeService;

    @Autowired
    SendMessageNoticeService sendMessageNoticeService;

    @Autowired
    LogUnit logUnit;

    @Autowired
    TestPlanService testPlanService;

    @Value("${base.url:null}")
    String baseUrl;

    @Override
    public String createRepository(@NotNull @Valid Repository repository) {
        String userId = LoginContext.getLoginId();
        User userInfo = userService.findUser(userId);

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

        //初始化项目权限
        dmRoleService.initDmRoles(repositoryId,userId,"teston" );

        //日志
        Map<String,String> map = new HashMap<>();
        map.put("name",repository.getName());
        map.put("repositoryId",repositoryId);
        map.put("user",testOnUnit.getUser().getNickname());
        map.put("mode","仓库");
        map.put("images",repository.getIconUrl());
        LoggingType oplogTypeOne = loggingTypeService.findOplogTypeOne(LOG_TYPE_CREATE_ID);
        map.put("actionType",oplogTypeOne.getName());

        logUnit.log(LOG_TYPE_CREATE_ID,"repository",map);

        //消息
        //站内信
        SendMessageNotice messageDispatchNotice = new SendMessageNotice();
        Map<String,String> site_mail_Map = new HashMap<>();
        site_mail_Map.put("name",repository.getName());
        site_mail_Map.put("id",repositoryId);
        site_mail_Map.put("userName",userInfo.getNickname());
        site_mail_Map.put("images",repository.getIconUrl());
        String siteMailMsg = JSONObject.toJSONString(site_mail_Map);

        messageDispatchNotice.setSiteData(siteMailMsg);

        //邮箱
        messageDispatchNotice.setEmailData(siteMailMsg);

        //钉钉
        Map<String,String> DD_MSGMap = new HashMap<>();
        DD_MSGMap.put("name",repository.getName());
        DD_MSGMap.put("userName",userInfo.getNickname());
        DD_MSGMap.put("images",repository.getIconUrl());
        messageDispatchNotice.setDingdingData(JSONObject.toJSONString(DD_MSGMap));

        //企业微信
        Map<String,String> WX_MSGMap = new HashMap<>();
        WX_MSGMap.put("name",repository.getName());
        WX_MSGMap.put("userName",userInfo.getNickname());
        messageDispatchNotice.setQywechatData(JSONObject.toJSONString(WX_MSGMap));

        messageDispatchNotice.setId("MESSAGE_NOTICE_ID");
        messageDispatchNotice.setBaseUrl(baseUrl);
        sendMessageNoticeService.createMessageItem(messageDispatchNotice);

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
        map.put("user",testOnUnit.getUser().getNickname());
        map.put("mode","仓库");
        map.put("images",repository.getIconUrl());
        LoggingType oplogTypeOne = loggingTypeService.findOplogTypeOne(LOG_TYPE_UPDATE_ID);
        map.put("actionType",oplogTypeOne.getName());
        logUnit.log(LOG_TYPE_UPDATE_ID,"repository",map);

    }

    @Override
    public void deleteRepository(@NotNull String id) {
        repositoryDao.deleteRepository(id);
        List<Category> categoryList = categoryService.findCategoryList(new CategoryQuery().setRepositoryId(id));
        if(CollectionUtils.isNotEmpty(categoryList)){
            for(Category category:categoryList){
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
        RepositoryFollowQuery repositoryFollowQuery = new RepositoryFollowQuery();
        List<Repository> repositoryFollowList = repositoryFollowService.findRepositoryFollowList(repositoryFollowQuery);

        //设置是否关注
        if(CollectionUtils.isNotEmpty(repositoryList)&&CollectionUtils.isNotEmpty(repositoryFollowList)){
            for(Repository repository : repositoryList){
                for(Repository repositoryFollow: repositoryFollowList){
                    if(Objects.equals(repository.getId(), repositoryFollow.getId())){
                        repository.setIsFollow(1);
                    }else {
                        repository.setIsFollow(0);
                    }
                }
            }
        }else {
            for(Repository repository : repositoryList){
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
        //查询dmuser list
        DmUserQuery dmUserQuery = new DmUserQuery();
        dmUserQuery.setUserId(repositoryQuery.getUserId());
        List<DmUser> dmUserList = dmUserService.findDmUserList(dmUserQuery);

        RepositoryQuery repositoryQuery1 = new RepositoryQuery();
        List<Repository> repositoryList = findRepositoryList(repositoryQuery1);


        ArrayList<Repository> repositoryArrayList = new ArrayList<>();

        if(CollectionUtils.isNotEmpty(dmUserList)){
            for(DmUser dmUser: dmUserList){
                for(Repository repository : repositoryList){
                    if(Objects.equals(dmUser.getDomainId(), repository.getId())){
                        repositoryArrayList.add(repository);
                    }
                }
            }
        }
        joinTemplate.joinQuery(repositoryArrayList);

        return repositoryArrayList;
    }




}