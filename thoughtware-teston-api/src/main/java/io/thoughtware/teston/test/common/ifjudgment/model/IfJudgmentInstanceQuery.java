package io.thoughtware.teston.test.common.ifjudgment.model;

import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;
import io.thoughtware.core.page.Page;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel
public class IfJudgmentInstanceQuery implements Serializable {
    @ApiProperty(name ="stepInstanceId",desc = "用例，精确匹配")
    private String stepInstanceId;

    @ApiProperty(name = "orderParams", desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name = "pageParam", desc = "分页参数")
    private Page pageParam = new Page();

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

    public String getStepInstanceId() {
        return stepInstanceId;
    }

    public void setStepInstanceId(String stepInstanceId) {
        this.stepInstanceId = stepInstanceId;
    }
}