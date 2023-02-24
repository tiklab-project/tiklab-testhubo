package net.tiklab.teston.test.webscene.instance.entity;

import net.tiklab.dal.jpa.annotation.*;

import java.sql.Timestamp;

@Entity
@Table(name="teston_web_scene_instance")
public class WebSceneInstanceEntity {

    @Id
    @GeneratorValue
    @Column(name = "id",length = 32)
    private String id;

    @Column(name = "web_scene_id")
    private String webSceneId;

    @Column(name = "result")
    private Integer result;

    @Column(name = "step_num")
    private Integer stepNum;

    @Column(name = "pass_num")
    private Integer passNum;

    @Column(name = "fail_num")
    private Integer failNum;

    @Column(name = "pass_rate")
    private String passRate;

    @Column(name = "total_duration")
    private double totalDuration;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "execute_number")
    private Integer executeNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWebSceneId() {
        return webSceneId;
    }

    public void setWebSceneId(String webSceneId) {
        this.webSceneId = webSceneId;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getStepNum() {
        return stepNum;
    }

    public void setStepNum(Integer stepNum) {
        this.stepNum = stepNum;
    }

    public Integer getPassNum() {
        return passNum;
    }

    public void setPassNum(Integer passNum) {
        this.passNum = passNum;
    }

    public Integer getFailNum() {
        return failNum;
    }

    public void setFailNum(Integer failNum) {
        this.failNum = failNum;
    }

    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }

    public double getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(double totalDuration) {
        this.totalDuration = totalDuration;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getExecuteNumber() {
        return executeNumber;
    }

    public void setExecuteNumber(Integer executeNumber) {
        this.executeNumber = executeNumber;
    }
}
