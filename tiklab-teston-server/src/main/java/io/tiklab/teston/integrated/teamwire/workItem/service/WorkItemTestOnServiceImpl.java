package io.tiklab.teston.integrated.teamwire.workItem.service;


import io.tiklab.eam.common.context.LoginContext;
import io.tiklab.rpc.client.router.lookup.FixedLookup;
import io.tiklab.teamwire.project.project.model.Project;
import io.tiklab.teamwire.project.project.service.ProjectService;
import io.tiklab.teamwire.workitem.model.*;
import io.tiklab.teamwire.workitem.service.WorkItemService;
import io.tiklab.teamwire.workitem.service.WorkTypeDmService;
import io.tiklab.teamwire.workitem.service.WorkTypeService;
import io.tiklab.teston.integrated.postin.integratedurl.model.IntegratedUrl;
import io.tiklab.teston.integrated.postin.integratedurl.model.IntegratedUrlQuery;
import io.tiklab.teston.integrated.postin.integratedurl.service.PostinUrlService;
import io.tiklab.teston.integrated.teamwire.workItem.model.ProjectTestOn;
import io.tiklab.teston.integrated.teamwire.workItem.model.ProjectTestOnQuery;
import io.tiklab.teston.integrated.teamwire.workItem.model.WorkItemTestOn;
import io.tiklab.teston.integrated.teamwire.workItem.model.WorkItemTestOnQuery;
import io.tiklab.teston.integrated.teamwire.workItemBind.model.WorkItemBind;
import io.tiklab.teston.integrated.teamwire.workItemBind.service.WorkItemBindService;
import io.tiklab.teston.support.utils.RpcClientApixUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * teamwire 需求 缺陷
 */
@Service
public class WorkItemTestOnServiceImpl implements WorkItemTestOnService {

    @Autowired
    RpcClientApixUtil rpcClientApixUtil;

    @Autowired
    WorkItemBindService workItemBindService;

    @Autowired
    PostinUrlService postinUrlService;

    /**
     * rpc 调用
     */
    ProjectService projectServiceRpc(){
        IntegratedUrlQuery integratedUrlQuery = new IntegratedUrlQuery();
        integratedUrlQuery.setUserId(LoginContext.getLoginId());
        integratedUrlQuery.setProjectName("teamwire");
        List<IntegratedUrl> integratedUrlList = postinUrlService.findPostinUrlList(integratedUrlQuery);
        return rpcClientApixUtil.rpcClient().getBean(ProjectService.class, new FixedLookup(integratedUrlList.get(0).getUrl()));
    }

    /**
     * rpc 调用
     */
    WorkTypeDmService workTypeDmServiceRpc(){
        IntegratedUrlQuery integratedUrlQuery = new IntegratedUrlQuery();
        integratedUrlQuery.setUserId(LoginContext.getLoginId());
        integratedUrlQuery.setProjectName("teamwire");
        List<IntegratedUrl> integratedUrlList = postinUrlService.findPostinUrlList(integratedUrlQuery);
        return rpcClientApixUtil.rpcClient().getBean(WorkTypeDmService.class, new FixedLookup(integratedUrlList.get(0).getUrl()));
    }

    /**
     * rpc 调用
     */
    WorkItemService workItemServiceRpc(){
        IntegratedUrlQuery integratedUrlQuery = new IntegratedUrlQuery();
        integratedUrlQuery.setUserId(LoginContext.getLoginId());
        integratedUrlQuery.setProjectName("teamwire");
        List<IntegratedUrl> integratedUrlList = postinUrlService.findPostinUrlList(integratedUrlQuery);
        return rpcClientApixUtil.rpcClient().getBean(WorkItemService.class, new FixedLookup(integratedUrlList.get(0).getUrl()));
    }


    @Override
    public List<ProjectTestOn> findProjectList(ProjectTestOnQuery projectTestOnQuery) {
        //查询所有项目
        List<Project> projectList = projectServiceRpc().findAllProject();
        
        //存储处理过的项目
        ArrayList<ProjectTestOn> arrayList = new ArrayList<>();
        if(projectList!=null&&projectList.size()>0){
            for(Project project:projectList){
                ProjectTestOn projectTestOn = new ProjectTestOn();
                projectTestOn.setId(project.getId());
                projectTestOn.setName(project.getProjectName());
                arrayList.add(projectTestOn);
            }
        }
        
        return arrayList;
    }

    @Override
    public List<WorkItemTestOn> findWorkItemList(WorkItemTestOnQuery workItemTestOnQuery) {
        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setWorkTypeCode(workItemTestOnQuery.getWorkTypeCode());
        workItemQuery.setProjectId(workItemTestOnQuery.getProjectId());
        workItemQuery.setTitle(workItemTestOnQuery.getName());
        List<WorkItem> workItemList = workItemServiceRpc().findWorkItemList(workItemQuery);

        //存储处理过的事项
        ArrayList<WorkItemTestOn> workItemTestOnList = new ArrayList<>();
        if(workItemList!=null&&workItemList.size()>0){
            for(WorkItem workItem:workItemList){
                WorkItemTestOn workItemTestOn = new WorkItemTestOn();
                workItemTestOn.setId(workItem.getId());
                workItemTestOn.setName(workItem.getTitle());
                workItemTestOn.setProjectName(workItem.getProject().getProjectName());
                if(workItem.getWorkStatusNode()!=null&&workItem.getWorkStatusNode().getName()!=null){
                    workItemTestOn.setStatus(workItem.getWorkStatusNode().getName());
                }
                if(workItem.getAssigner()!=null&&workItem.getAssigner().getNickname()!=null){
                    workItemTestOn.setDirector(workItem.getAssigner().getNickname());
                }
                if(workItem.getWorkPriority()!=null&&workItem.getWorkPriority().getName()!=null){
                    workItemTestOn.setPriority(workItem.getWorkPriority().getName());
                }


                workItemTestOnList.add(workItemTestOn);
            }
        }

        return workItemTestOnList;
    }

    @Override
    public WorkItemTestOn findWorkItem(String id) {
        WorkItem workItem = workItemServiceRpc().findWorkItem(id);

        WorkItemTestOn workItemTestOn = new WorkItemTestOn();
        workItemTestOn.setId(workItem.getId());
        workItemTestOn.setName(workItem.getTitle());
        workItemTestOn.setProjectId(workItem.getProject().getId());
        workItemTestOn.setProjectName(workItem.getProject().getProjectName());
        if(workItem.getWorkStatusNode()!=null&&workItem.getWorkStatusNode().getName()!=null){
            workItemTestOn.setStatus(workItem.getWorkStatusNode().getName());
        }
        if(workItem.getAssigner()!=null&&workItem.getAssigner().getNickname()!=null){
            workItemTestOn.setDirector(workItem.getAssigner().getNickname());
        }
        if(workItem.getWorkPriority()!=null&&workItem.getWorkPriority().getName()!=null){
            workItemTestOn.setPriority(workItem.getWorkPriority().getName());
        }


        //获取服务端地址
        IntegratedUrlQuery integratedUrlQuery = new IntegratedUrlQuery();
        integratedUrlQuery.setProjectName("teamwire");
        List<IntegratedUrl> postinUrlList = postinUrlService.findPostinUrlList(integratedUrlQuery);
        workItemTestOn.setProjectUrl(postinUrlList.get(0).getUrl());

        return workItemTestOn;
    }

    @Override
    public String createWorkItem(WorkItemTestOn workItemTestOn) {
        WorkTypeDmQuery workTypeDmQuery = new WorkTypeDmQuery();
        workTypeDmQuery.setProjectId(workItemTestOn.getProjectId());
        List<WorkTypeDm> workTypeDmList = workTypeDmServiceRpc().findWorkTypeDmList(workTypeDmQuery);

        //从teamwire获取类型为缺陷
        WorkTypeDm defectType = null;
        if(workTypeDmList!=null&&workTypeDmList.size()>0){
            for(WorkTypeDm workTypeDm:workTypeDmList){
                if(Objects.equals(workTypeDm.getWorkType().getCode(),"defect")){
                    defectType = workTypeDm;
                }
            }
        }

        WorkItem workItem = new WorkItem();
        //设置类型为缺陷
        workItem.setWorkType(defectType);
        //设置项目
        Project project = new Project();
        project.setId(workItemTestOn.getProjectId());
        workItem.setProject(project);
        //设置名称
        workItem.setTitle(workItemTestOn.getName());
        //设置开始结束时间
        workItem.setPlanBeginTime(workItemTestOn.getPlanBeginTime());
        workItem.setPlanEndTime(workItemTestOn.getPlanEndTime());
        //创建缺陷获取id
        String defectId = workItemServiceRpc().createWorkItem(workItem);


        //绑定到当前用例上
        WorkItemBind workItemBind = new WorkItemBind();
        WorkItemTestOn workItemTestOn1 = new WorkItemTestOn();
        workItemTestOn1.setId(defectId);
        workItemBind.setWorkItem(workItemTestOn1);
        workItemBind.setCaseId(workItemTestOn.getCaseId());

        String workItemBindId = workItemBindService.createWorkItemBind(workItemBind);

        return workItemBindId;
    }


}
