package io.thoughtware.testrubo.test.common.stepcommon.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.testrubo.test.apix.http.scene.cases.model.ApiSceneStep;
import io.thoughtware.testrubo.test.apix.http.scene.execute.model.ApiSceneTestRequest;
import io.thoughtware.testrubo.test.app.scene.cases.model.AppSceneStep;
import io.thoughtware.testrubo.test.common.ifjudgment.model.IfJudgment;
import io.thoughtware.testrubo.test.func.model.FuncUnitStep;
import io.thoughtware.testrubo.test.web.scene.cases.model.WebSceneStep;

import java.sql.Timestamp;

/**
 * 公共步骤 模型
 */
@ApiModel
@Mapper
@Join
public class StepCommon extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="caseId",desc="caseId")
    private String caseId;

    @ApiProperty(name="type",desc="步骤类型")
    private String type;

    @ApiProperty(name="createTime",desc="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Timestamp createTime;

    @ApiProperty(name="sort",desc="排序")
    private Integer sort;

    @ApiProperty(name="oldSort",desc="原排序")
    private Integer oldSort;


    private ApiSceneStep apiSceneStep;
    private ApiSceneTestRequest apiSceneTestRequest;
    private WebSceneStep webSceneStep;
    private AppSceneStep appSceneStep;
    private FuncUnitStep funcUnitStep;
    private IfJudgment ifJudgment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getOldSort() {
        return oldSort;
    }

    public void setOldSort(Integer oldSort) {
        this.oldSort = oldSort;
    }

    public WebSceneStep getWebSceneStep() {
        return webSceneStep;
    }

    public void setWebSceneStep(WebSceneStep webSceneStep) {
        this.webSceneStep = webSceneStep;
    }

    public AppSceneStep getAppSceneStep() {
        return appSceneStep;
    }

    public void setAppSceneStep(AppSceneStep appSceneStep) {
        this.appSceneStep = appSceneStep;
    }

    public ApiSceneStep getApiSceneStep() {
        return apiSceneStep;
    }

    public void setApiSceneStep(ApiSceneStep apiSceneStep) {
        this.apiSceneStep = apiSceneStep;
    }

    public FuncUnitStep getFuncUnitStep() {
        return funcUnitStep;
    }

    public void setFuncUnitStep(FuncUnitStep funcUnitStep) {
        this.funcUnitStep = funcUnitStep;
    }

    public IfJudgment getIfJudgment() {
        return ifJudgment;
    }

    public void setIfJudgment(IfJudgment ifJudgment) {
        this.ifJudgment = ifJudgment;
    }

    public ApiSceneTestRequest getApiSceneTestRequest() {
        return apiSceneTestRequest;
    }

    public void setApiSceneTestRequest(ApiSceneTestRequest apiSceneTestRequest) {
        this.apiSceneTestRequest = apiSceneTestRequest;
    }
}
