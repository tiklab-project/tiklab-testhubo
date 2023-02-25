package net.tiklab.testonapix.http.perf.cases.model;

import net.tiklab.core.order.Order;
import net.tiklab.core.order.OrderBuilders;
import net.tiklab.core.page.Page;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class ApiPerfCaseQuery {
    @ApiProperty(name = "orderParams", desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name = "pageParam", desc = "分页参数")
    private Page pageParam = new Page();

    @ApiProperty(name = "testCaseId", desc = "用例id")
    private String testCaseId;

    @ApiProperty(name = "id", desc = "ID，精确匹配")
    private String id;

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

    public String getTestCaseId() {
        return testCaseId;
    }

    public ApiPerfCaseQuery setTestCaseId(String testCaseId) {
        this.testCaseId = testCaseId;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}