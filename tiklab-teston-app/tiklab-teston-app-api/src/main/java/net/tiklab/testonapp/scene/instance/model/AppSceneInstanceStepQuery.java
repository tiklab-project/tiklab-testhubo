package net.tiklab.testonapp.scene.instance.model;

import net.tiklab.core.order.Order;
import net.tiklab.core.order.OrderBuilders;
import net.tiklab.core.page.Page;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;

import java.util.List;


@ApiModel
public class AppSceneInstanceStepQuery {

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    @ApiProperty(name = "appSceneInstanceId", desc = "appSceneInstance的id")
    private String appSceneInstanceId;

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

    public String getAppSceneInstanceId() {
        return appSceneInstanceId;
    }

    public AppSceneInstanceStepQuery setAppSceneInstanceId(String appSceneInstanceId) {
        this.appSceneInstanceId = appSceneInstanceId;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}