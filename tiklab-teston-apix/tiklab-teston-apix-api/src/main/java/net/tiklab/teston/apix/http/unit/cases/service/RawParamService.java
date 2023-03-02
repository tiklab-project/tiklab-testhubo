package net.tiklab.teston.apix.http.unit.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.teston.apix.http.unit.cases.model.RawParam;
import net.tiklab.teston.apix.http.unit.cases.model.RawParamQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* raw类型 服务接口
*/
public interface RawParamService {

    /**
    * 创建raw
    * @param rawParam
    * @return
    */
    String createRawParam(@NotNull @Valid RawParam rawParam);

    /**
    * 更新raw
    * @param rawParam
    */
    void updateRawParam(@NotNull @Valid RawParam rawParam);

    /**
    * 删除raw
    * @param id
    */
    void deleteRawParam(@NotNull String id);

    RawParam findOne(@NotNull String id);

    List<RawParam> findList(List<String> idList);

    /**
    * 根据id查找raw
    * @param id
    * @return
    */
    RawParam findRawParam(@NotNull String id);

    /**
    * 查找所有raw
    * @return
    */
    List<RawParam> findAllRawParam();

    /**
    * 根据查询参数查询raw列表
    * @param rawParamQuery
    * @return
    */
    List<RawParam> findRawParamList(RawParamQuery rawParamQuery);

    /**
    * 根据查询参数按分页查询raw
    * @param rawParamQuery
    * @return
    */
    Pagination<RawParam> findRawParamPage(RawParamQuery rawParamQuery);

}