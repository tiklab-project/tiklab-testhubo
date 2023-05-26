package io.tiklab.teston.integrated.postin.postinapi.model;

import io.tiklab.core.BaseModel;
import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;

import java.util.List;

/**
 * postinApitocase配置 模型
 */
@ApiModel
public class PostInApiToCase extends BaseModel {

    @ApiProperty(name="repositoryId",desc="repositoryId")
    private String repositoryId;

    @ApiProperty(name="apiList",desc="接口id列表")
    private List<String> apiList;

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public List<String> getApiList() {
        return apiList;
    }

    public void setApiList(List<String> apiList) {
        this.apiList = apiList;
    }
}
