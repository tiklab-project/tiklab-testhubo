package io.thoughtware.testrubo.integrated.postin.postinapi.model;

import io.thoughtware.toolkit.beans.annotation.Mapper;
import io.thoughtware.core.BaseModel;
import io.thoughtware.toolkit.join.annotation.Join;
import io.thoughtware.postin.annotation.ApiModel;
import io.thoughtware.postin.annotation.ApiProperty;

import javax.validation.constraints.NotNull;

/**
 * raw 模型
 */
@ApiModel
@Join
@Mapper
public class RawParam extends BaseModel {

    @ApiProperty(name="id",desc="唯一标识，非自动生成")
    private String id;

    @ApiProperty(name="apiId",desc="所属接口")
    private String apiId;

    @NotNull
    @ApiProperty(name = "type",desc = "raw中类型",required = true)
    private String type;

    @NotNull
    @ApiProperty(name="raw",desc="raw自定义文本",required = true)
    private String raw;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
