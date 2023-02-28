package net.tiklab.teston.web.perf.execute.model;

import net.tiklab.core.BaseModel;
import net.tiklab.postin.annotation.ApiModel;
import net.tiklab.postin.annotation.ApiProperty;
import net.tiklab.teston.web.perf.cases.model.WebPerfCase;
import net.tiklab.teston.web.scene.execute.model.WebSceneTestRequest;

import java.util.List;

@ApiModel
public class WebPerfTestRequest extends BaseModel {

    @ApiProperty(name = "webPerfId", desc = "webPerfId")
    private String webPerfId;

    @ApiProperty(name="webPerfCase",desc="性能测试用例")
    private WebPerfCase webPerfCase;

    @ApiProperty(name = "webEnv", desc = "webEnv")
    private String webEnv;

    @ApiProperty(name="webSceneTestRequestList",desc="压力测试下绑定的场景列表请求参数")
    private List<WebSceneTestRequest> webSceneTestRequestList;

    @ApiProperty(name="exeNum",desc="执行次数")
    private Integer exeNum;


    @ApiProperty(name = "exeType", desc = "exeType")
    private String exeType;

    public Integer getExeNum() {
        return exeNum;
    }

    public void setExeNum(Integer exeNum) {
        this.exeNum = exeNum;
    }

    public String getWebPerfId() {
        return webPerfId;
    }

    public void setWebPerfId(String webPerfId) {
        this.webPerfId = webPerfId;
    }

    public WebPerfCase getWebPerfCase() {
        return webPerfCase;
    }

    public void setWebPerfCase(WebPerfCase webPerfCase) {
        this.webPerfCase = webPerfCase;
    }

    public String getWebEnv() {
        return webEnv;
    }

    public void setWebEnv(String webEnv) {
        this.webEnv = webEnv;
    }

    public List<WebSceneTestRequest> getWebSceneTestRequestList() {
        return webSceneTestRequestList;
    }

    public void setWebSceneTestRequestList(List<WebSceneTestRequest> webSceneTestRequestList) {
        this.webSceneTestRequestList = webSceneTestRequestList;
    }

    public String getExeType() {
        return exeType;
    }

    public void setExeType(String exeType) {
        this.exeType = exeType;
    }
}