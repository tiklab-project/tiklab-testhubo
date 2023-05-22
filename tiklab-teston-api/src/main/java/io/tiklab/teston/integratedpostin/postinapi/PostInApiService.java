package io.tiklab.teston.integratedpostin.postinapi;

import io.tiklab.postin.api.apix.model.ApixQuery;
import io.tiklab.postin.api.http.definition.model.HttpApi;

import java.util.List;

/**
* 接口单元 服务接口
*/
public interface PostInApiService  {



    /**
    * 创建接口单元
    * @param unitCase
    * @return
    */
//    String createPostInApi(@NotNull @Valid PostInApi unitCase);
    

    /**
    * 根据查询参数查询接口单元列表
    * @param apixQuery
    * @return
    */
    List<HttpApi> findPostInApiList(ApixQuery apixQuery);




}