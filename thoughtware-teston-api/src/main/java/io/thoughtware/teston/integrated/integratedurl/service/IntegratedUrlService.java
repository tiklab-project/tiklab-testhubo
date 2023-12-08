package io.thoughtware.teston.integrated.integratedurl.service;

import io.thoughtware.core.page.Pagination;
import io.thoughtware.teston.integrated.integratedurl.model.IntegratedUrl;
import io.thoughtware.teston.integrated.integratedurl.model.IntegratedUrlQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* postinUrl配置 服务接口
*/
public interface IntegratedUrlService {

    /**
    * 创建postinUrl配置
    * @param integratedUrl
    * @return
    */
    String createIntegratedUrl(@NotNull @Valid IntegratedUrl integratedUrl);

    /**
    * 更新postinUrl配置
    * @param integratedUrl
    */
    void updateIntegratedUrl(@NotNull @Valid IntegratedUrl integratedUrl);

    /**
    * 删除postinUrl配置
    * @param id
    */
    void deleteIntegratedUrl(@NotNull String id);

    IntegratedUrl findOne(@NotNull String id);

    List<IntegratedUrl> findList(List<String> idList);

    /**
    * 根据id查找postinUrl配置
    * @param id
    * @return
    */
    IntegratedUrl findIntegratedUrl(@NotNull String id);

    /**
    * 查找所有postinUrl配置
    * @return
    */
    List<IntegratedUrl> findAllIntegratedUrl();

    /**
    * 根据查询参数查询postinUrl配置列表
    * @param integratedUrlQuery
    * @return
    */
    List<IntegratedUrl> findIntegratedUrlList(IntegratedUrlQuery integratedUrlQuery);

    /**
    * 根据查询参数按分页查询postinUrl配置
    * @param integratedUrlQuery
    * @return
    */
    Pagination<IntegratedUrl> findIntegratedUrlPage(IntegratedUrlQuery integratedUrlQuery);

    /**
     * 获取集成产品的服务地址
     * @param integratedUrlQuery
     * @return
     */
    String getProductUrl(IntegratedUrlQuery integratedUrlQuery);
    
}