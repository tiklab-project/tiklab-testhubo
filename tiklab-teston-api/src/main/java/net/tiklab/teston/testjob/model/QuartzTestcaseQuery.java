package net.tiklab.teston.testjob.model;

import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.core.order.Order;
import net.tiklab.core.order.OrderBuilders;
import net.tiklab.core.page.Page;

import java.util.List;

@ApiModel
public class QuartzTestcaseQuery {
    @ApiProperty(name = "orderParams", desc = "排序参数")

    private List<Order> orderParams = OrderBuilders.instance().asc("id").get();

    @ApiProperty(name = "pageParam", desc = "分页参数")

    private Page pageParam = new Page();

    @ApiProperty(name = "quartzMasterId", desc = "定时任务主表id")

    private String quartzMasterId;

    @ApiProperty(name = "repositoryId", desc = "空间id")

    private String repositoryId;

    @ApiProperty(name = "quartzType", desc = "用例类型 app  api web")

    private String quartzType;

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

    public String getQuartzMasterId() {
        return quartzMasterId;
    }

    public QuartzTestcaseQuery setQuartzMasterId(String quartzMasterId) {
        this.quartzMasterId = quartzMasterId;
        return this;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getQuartzType() {
        return quartzType;
    }

    public void setQuartzType(String quartzType) {
        this.quartzType = quartzType;
    }
}