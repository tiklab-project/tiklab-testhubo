package io.tiklab.testhubo.test.apix.http.scene.instance.model;


import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class ApiSceneStepInstanceBindQuery {

    @ApiProperty(name ="apiSceneInstanceId",desc = "场景历史id")
    private String apiSceneInstanceId;

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
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

    public String getApiSceneInstanceId() {
        return apiSceneInstanceId;
    }

    public void setApiSceneInstanceId(String apiSceneInstanceId) {
        this.apiSceneInstanceId = apiSceneInstanceId;
    }
}