package io.tiklab.teston.support.backups.service;

import io.tiklab.teston.support.backups.model.Backups;

import java.util.List;

public interface BackupsService {

    String createBackups(Backups backups);

    void updateBackups(Backups backups);

    void deleteBackups(String backupsId);

    Backups findBackups(String backupsId);

    List<Backups> findAllBackups();

    Backups findLastBackups(String type);

}
