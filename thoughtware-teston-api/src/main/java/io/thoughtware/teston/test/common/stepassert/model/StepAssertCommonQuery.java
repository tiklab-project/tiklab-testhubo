package io.thoughtware.teston.test.common.stepassert.model;

import io.thoughtware.core.order.Order;
import io.thoughtware.core.order.OrderBuilders;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class StepAssertCommonQuery {

    @ApiProperty(name = "id", desc = "ID，精确匹配")
    private String id;

    @ApiProperty(name = "stepId", desc = "stepId，精确匹配")
    private String stepId;

    @ApiProperty(name = "orderParams", desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("createTime").get();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public List<Order> getOrderParams() {
        return orderParams;
    }

    public void setOrderParams(List<Order> orderParams) {
        this.orderParams = orderParams;
    }
}