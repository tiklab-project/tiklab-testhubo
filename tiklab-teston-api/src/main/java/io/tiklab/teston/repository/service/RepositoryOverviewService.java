package io.tiklab.teston.repository.service;


import io.tiklab.teston.repository.model.RepositoryTotal;

import javax.validation.constraints.NotNull;

/**
* 仓库概况 服务接口
*/
public interface RepositoryOverviewService {


    /**
     * 单个仓库的概况
     * @param id
     * @return
     */
    RepositoryTotal findRepositoryOverview(@NotNull String id);

}