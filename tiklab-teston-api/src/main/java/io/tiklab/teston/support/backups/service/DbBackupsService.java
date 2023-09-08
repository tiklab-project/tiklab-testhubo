package io.tiklab.teston.support.backups.service;


import io.tiklab.teston.support.backups.model.Backups;

public interface DbBackupsService {


    /**
     * 备份数据
     */
    void execBackups();


    /**
     * 查询备份信息
     * @return 备份信息
     */
    Backups findBackupsResult();


    /**
     * 更新备份是否定时开启
     * @param state true：开启 false：关闭
     */
    void updateBackups(Boolean state);




}
