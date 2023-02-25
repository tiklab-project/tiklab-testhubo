package net.tiklab.testonapix.http.unit.cases.service;

import net.tiklab.core.page.Pagination;
import net.tiklab.testonapix.http.unit.cases.model.RawParam;
import net.tiklab.testonapix.http.unit.cases.model.RawParamQuery;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RawParamService
*/
public interface RawParamService {

    /**
    * 创建
    * @param rawParam
    * @return
    */
    String createRawParam(@NotNull @Valid RawParam rawParam);

    /**
    * 更新
    * @param rawParam
    */
    void updateRawParam(@NotNull @Valid RawParam rawParam);

    /**
    * 删除
    * @param id
    */
    void deleteRawParam(@NotNull String id);

    RawParam findOne(@NotNull String id);

    List<RawParam> findList(List<String> idList);

    /**
    * 查找
    * @param id
    * @return
    */
    RawParam findRawParam(@NotNull String id);

    /**
    * 查找所有
    * @return
    */
    List<RawParam> findAllRawParam();

    /**
    * 查询列表
    * @param rawParamQuery
    * @return
    */
    List<RawParam> findRawParamList(RawParamQuery rawParamQuery);

    /**
    * 按分页查询
    * @param rawParamQuery
    * @return
    */
    Pagination<RawParam> findRawParamPage(RawParamQuery rawParamQuery);

}