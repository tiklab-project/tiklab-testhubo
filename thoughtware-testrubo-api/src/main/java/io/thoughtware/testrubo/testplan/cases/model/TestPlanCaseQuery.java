package io.thoughtware.testrubo.testplan.cases.model;

import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;
import io.thoughtware.core.page.Page;

import java.util.List;

@ApiModel
public class TestPlanCaseQuery {
    @ApiProperty(name = "orderParams", desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name = "pageParam", desc = "分页参数")
    private Page pageParam = new Page();

    @ApiProperty(name = "testPlanId", desc = "测试计划id")
    private String testPlanId;

    @ApiProperty(name = "repositoryId", desc = "项目id")
    private String repositoryId;

    @ApiProperty(name = "name", desc = "用例名称")
    private String name;

    @ApiProperty(name = "caseType", desc = "用例类型")
    private String caseType;

    @ApiProperty(name = "testType", desc = "用例类型")
    private String testType;

    private String[] caseTypeList;

    public List<Order> getOrderParams() {
        return orderParams;
    }

    public void setOrderParams(List<Order> orderParams) {
        this.orderParams = orderParams;
    }

    public Page getPageParam() {
        return pageParam;
    }

    public void setPageParam(Page pageParam) {
        this.pageParam = pageParam;
    }

    public String getTestPlanId() {
        return testPlanId;
    }

    public TestPlanCaseQuery setTestPlanId(String testPlanId) {
        this.testPlanId = testPlanId;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String[] getCaseTypeList() {
        return caseTypeList;
    }

    public void setCaseTypeList(String[] caseTypeList) {
        this.caseTypeList = caseTypeList;
    }
}