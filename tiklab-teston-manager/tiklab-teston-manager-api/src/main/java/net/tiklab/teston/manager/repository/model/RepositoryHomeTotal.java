package net.tiklab.teston.manager.repository.model;

import net.tiklab.core.BaseModel;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;

@ApiModel
public class RepositoryHomeTotal extends BaseModel {

    @ApiProperty(name="allTotal",desc="接口总和")
    private Integer allTotal;

    @ApiProperty(name="createTotal",desc="分组总和")
    private Integer createTotal;

    @ApiProperty(name="joinTotal",desc="参与总和")
    private Integer joinTotal;

//    @ApiProperty(name="followTotal",desc="模型总和")
//    private Integer followTotal;

    public Integer getAllTotal() {
        return allTotal;
    }

    public void setAllTotal(Integer allTotal) {
        this.allTotal = allTotal;
    }

    public Integer getCreateTotal() {
        return createTotal;
    }

    public void setCreateTotal(Integer createTotal) {
        this.createTotal = createTotal;
    }

    public Integer getJoinTotal() {
        return joinTotal;
    }

    public void setJoinTotal(Integer joinTotal) {
        this.joinTotal = joinTotal;
    }

//    public Integer getFollowTotal() {
//        return followTotal;
//    }
//
//    public void setFollowTotal(Integer followTotal) {
//        this.followTotal = followTotal;
//    }
}
