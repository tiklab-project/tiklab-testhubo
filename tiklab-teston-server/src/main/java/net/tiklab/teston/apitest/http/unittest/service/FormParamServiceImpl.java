package net.tiklab.teston.apitest.http.unittest.service;

import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.teston.apitest.http.unittest.dao.FormParamDao;
import net.tiklab.teston.apitest.http.unittest.entity.FormParamEntity;
import net.tiklab.teston.apitest.http.unittest.model.FormParam;
import net.tiklab.teston.apitest.http.unittest.model.FormParamQuery;

import net.tiklab.core.page.Pagination;
import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* FormParamServiceImpl
*/
@Service
public class FormParamServiceImpl implements FormParamService {

    @Autowired
    FormParamDao formParamDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createFormParam(@NotNull @Valid FormParam formParam) {
        FormParamEntity formParamEntity = BeanMapper.map(formParam, FormParamEntity.class);

        return formParamDao.createFormParam(formParamEntity);
    }

    @Override
    public void updateFormParam(@NotNull @Valid FormParam formParam) {
        FormParamEntity formParamEntity = BeanMapper.map(formParam, FormParamEntity.class);

        formParamDao.updateFormParam(formParamEntity);
    }

    @Override
    public void deleteFormParam(@NotNull String id) {
        formParamDao.deleteFormParam(id);
    }

    @Override
    public FormParam findOne(String id) {
        FormParamEntity formParamEntity = formParamDao.findFormParam(id);

        FormParam formParam = BeanMapper.map(formParamEntity, FormParam.class);
        return formParam;
    }

    @Override
    public List<FormParam> findList(List<String> idList) {
        List<FormParamEntity> formParamEntityList =  formParamDao.findFormParamList(idList);

        List<FormParam> formParamList =  BeanMapper.mapList(formParamEntityList,FormParam.class);
        return formParamList;
    }

    @Override
    public FormParam findFormParam(@NotNull String id) {
        FormParam formParam = findOne(id);

        joinTemplate.joinQuery(formParam);
        return formParam;
    }

    @Override
    public List<FormParam> findAllFormParam() {
        List<FormParamEntity> formParamEntityList =  formParamDao.findAllFormParam();

        List<FormParam> formParamList =  BeanMapper.mapList(formParamEntityList,FormParam.class);

        joinTemplate.joinQuery(formParamList);
        return formParamList;
    }

    @Override
    public List<FormParam> findFormParamList(FormParamQuery formParamQuery) {
        List<FormParamEntity> formParamEntityList = formParamDao.findFormParamList(formParamQuery);

        List<FormParam> formParamList = BeanMapper.mapList(formParamEntityList,FormParam.class);

        joinTemplate.joinQuery(formParamList);

        return formParamList;
    }

    @Override
    public Pagination<FormParam> findFormParamPage(FormParamQuery formParamQuery) {

        Pagination<FormParamEntity>  pagination = formParamDao.findFormParamPage(formParamQuery);

        List<FormParam> formParamList = BeanMapper.mapList(pagination.getDataList(),FormParam.class);

        joinTemplate.joinQuery(formParamList);

        return PaginationBuilder.build(pagination,formParamList);
    }
}