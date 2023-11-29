package io.tiklab.teston.integrated.postin.workspaceBind.service;

import com.alibaba.fastjson.JSONObject;
import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.common.RestTemplateUtils;
import io.tiklab.teston.integrated.postin.workspaceBind.dao.WorkspaceBindDao;
import io.tiklab.teston.integrated.postin.workspaceBind.entity.WorkspaceBindEntity;
import io.tiklab.teston.integrated.postin.workspaceBind.model.Workspace;
import io.tiklab.teston.integrated.postin.workspaceBind.model.WorkspaceBind;
import io.tiklab.teston.integrated.postin.workspaceBind.model.WorkspaceBindQuery;
import io.tiklab.teston.integrated.integratedurl.model.IntegratedUrlQuery;
import io.tiklab.teston.integrated.integratedurl.service.IntegratedUrlService;
import io.tiklab.teston.integrated.postin.workspaceBind.model.WorkspaceQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.*;

/**
* 绑定的空间 服务
*/
@Service
public class WorkspaceBindServiceImpl implements WorkspaceBindService {

    @Autowired
    WorkspaceBindDao workspaceBindDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    IntegratedUrlService integratedUrlService;

    @Autowired
    RestTemplateUtils restTemplateUtils;



    @Override
    public String createWorkspaceBind(@NotNull @Valid WorkspaceBind workspaceBind) {
        WorkspaceBindEntity workspaceBindEntity = BeanMapper.map(workspaceBind, WorkspaceBindEntity.class);
        workspaceBindEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        return workspaceBindDao.createWorkspaceBind(workspaceBindEntity);
    }

    @Override
    public void updateWorkspaceBind(@NotNull @Valid WorkspaceBind workspaceBind) {
        WorkspaceBindEntity workspaceBindEntity = BeanMapper.map(workspaceBind, WorkspaceBindEntity.class);

        workspaceBindDao.updateWorkspaceBind(workspaceBindEntity);
    }

    @Override
    public void deleteWorkspaceBind(@NotNull String id) {
        workspaceBindDao.deleteWorkspaceBind(id);
    }

    @Override
    public WorkspaceBind findOne(String id) {
        WorkspaceBindEntity workspaceBindEntity = workspaceBindDao.findWorkspaceBind(id);

        WorkspaceBind workspaceBind = BeanMapper.map(workspaceBindEntity, WorkspaceBind.class);
        return workspaceBind;
    }

    @Override
    public List<WorkspaceBind> findList(List<String> idList) {
        List<WorkspaceBindEntity> workspaceBindEntityList =  workspaceBindDao.findWorkspaceBindList(idList);

        List<WorkspaceBind> workspaceBindList =  BeanMapper.mapList(workspaceBindEntityList,WorkspaceBind.class);
        return workspaceBindList;
    }

    @Override
    public WorkspaceBind findWorkspaceBind(@NotNull String id) {
        WorkspaceBind workspaceBind = findOne(id);

        joinTemplate.joinQuery(workspaceBind);

        return workspaceBind;
    }

    @Override
    public List<WorkspaceBind> findAllWorkspaceBind() {
        List<WorkspaceBindEntity> workspaceBindEntityList =  workspaceBindDao.findAllWorkspaceBind();

        List<WorkspaceBind> workspaceBindList =  BeanMapper.mapList(workspaceBindEntityList,WorkspaceBind.class);

        joinTemplate.joinQuery(workspaceBindList);

        return workspaceBindList;
    }

    @Override
    public List<WorkspaceBind> findWorkspaceBindList(WorkspaceBindQuery workspaceBindQuery) {
        List<WorkspaceBindEntity> workspaceBindEntityList = workspaceBindDao.findWorkspaceBindList(workspaceBindQuery);
        List<WorkspaceBind> workspaceBindList = BeanMapper.mapList(workspaceBindEntityList,WorkspaceBind.class);

        String postInUrl = getPostInUrl(workspaceBindQuery.getRepositoryId());
        //请求的apix接口
        String findWorkspaceUrl = postInUrl+"/api/workspace/findWorkspace";

        //通过workspaceId 获取空间的数据
        if(workspaceBindList!=null&&workspaceBindList.size()>0){
            for(WorkspaceBind workspaceBind:workspaceBindList){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",workspaceBind.getWorkspace().getId());
                Workspace workspace = restTemplateUtils.requestPost(findWorkspaceUrl,jsonObject, Workspace.class);
                workspaceBind.setWorkspace(workspace);
            }
        }

        return workspaceBindList;
    }

    @Override
    public Pagination<WorkspaceBind> findWorkspaceBindPage(WorkspaceBindQuery workspaceBindQuery) {
        Pagination<WorkspaceBindEntity>  pagination = workspaceBindDao.findWorkspaceBindPage(workspaceBindQuery);

        List<WorkspaceBind> workspaceBindList = BeanMapper.mapList(pagination.getDataList(),WorkspaceBind.class);

        joinTemplate.joinQuery(workspaceBindList);

        return PaginationBuilder.build(pagination,workspaceBindList);
    }

    @Override
    public List<Workspace> findWorkspaceList(WorkspaceQuery workspaceQuery) {

        String postInUrl = getPostInUrl(workspaceQuery.getRepositoryId());
        //请求的apix接口
        String findWorkspaceListUrl = postInUrl+"/api/workspace/findWorkspaceList";

        List<Workspace> workspaceList = restTemplateUtils.requestPostList(findWorkspaceListUrl, workspaceQuery, Workspace.class);

        return workspaceList;
    }

    @Override
    public String bindWorkspace(WorkspaceBind workspaceBind) {
        WorkspaceBindQuery workspaceBindQuery = new WorkspaceBindQuery();
        workspaceBindQuery.setRepositoryId(workspaceBind.getRepositoryId());
        List<WorkspaceBind> workspaceBindList = findWorkspaceBindList(workspaceBindQuery);
        //查看有没有绑定的空间，有就删除
        if(workspaceBindList!=null&&workspaceBindList.size()>0){
            for(WorkspaceBind workspaceBind1:workspaceBindList){
                deleteWorkspaceBind(workspaceBind1.getId());
            }
        }

        //重新绑定空间
        String workspaceBindId = createWorkspaceBind(workspaceBind);

        return workspaceBindId;
    }

    private String getPostInUrl(String repositoryId){
        IntegratedUrlQuery integratedUrlQuery = new IntegratedUrlQuery();
        integratedUrlQuery.setRepositoryId(repositoryId);
        integratedUrlQuery.setProjectName("postin");
        String productUrl = integratedUrlService.getProductUrl(integratedUrlQuery);

        return productUrl;
    }


}