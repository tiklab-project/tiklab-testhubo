package io.thoughtware.testrubo.integrated.teamwire.workItemBind.service;

import io.thoughtware.testrubo.common.RestTemplateUtils;
import io.thoughtware.testrubo.integrated.integratedurl.model.IntegratedUrl;
import io.thoughtware.testrubo.integrated.integratedurl.model.IntegratedUrlQuery;
import io.thoughtware.testrubo.integrated.integratedurl.service.IntegratedUrlService;
import io.thoughtware.testrubo.integrated.teamwire.workItem.model.WorkItem;
import io.thoughtware.testrubo.integrated.teamwire.workItem.model.WorkItemTestOn;
import io.thoughtware.testrubo.integrated.teamwire.workItemBind.model.WorkItemBind;
import io.thoughtware.testrubo.integrated.teamwire.workItemBind.model.WorkItemBindQuery;
import io.thoughtware.testrubo.test.test.model.TestCase;
import io.thoughtware.testrubo.test.test.service.TestCaseService;
import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.core.page.Pagination;
import io.thoughtware.core.page.PaginationBuilder;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.testrubo.integrated.teamwire.workItemBind.dao.WorkItemBindDao;
import io.thoughtware.testrubo.integrated.teamwire.workItemBind.entity.WorkItemBindEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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
    TestCaseService testCaseService;

    @Autowired
    JoinTemplate joinTemplate;

    @Autowired
    IntegratedUrlService integratedUrlService;

    @Autowired
    RestTemplateUtils restTemplateUtils;


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
    public void deleteAllWorkItemBind(String caseId) {
        WorkItemBindQuery workItemBindQuery = new WorkItemBindQuery();
        workItemBindQuery.setCaseId(caseId);
        List<WorkItemBind> workItemBindList = findWorkItemBindList(workItemBindQuery);
        for(WorkItemBind workItemBind : workItemBindList){
            deleteWorkItemBind(workItemBind.getId());
        }
    }

    @Override
    public int findTotalNum(String caseId){
        int totalNum = workItemBindDao.findTotalNum(caseId);
        return totalNum;
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
                String caseId = workItemBind.getTestCase().getId();
                TestCase testCase = testCaseService.findTestCase(caseId);
                workItemBind.setTestCase(testCase);

                String teamWireUrl = getTeamWireUrl(workItemBindQuery.getRepositoryId());
                String findWorkItemUrl = teamWireUrl + "/api/workItem/findWorkItem";
                //查到teamwire缺陷

                MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
                paramMap.add("id",workItemBind.getWorkItem().getId());
                WorkItem workItem = restTemplateUtils.requestPost(findWorkItemUrl, paramMap, WorkItem.class);

                if(workItem!=null){
                    //放入本工程模型中
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

                    workItemBind.setWorkItem(workItemTestOn);

                    //获取服务端地址
                    IntegratedUrlQuery integratedUrlQuery = new IntegratedUrlQuery();
                    integratedUrlQuery.setProjectName("teamwire");
                    List<IntegratedUrl> postinUrlList = integratedUrlService.findIntegratedUrlList(integratedUrlQuery);
                    workItemBind.setProjectUrl(postinUrlList.get(0).getUrl());
                }

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

    private String getTeamWireUrl(String repositoryId){
        IntegratedUrlQuery integratedUrlQuery = new IntegratedUrlQuery();
        integratedUrlQuery.setRepositoryId(repositoryId);
        integratedUrlQuery.setProjectName("teamwire");
        String productUrl = integratedUrlService.getProductUrl(integratedUrlQuery);

        return productUrl;
    }


}