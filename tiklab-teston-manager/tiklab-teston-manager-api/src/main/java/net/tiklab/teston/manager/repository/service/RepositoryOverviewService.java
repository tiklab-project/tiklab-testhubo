package net.tiklab.teston.manager.repository.service;


import net.tiklab.teston.manager.repository.model.RepositoryTotal;

import javax.validation.constraints.NotNull;

/**
* RepositoryOverviewService
*/
public interface RepositoryOverviewService {


    /**
     * 单个仓库的概况
     * @param id
     * @return
     */
    RepositoryTotal findRepositoryOverview(@NotNull String id);

}