package io.tiklab.teston.integrated.teamwire.workItem.service;


import com.alibaba.fastjson.JSONObject;
import io.tiklab.teston.common.RestTemplateUtils;
import io.tiklab.teston.integrated.integratedurl.model.IntegratedUrl;
import io.tiklab.teston.integrated.integratedurl.model.IntegratedUrlQuery;
import io.tiklab.teston.integrated.integratedurl.service.IntegratedUrlService;
import io.tiklab.teston.integrated.teamwire.workItem.model.*;
import io.tiklab.teston.integrated.teamwire.workItemBind.model.WorkItemBind;
import io.tiklab.teston.integrated.teamwire.workItemBind.service.WorkItemBindService;
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
    WorkItemBindService workItemBindService;

    @Autowired
    IntegratedUrlService integratedUrlService;

    @Autowired
    RestTemplateUtils restTemplateUtils;



    @Override
    public List<ProjectTestOn> findProjectList(ProjectTestOnQuery projectTestOnQuery) {

        String teamWireUrl = getTeamWireUrl(projectTestOnQuery.getRepositoryId());
        String findAllProjectUrl = teamWireUrl + "/api/project/findAllProject";

        //查询所有项目
        List<Project> projectList = restTemplateUtils.requestPostList(findAllProjectUrl, null, Project.class);

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

        String teamWireUrl = getTeamWireUrl(workItemTestOnQuery.getRepositoryId());
        String findAllProjectUrl = teamWireUrl + "/api/workItem/findWorkItemList";

        WorkItemQuery workItemQuery = new WorkItemQuery();
        workItemQuery.setWorkTypeCode(workItemTestOnQuery.getWorkTypeCode());
        workItemQuery.setProjectId(workItemTestOnQuery.getProjectId());
        workItemQuery.setTitle(workItemTestOnQuery.getName());
        List<WorkItem> workItemList = restTemplateUtils.requestPostList(findAllProjectUrl, workItemQuery, WorkItem.class);

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
    public WorkItemTestOn findWorkItem(String id,String repositoryId) {

        String teamWireUrl = getTeamWireUrl(repositoryId);
        String findWorkItemUrl = teamWireUrl + "/api/workItem/findWorkItem";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        WorkItem workItem = restTemplateUtils.requestPost(findWorkItemUrl, jsonObject, WorkItem.class);

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

        workItemTestOn.setProjectUrl(teamWireUrl);

        return workItemTestOn;
    }

    @Override
    public String createWorkItem(WorkItemTestOn workItemTestOn) {

        String teamWireUrl = getTeamWireUrl(workItemTestOn.getRepositoryId());
        String findWorkTypeDmListUrl = teamWireUrl + "/api/workTypeDm/findWorkTypeDmList";

        WorkTypeDmQuery workTypeDmQuery = new WorkTypeDmQuery();
        workTypeDmQuery.setProjectId(workItemTestOn.getProjectId());
        List<WorkTypeDm> workTypeDmList = restTemplateUtils.requestPostList(findWorkTypeDmListUrl, workTypeDmQuery, WorkTypeDm.class);

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
//        workItem.setPlanBeginTime(workItemTestOn.getPlanBeginTime());
//        workItem.setPlanEndTime(workItemTestOn.getPlanEndTime());

        //创建缺陷获取id
        String createWorkItemUrl = teamWireUrl + "/api/workType/createWorkItem";
        String defectId = restTemplateUtils.requestPost(createWorkItemUrl, workItem, String.class);


        //绑定到当前用例上
        WorkItemBind workItemBind = new WorkItemBind();
        WorkItemTestOn workItemTestOn1 = new WorkItemTestOn();
        workItemTestOn1.setId(defectId);
        workItemBind.setWorkItem(workItemTestOn1);
        workItemBind.setCaseId(workItemTestOn.getCaseId());

        String workItemBindId = workItemBindService.createWorkItemBind(workItemBind);

        return workItemBindId;
    }



    private String getTeamWireUrl(String repositoryId){
        IntegratedUrlQuery integratedUrlQuery = new IntegratedUrlQuery();
        integratedUrlQuery.setRepositoryId(repositoryId);
        integratedUrlQuery.setProjectName("teamwire");
        String productUrl = integratedUrlService.getProductUrl(integratedUrlQuery);

        return productUrl;
    }


}
