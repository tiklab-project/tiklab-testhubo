package net.tiklab.teston.apitest.http.unittest.service;

import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.teston.apitest.http.unittest.dao.RawParamDao;
import net.tiklab.teston.apitest.http.unittest.entity.RawParamEntity;
import net.tiklab.teston.apitest.http.unittest.model.RawParam;
import net.tiklab.teston.apitest.http.unittest.model.RawParamQuery;

import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* RawParamServiceImpl
*/
@Service
public class RawParamServiceImpl implements RawParamService {

    @Autowired
    RawParamDao rawParamDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createRawParam(@NotNull @Valid RawParam rawParam) {
        RawParamEntity rawParamEntity = BeanMapper.map(rawParam, RawParamEntity.class);

        return rawParamDao.createRawParam(rawParamEntity);
    }

    @Override
    public void updateRawParam(@NotNull @Valid RawParam rawParam) {
        RawParamEntity rawParamEntity = BeanMapper.map(rawParam, RawParamEntity.class);

        rawParamDao.updateRawParam(rawParamEntity);
    }

    @Override
    public void deleteRawParam(@NotNull String id) {
        rawParamDao.deleteRawParam(id);
    }

    @Override
    public RawParam findOne(String id) {
        RawParamEntity rawParamEntity = rawParamDao.findRawParam(id);

        RawParam rawParam = BeanMapper.map(rawParamEntity, RawParam.class);
        return rawParam;
    }

    @Override
    public List<RawParam> findList(List<String> idList) {
        List<RawParamEntity> rawParamEntityList =  rawParamDao.findRawParamList(idList);

        List<RawParam> rawParamList =  BeanMapper.mapList(rawParamEntityList,RawParam.class);
        return rawParamList;
    }

    @Override
    public RawParam findRawParam(@NotNull String id) {
        RawParam rawParam = findOne(id);

        joinTemplate.joinQuery(rawParam);
        return rawParam;
    }

    @Override
    public List<RawParam> findAllRawParam() {
        List<RawParamEntity> rawParamEntityList =  rawParamDao.findAllRawParam();

        List<RawParam> rawParamList =  BeanMapper.mapList(rawParamEntityList,RawParam.class);

        joinTemplate.joinQuery(rawParamList);
        return rawParamList;
    }

    @Override
    public List<RawParam> findRawParamList(RawParamQuery rawParamQuery) {
        List<RawParamEntity> rawParamEntityList = rawParamDao.findRawParamList(rawParamQuery);

        List<RawParam> rawParamList = BeanMapper.mapList(rawParamEntityList,RawParam.class);

        joinTemplate.joinQuery(rawParamList);

        return rawParamList;
    }

    @Override
    public Pagination<RawParam> findRawParamPage(RawParamQuery rawParamQuery) {

        Pagination<RawParamEntity>  pagination = rawParamDao.findRawParamPage(rawParamQuery);

        List<RawParam> rawParamList = BeanMapper.mapList(pagination.getDataList(),RawParam.class);

        joinTemplate.joinQuery(rawParamList);


        return PaginationBuilder.build(pagination,rawParamList);
    }
}