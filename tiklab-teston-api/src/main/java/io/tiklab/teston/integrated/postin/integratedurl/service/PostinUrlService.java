package io.tiklab.teston.integrated.postin.integratedurl.service;

import io.tiklab.core.page.Pagination;
import io.tiklab.teston.integrated.postin.integratedurl.model.IntegratedUrl;
import io.tiklab.teston.integrated.postin.integratedurl.model.IntegratedUrlQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* postinUrl配置 服务接口
*/
public interface PostinUrlService {

    /**
    * 创建postinUrl配置
    * @param integratedUrl
    * @return
    */
    String createPostinUrl(@NotNull @Valid IntegratedUrl integratedUrl);

    /**
    * 更新postinUrl配置
    * @param integratedUrl
    */
    void updatePostinUrl(@NotNull @Valid IntegratedUrl integratedUrl);

    /**
    * 删除postinUrl配置
    * @param id
    */
    void deletePostinUrl(@NotNull String id);

    IntegratedUrl findOne(@NotNull String id);

    List<IntegratedUrl> findList(List<String> idList);

    /**
    * 根据id查找postinUrl配置
    * @param id
    * @return
    */
    IntegratedUrl findPostinUrl(@NotNull String id);

    /**
    * 查找所有postinUrl配置
    * @return
    */
    List<IntegratedUrl> findAllPostinUrl();

    /**
    * 根据查询参数查询postinUrl配置列表
    * @param integratedUrlQuery
    * @return
    */
    List<IntegratedUrl> findPostinUrlList(IntegratedUrlQuery integratedUrlQuery);

    /**
    * 根据查询参数按分页查询postinUrl配置
    * @param integratedUrlQuery
    * @return
    */
    Pagination<IntegratedUrl> findPostinUrlPage(IntegratedUrlQuery integratedUrlQuery);

}