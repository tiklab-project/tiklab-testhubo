package io.tiklab.teston.test.web.scene.instance.model;

import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class WebSceneInstanceStepQuery {

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    @ApiProperty(name = "webSceneInstanceId", desc = "webSceneInstance的id")
    private String webSceneInstanceId;

    @ApiProperty(name = "id", desc = "id精确匹配")
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

    public String getWebSceneInstanceId() {
        return webSceneInstanceId;
    }

    public WebSceneInstanceStepQuery setWebSceneInstanceId(String webSceneInstanceId) {
        this.webSceneInstanceId = webSceneInstanceId;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}