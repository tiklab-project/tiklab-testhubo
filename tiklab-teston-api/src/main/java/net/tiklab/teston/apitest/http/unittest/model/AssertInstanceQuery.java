package net.tiklab.teston.apitest.http.unittest.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.core.order.Order;
import net.tiklab.core.order.OrderBuilders;
import net.tiklab.core.page.Page;

import java.util.List;

@ApiModel
public class AssertInstanceQuery {

    @ApiProperty(name = "apiUnitInstanceId", desc = "测试实例id")
    private String apiUnitInstanceId;

    @ApiProperty(name = "orderParams", desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name = "pageParam", desc = "分页参数")
    private Page pageParam = new Page();

    public String getApiUnitInstanceId() {
        return apiUnitInstanceId;
    }

    public AssertInstanceQuery setApiUnitInstanceId(String apiUnitInstanceId) {
        this.apiUnitInstanceId = apiUnitInstanceId;
        return this;
    }

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
}