package io.tiklab.teston.integrated.postin.postinapi.service;

import io.tiklab.teston.integrated.postin.postinapi.model.Apix;
import io.tiklab.teston.integrated.postin.postinapi.model.ApixQuery;
import io.tiklab.teston.integrated.postin.postinapi.model.PostInApiToCase;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* 接口单元 服务接口
*/
public interface PostInApiService  {



    /**
    * postin接口转用例
    * @param postInApiToCase
    * @return
    */
    void createPostInApiToCase(@NotNull @Valid PostInApiToCase postInApiToCase);
    

    /**
    * 根据查询仓库关联的空间查询接口
    * @param repositoryId
    * @return
    */
    List<Apix> findPostInApiList(String repositoryId);




}