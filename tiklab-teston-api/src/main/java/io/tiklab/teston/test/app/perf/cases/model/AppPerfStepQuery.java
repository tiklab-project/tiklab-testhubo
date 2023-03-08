package io.tiklab.teston.test.app.perf.cases.model;

import io.tiklab.core.BaseModel;
import io.tiklab.core.order.Order;
import io.tiklab.core.order.OrderBuilders;
import io.tiklab.core.page.Page;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

@ApiModel
public class AppPerfStepQuery extends BaseModel {

    @ApiProperty(name ="orderParams",desc = "排序参数")
    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name ="pageParam",desc = "分页参数")
    private Page pageParam = new Page();

    @ApiProperty(name = "appPerfId",desc = "性能测试id")
    private String appPerfId;

    @ApiProperty(name = "id", desc = "ID，精确匹配")
    private String id;

    public String getAppPerfId() {
        return appPerfId;
    }

    public void setAppPerfId(String appPerfId) {
        this.appPerfId = appPerfId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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