package io.tiklab.teston.integratedpostin.workspaceBind.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.join.JoinTemplate;
import io.tiklab.postin.workspace.model.Workspace;
import io.tiklab.postin.workspace.model.WorkspaceQuery;
import io.tiklab.postin.workspace.service.WorkspaceService;
import io.tiklab.rpc.client.router.lookup.FixedLookup;
import io.tiklab.teston.integratedpostin.workspaceBind.dao.WorkspaceBindDao;
import io.tiklab.teston.integratedpostin.workspaceBind.entity.WorkspaceBindEntity;
import io.tiklab.teston.integratedpostin.workspaceBind.model.WorkspaceBind;
import io.tiklab.teston.integratedpostin.workspaceBind.model.WorkspaceBindQuery;

import io.tiklab.teston.integratedpostin.postinurl.model.PostinUrl;
import io.tiklab.teston.integratedpostin.postinurl.model.PostinUrlQuery;
import io.tiklab.teston.integratedpostin.postinurl.service.PostinUrlService;
import io.tiklab.teston.support.utils.RpcClientApixUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    RpcClientApixUtil rpcClientApixUtil;

    @Autowired
    PostinUrlService postinUrlService;

    /**
     * rpc 调用
     */
    WorkspaceService workspaceRpc(){
        List<PostinUrl> postinUrlList = postinUrlService.findPostinUrlList(new PostinUrlQuery().setUserId(LoginContext.getLoginId()));
        return rpcClientApixUtil.rpcClient().getBean(WorkspaceService.class, new FixedLookup(postinUrlList.get(0).getUrl()));
    }


    @Override
    public String createWorkspaceBind(@NotNull @Valid WorkspaceBind workspaceBind) {
        WorkspaceBindEntity workspaceBindEntity = BeanMapper.map(workspaceBind, WorkspaceBindEntity.class);

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

        //通过workspaceId 获取空间的数据
        if(workspaceBindList!=null&&workspaceBindList.size()>0){
            for(WorkspaceBind workspaceBind:workspaceBindList){
                Workspace workspace = workspaceRpc().findWorkspace(workspaceBind.getWorkspace().getId());
                workspaceBind.setWorkspace(workspace);
            }
        }
        
        joinTemplate.joinQuery(workspaceBindList);

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

        List<Workspace> workspaceList = workspaceRpc().findWorkspaceList(workspaceQuery);

        return workspaceList;
    }


}