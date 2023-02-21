package net.tiklab.teston.apitest.http.scenetest.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.core.order.Order;
import net.tiklab.core.order.OrderBuilders;
import net.tiklab.core.page.Page;

import java.util.List;

@ApiModel
public class ApiSceneCaseQuery {
    @ApiProperty(name = "orderParams", desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name = "pageParam", desc = "分页参数")
    private Page pageParam = new Page();

    @ApiProperty(name = "id", desc = "接口ID，精确匹配")
    private String id;

    @ApiProperty(name = "testCaseId", desc = "用例id精确匹配")
    private String testCaseId;

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

    public ApiSceneCaseQuery setTestCaseId(String testCaseId) {
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
