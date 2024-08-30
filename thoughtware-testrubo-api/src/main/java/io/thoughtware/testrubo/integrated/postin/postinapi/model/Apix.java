package io.thoughtware.testrubo.integrated.postin.postinapi.model;


import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.toolkit.beans.annotation.Mapping;
import io.thoughtware.toolkit.beans.annotation.Mappings;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.toolkit.join.annotation.JoinQuery;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;
import io.thoughtware.user.user.model.User;


@ApiModel
@Join
@Mapper
public class Apix extends BaseModel{

    @ApiProperty(name="id",desc="id")
    private String id;

    @ApiProperty(name="categoryId",desc="categoryId")
    private String categoryId;

    @ApiProperty(name="protocolType",desc="协议类型",required = true)
    private java.lang.String protocolType;

    @ApiProperty(name="path",desc="路径",required = true)
    private java.lang.String path;

    @ApiProperty(name="executor",desc="责任人")
    @Mappings({
            @Mapping(source = "executor.id",target = "executor")
    })
    @JoinQuery(key = "id")
    private User executor;

    @ApiProperty(name="desc",desc="描述")
    private java.lang.String desc;

    @ApiProperty(name="version",desc="版本")
    private java.lang.String version;

    @ApiProperty(name="apiUid",desc="绑定api的id")
    private java.lang.String apiUid;

    @ApiProperty(name="httpApi",desc="httpApi")
    private HttpApi httpApi;

    @ApiProperty(name="node",desc="node")
    private Node node;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getApiUid() {
        return apiUid;
    }

    public void setApiUid(String apiUid) {
        this.apiUid = apiUid;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public HttpApi getHttpApi() {
        return httpApi;
    }

    public void setHttpApi(HttpApi httpApi) {
        this.httpApi = httpApi;
    }


}
