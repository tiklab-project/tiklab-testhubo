package io.thoughtware.teston.test.app.scene.execute.model;


import io.thoughtware.core.BaseModel;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

/**
 * appium的配置
 */
@ApiModel
public class AppTestConfig extends BaseModel {

    @ApiProperty(name="deviceName",desc="设备名称",required = true)
    private String deviceName;

    @ApiProperty(name="platformName",desc="被测APP所处平台-操作系统 Android  IOS",required = true)
    private String platformName;

    @ApiProperty(name="platformVersion",desc="操作系统版本")
    private String platformVersion;

    @ApiProperty(name="appPackage",desc="android应用的包名")
    private String appPackage;

    @ApiProperty(name="appActivity",desc="被测app入口")
    private String appActivity;

    @ApiProperty(name="appiumSever",desc="appium通信地址")
    private String appiumSever;

    @ApiProperty(name="udId",desc="模拟器地址")
    private String udId;

    @ApiProperty(name="mainjsPath",desc="mainjs路径  自动自动appiu需要")
    private String mainjsPath;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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

    public String getUdId() {
        return udId;
    }

    public void setUdId(String udId) {
        this.udId = udId;
    }

    public String getMainjsPath() {
        return mainjsPath;
    }

    public void setMainjsPath(String mainjsPath) {
        this.mainjsPath = mainjsPath;
    }
}
