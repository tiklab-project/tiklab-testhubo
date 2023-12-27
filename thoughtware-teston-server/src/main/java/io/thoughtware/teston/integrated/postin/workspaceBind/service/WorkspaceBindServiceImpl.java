package io.thoughtware.teston.integrated.postin.workspaceBind.service;

import io.thoughtware.teston.common.RestTemplateUtils;
import io.thoughtware.teston.integrated.integratedurl.model.IntegratedUrlQuery;
import io.thoughtware.teston.integrated.integratedurl.service.IntegratedUrlService;
import io.thoughtware.teston.integrated.postin.workspaceBind.model.Workspace;
import io.thoughtware.teston.integrated.postin.workspaceBind.model.WorkspaceBind;
import io.thoughtware.teston.integrated.postin.workspaceBind.model.WorkspaceBindQuery;
import io.thoughtware.teston.integrated.postin.workspaceBind.model.WorkspaceQuery;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.teston.integrated.postin.workspaceBind.dao.WorkspaceBindDao;
import io.thoughtware.teston.integrated.postin.workspaceBind.entity.WorkspaceBindEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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
                MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
                paramMap.add("id",workspaceBind.getWorkspace().getId());
                Workspace workspace = restTemplateUtils.requestPost(findWorkspaceUrl,paramMap, Workspace.class);
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