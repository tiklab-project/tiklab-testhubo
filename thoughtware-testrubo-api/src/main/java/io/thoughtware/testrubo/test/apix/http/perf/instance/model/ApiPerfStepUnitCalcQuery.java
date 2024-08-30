package io.thoughtware.testrubo.test.apix.http.perf.instance.model;

import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;
import io.thoughtware.core.page.Page;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class ApiPerfStepUnitCalcQuery {
    @ApiProperty(name = "orderParams", desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().desc("name").get();

    @ApiProperty(name = "pageParam", desc = "分页参数")
    private Page pageParam = new Page();

    @ApiProperty(name = "apiPerfStepInstanceId", desc = "性能测试id")
    private String apiPerfStepInstanceId;

    @ApiProperty(name = "id", desc = "性能测试id")
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

    public String getApiPerfStepInstanceId() {
        return apiPerfStepInstanceId;
    }

    public void setApiPerfStepInstanceId(String apiPerfStepInstanceId) {
        this.apiPerfStepInstanceId = apiPerfStepInstanceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}