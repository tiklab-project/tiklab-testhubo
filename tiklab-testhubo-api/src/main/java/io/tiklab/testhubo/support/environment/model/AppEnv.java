package io.tiklab.testhubo.support.environment.model;

import io.tiklab.postin.annotation.ApiModel;
import io.tiklab.postin.annotation.ApiProperty;
import io.tiklab.toolkit.beans.annotation.Mapper;
import io.tiklab.core.BaseModel;

/**
 * app环境 模型
 */
@ApiModel
@Mapper
public class AppEnv extends BaseModel {

    @ApiProperty(name="id",desc="id")
    private java.lang.String id;

    @ApiProperty(name="repositoryId",desc="所属仓库")
    private java.lang.String repositoryId;

    @ApiProperty(name="name",desc="名称")
    private java.lang.String name;

    @ApiProperty(name="appiumSever",desc="appiumSever")
    private java.lang.String appiumSever;

    @ApiProperty(name="platformName",desc="platformName")
    private java.lang.String platformName;

    @ApiProperty(name="platformVersion",desc="platformVersion")
    private java.lang.String platformVersion;

    @ApiProperty(name="deviceName",desc="deviceName")
    private java.lang.String deviceName;

    @ApiProperty(name="appPackage",desc="appPackage")
    private java.lang.String appPackage;

    @ApiProperty(name="appActivity",desc="appActivity")
    private java.lang.String appActivity;



    public java.lang.String getId() {
        return id;
    }

    public void setId(java.lang.String id) {
        this.id = id;
    }
    public java.lang.String getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(java.lang.String repositoryId) {
        this.repositoryId = repositoryId;
    }
    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }
    public java.lang.String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(java.lang.String platformName) {
        this.platformName = platformName;
    }
    public java.lang.String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(java.lang.String platformVersion) {
        this.platformVersion = platformVersion;
    }
    public java.lang.String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(java.lang.String deviceName) {
        this.deviceName = deviceName;
    }
    public java.lang.String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(java.lang.String appPackage) {
        this.appPackage = appPackage;
    }
    public java.lang.String getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(java.lang.String appActivity) {
        this.appActivity = appActivity;
    }
    public java.lang.String getAppiumSever() {
        return appiumSever;
    }

    public void setAppiumSever(java.lang.String appiumSever) {
        this.appiumSever = appiumSever;
    }
}
