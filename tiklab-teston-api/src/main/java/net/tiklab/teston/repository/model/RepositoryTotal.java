package net.tiklab.teston.repository.model;

import net.tiklab.core.BaseModel;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;

/**
 * 空间概况 模型
 */
@ApiModel
public class RepositoryTotal extends BaseModel {

    @ApiProperty(name="planTotal",desc="测试计划总和")
    private Integer planTotal;

    @ApiProperty(name="categoryTotal",desc="分组总和")
    private Integer categoryTotal;

    @ApiProperty(name="reviewTotal",desc="用例评审总和")
    private Integer reviewTotal;

    @ApiProperty(name="memberTotal",desc="成员总和")
    private Integer memberTotal;


    public Integer getPlanTotal() {
        return planTotal;
    }

    public void setPlanTotal(Integer planTotal) {
        this.planTotal = planTotal;
    }

    public Integer getCategoryTotal() {
        return categoryTotal;
    }

    public void setCategoryTotal(Integer categoryTotal) {
        this.categoryTotal = categoryTotal;
    }

    public Integer getReviewTotal() {
        return reviewTotal;
    }

    public void setReviewTotal(Integer reviewTotal) {
        this.reviewTotal = reviewTotal;
    }

    public Integer getMemberTotal() {
        return memberTotal;
    }

    public void setMemberTotal(Integer memberTotal) {
        this.memberTotal = memberTotal;
    }
}
