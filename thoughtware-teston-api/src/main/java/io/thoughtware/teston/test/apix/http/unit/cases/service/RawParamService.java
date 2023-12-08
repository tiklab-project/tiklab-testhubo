package io.thoughtware.teston.test.apix.http.unit.cases.service;

import io.thoughtware.teston.test.apix.http.unit.cases.model.RawParamUnit;
import io.thoughtware.teston.test.apix.http.unit.cases.model.RawParamUnitQuery;
import io.thoughtware.core.page.Pagination;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* raw类型 服务接口
*/
public interface RawParamService {

    /**
    * 创建raw
    * @param rawParamUnit
    * @return
    */
    String createRawParam(@NotNull @Valid RawParamUnit rawParamUnit);

    /**
    * 更新raw
    * @param rawParamUnit
    */
    void updateRawParam(@NotNull @Valid RawParamUnit rawParamUnit);

    /**
    * 删除raw
    * @param id
    */
    void deleteRawParam(@NotNull String id);

    RawParamUnit findOne(@NotNull String id);

    List<RawParamUnit> findList(List<String> idList);

    /**
    * 根据id查找raw
    * @param id
    * @return
    */
    RawParamUnit findRawParam(@NotNull String id);

    /**
    * 查找所有raw
    * @return
    */
    List<RawParamUnit> findAllRawParam();

    /**
    * 根据查询参数查询raw列表
    * @param rawParamUnitQuery
    * @return
    */
    List<RawParamUnit> findRawParamList(RawParamUnitQuery rawParamUnitQuery);

    /**
    * 根据查询参数按分页查询raw
    * @param rawParamUnitQuery
    * @return
    */
    Pagination<RawParamUnit> findRawParamPage(RawParamUnitQuery rawParamUnitQuery);

}