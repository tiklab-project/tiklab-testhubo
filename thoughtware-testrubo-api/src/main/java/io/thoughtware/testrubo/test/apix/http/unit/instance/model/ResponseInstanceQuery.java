package io.thoughtware.testrubo.test.apix.http.unit.instance.model;

import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;
import io.thoughtware.core.page.Page;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class ResponseInstanceQuery {
    @ApiProperty(name = "orderParams", desc = "排序参数")

    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name = "pageParam", desc = "分页参数")

    private Page pageParam = new Page();

    @ApiProperty(name = "apiUnitInstanceId", desc = "测试实例id")
    private String apiUnitInstanceId;

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

    public String getApiUnitInstanceId() {
        return apiUnitInstanceId;
    }

    public void setApiUnitInstanceId(String apiUnitInstanceId) {
        this.apiUnitInstanceId = apiUnitInstanceId;
    }
}