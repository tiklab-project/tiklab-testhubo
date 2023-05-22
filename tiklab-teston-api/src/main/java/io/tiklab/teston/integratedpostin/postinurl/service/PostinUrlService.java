package io.tiklab.teston.integratedpostin.postinurl.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.teston.integratedpostin.postinurl.model.PostinUrl;
import io.tiklab.teston.integratedpostin.postinurl.model.PostinUrlQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* postinUrl配置 服务接口
*/
public interface PostinUrlService {

    /**
    * 创建postinUrl配置
    * @param postinUrl
    * @return
    */
    String createPostinUrl(@NotNull @Valid PostinUrl postinUrl);

    /**
    * 更新postinUrl配置
    * @param postinUrl
    */
    void updatePostinUrl(@NotNull @Valid PostinUrl postinUrl);

    /**
    * 删除postinUrl配置
    * @param id
    */
    void deletePostinUrl(@NotNull String id);

    PostinUrl findOne(@NotNull String id);

    List<PostinUrl> findList(List<String> idList);

    /**
    * 根据id查找postinUrl配置
    * @param id
    * @return
    */
    PostinUrl findPostinUrl(@NotNull String id);

    /**
    * 查找所有postinUrl配置
    * @return
    */
    List<PostinUrl> findAllPostinUrl();

    /**
    * 根据查询参数查询postinUrl配置列表
    * @param postinUrlQuery
    * @return
    */
    List<PostinUrl> findPostinUrlList(PostinUrlQuery postinUrlQuery);

    /**
    * 根据查询参数按分页查询postinUrl配置
    * @param postinUrlQuery
    * @return
    */
    Pagination<PostinUrl> findPostinUrlPage(PostinUrlQuery postinUrlQuery);

}