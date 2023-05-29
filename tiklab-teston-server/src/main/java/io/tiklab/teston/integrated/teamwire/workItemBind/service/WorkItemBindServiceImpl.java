package io.tiklab.teston.integrated.teamwire.workItemBind.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.join.JoinTemplate;
import io.tiklab.rpc.client.router.lookup.FixedLookup;
import io.tiklab.teamwire.workitem.model.WorkItem;
import io.tiklab.teamwire.workitem.service.WorkItemService;
import io.tiklab.teston.integrated.postin.integratedurl.model.IntegratedUrl;
import io.tiklab.teston.integrated.postin.integratedurl.model.IntegratedUrlQuery;
import io.tiklab.teston.integrated.postin.integratedurl.service.PostinUrlService;
import io.tiklab.teston.integrated.teamwire.workItem.model.WorkItemTestOn;
import io.tiklab.teston.integrated.teamwire.workItemBind.dao.WorkItemBindDao;
import io.tiklab.teston.integrated.teamwire.workItemBind.entity.WorkItemBindEntity;
import io.tiklab.teston.integrated.teamwire.workItemBind.model.WorkItemBind;
import io.tiklab.teston.integrated.teamwire.workItemBind.model.WorkItemBindQuery;
import io.tiklab.teston.support.utils.RpcClientApixUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* 绑定的缺陷 服务
*/
@Service
public class WorkItemBindServiceImpl implements WorkItemBindService {

    @Autowired
    WorkItemBindDao workItemBindDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    RpcClientApixUtil rpcClientApixUtil;

    @Autowired
    PostinUrlService postinUrlService;

    /**
     * rpc 调用
     */
    WorkItemService workItemRpc(){
        IntegratedUrlQuery integratedUrlQuery = new IntegratedUrlQuery();
        integratedUrlQuery.setUserId(LoginContext.getLoginId());
        integratedUrlQuery.setProjectName("teamwire");
        List<IntegratedUrl> integratedUrlList = postinUrlService.findPostinUrlList(integratedUrlQuery);

        return rpcClientApixUtil.rpcClient().getBean(WorkItemService.class, new FixedLookup(integratedUrlList.get(0).getUrl()));
    }


    @Override
    public String createWorkItemBind(@NotNull @Valid WorkItemBind workItemBind) {
        WorkItemBindEntity workItemBindEntity = BeanMapper.map(workItemBind, WorkItemBindEntity.class);
        workItemBindEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return workItemBindDao.createWorkItemBind(workItemBindEntity);
    }

    @Override
    public void updateWorkItemBind(@NotNull @Valid WorkItemBind workItemBind) {
        WorkItemBindEntity workItemBindEntity = BeanMapper.map(workItemBind, WorkItemBindEntity.class);

        workItemBindDao.updateWorkItemBind(workItemBindEntity);
    }

    @Override
    public void deleteWorkItemBind(@NotNull String id) {
        workItemBindDao.deleteWorkItemBind(id);
    }

    @Override
    public WorkItemBind findOne(String id) {
        WorkItemBindEntity workItemBindEntity = workItemBindDao.findWorkItemBind(id);

        WorkItemBind workItemBind = BeanMapper.map(workItemBindEntity, WorkItemBind.class);
        return workItemBind;
    }

    @Override
    public List<WorkItemBind> findList(List<String> idList) {
        List<WorkItemBindEntity> workItemBindEntityList =  workItemBindDao.findWorkItemBindList(idList);

        List<WorkItemBind> workItemBindList =  BeanMapper.mapList(workItemBindEntityList,WorkItemBind.class);
        return workItemBindList;
    }

    @Override
    public WorkItemBind findWorkItemBind(@NotNull String id) {
        WorkItemBind workItemBind = findOne(id);

        joinTemplate.joinQuery(workItemBind);

        return workItemBind;
    }

    @Override
    public List<WorkItemBind> findAllWorkItemBind() {
        List<WorkItemBindEntity> workItemBindEntityList =  workItemBindDao.findAllWorkItemBind();

        List<WorkItemBind> workItemBindList =  BeanMapper.mapList(workItemBindEntityList,WorkItemBind.class);

        joinTemplate.joinQuery(workItemBindList);

        return workItemBindList;
    }

    @Override
    public List<WorkItemBind> findWorkItemBindList(WorkItemBindQuery workItemBindQuery) {
        List<WorkItemBindEntity> workItemBindEntityList = workItemBindDao.findWorkItemBindList(workItemBindQuery);
        List<WorkItemBind> workItemBindList = BeanMapper.mapList(workItemBindEntityList,WorkItemBind.class);

        //
        if(workItemBindList!=null&&workItemBindList.size() > 0){
            for(WorkItemBind workItemBind:workItemBindList){
                //查到teamwire缺陷
                WorkItem workItem = workItemRpc().findWorkItem(workItemBind.getWorkItem().getId());
                //放入本工程模型中
                WorkItemTestOn workItemTestOn = new WorkItemTestOn();
                workItemTestOn.setId(workItem.getId());
                workItemTestOn.setName(workItem.getTitle());
                workItemTestOn.setProjectId(workItem.getProject().getId());

                workItemBind.setWorkItem(workItemTestOn);

                //获取服务端地址
                IntegratedUrlQuery integratedUrlQuery = new IntegratedUrlQuery();
                integratedUrlQuery.setProjectName("teamwire");
                List<IntegratedUrl> postinUrlList = postinUrlService.findPostinUrlList(integratedUrlQuery);
                workItemBind.setProjectUrl(postinUrlList.get(0).getUrl());

            }
        }

        return workItemBindList;
    }

    @Override
    public Pagination<WorkItemBind> findWorkItemBindPage(WorkItemBindQuery workItemBindQuery) {
        Pagination<WorkItemBindEntity>  pagination = workItemBindDao.findWorkItemBindPage(workItemBindQuery);

        List<WorkItemBind> workItemBindList = BeanMapper.mapList(pagination.getDataList(),WorkItemBind.class);

        joinTemplate.joinQuery(workItemBindList);

        return PaginationBuilder.build(pagination,workItemBindList);
    }



}