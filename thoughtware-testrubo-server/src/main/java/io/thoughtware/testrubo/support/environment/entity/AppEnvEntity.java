package io.thoughtware.testrubo.support.environment.entity;


import io.thoughtware.dal.jpa.annotation.*;

import java.io.Serializable;

/**
 * app环境 实体
 */
@Entity @Table(name="teston_env_app")
public class AppEnvEntity implements Serializable {

    @Id
     @GeneratorValue(length = 12)
    @Column(name = "id",length = 32)
    private String id;

    // 所属仓库
    @Column(name = "repository_id",length = 32)
    private String repositoryId;

    // 名称
    @Column(name = "name",length = 128)
    private String name;

    @Column(name = "platform_name",length = 32)
    private String platformName;

    @Column(name = "platform_version",length = 128)
    private String platformVersion;

    @Column(name = "device_name",length = 128)
    private String deviceName;

    @Column(name = "app_package",length = 128)
    private String appPackage;

    @Column(name = "app_activity",length = 128)
    private String appActivity;

    @Column(name = "appium_sever",length = 128)
    private String appiumSever;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(String repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(String appActivity) {
        this.appActivity = appActivity;
    }

    public String getAppiumSever() {
        return appiumSever;
    }

    public void setAppiumSever(String appiumSever) {
        this.appiumSever = appiumSever;
    }
}
