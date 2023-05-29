package io.tiklab.teston.testplan.cases.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class TestPlanQuery implements Serializable {


    @ApiProperty(name = "orderParams", desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name = "pageParam", desc = "分页参数")
    private Page pageParam = new Page();

    @ApiProperty(name = "repositoryId", desc = "空间id")
    private String repositoryId;

    @ApiProperty(name = "name", desc = "测试计划名字，模糊匹配")
    private String name;

    @ApiProperty(name = "state", desc = "测试计划名字，模糊匹配")
    private Integer state;

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

    public String getRepositoryId() {
        return repositoryId;
    }

    public TestPlanQuery setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}