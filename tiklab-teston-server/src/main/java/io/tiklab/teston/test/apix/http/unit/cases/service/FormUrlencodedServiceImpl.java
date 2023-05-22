package io.tiklab.teston.test.apix.http.unit.cases.service;



import io.tiklab.teston.test.apix.http.unit.cases.dao.FormUrlencodedDao;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.beans.BeanMapper;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.apix.http.unit.cases.entity.FormUrlEncodedEntity;
import io.tiklab.teston.test.apix.http.unit.cases.model.FormUrlEncoded;
import io.tiklab.teston.test.apix.http.unit.cases.model.FormUrlencodedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* form-urlencoded 服务
*/
@Service
public class FormUrlencodedServiceImpl implements FormUrlencodedService {

    @Autowired
    FormUrlencodedDao formUrlencodedDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createFormUrlencoded(@NotNull @Valid FormUrlEncoded formUrlencoded) {
        FormUrlEncodedEntity formUrlencodedEntity = BeanMapper.map(formUrlencoded, FormUrlEncodedEntity.class);

        return formUrlencodedDao.createFormUrlencoded(formUrlencodedEntity);
    }

    @Override
    public void updateFormUrlencoded(@NotNull @Valid FormUrlEncoded formUrlencoded) {
        FormUrlEncodedEntity formUrlencodedEntity = BeanMapper.map(formUrlencoded, FormUrlEncodedEntity.class);

        formUrlencodedDao.updateFormUrlencoded(formUrlencodedEntity);
    }

    @Override
    public void deleteFormUrlencoded(@NotNull String id) {
        formUrlencodedDao.deleteFormUrlencoded(id);
    }

    @Override
    public FormUrlEncoded findOne(String id) {
        FormUrlEncodedEntity formUrlencodedEntity = formUrlencodedDao.findFormUrlencoded(id);

        FormUrlEncoded formUrlencoded = BeanMapper.map(formUrlencodedEntity, FormUrlEncoded.class);
        return formUrlencoded;
    }

    @Override
    public List<FormUrlEncoded> findList(List<String> idList) {
        List<FormUrlEncodedEntity> formUrlEncodedEntityList =  formUrlencodedDao.findFormUrlencodedList(idList);

        List<FormUrlEncoded> formUrlEncodedList =  BeanMapper.mapList(formUrlEncodedEntityList, FormUrlEncoded.class);
        return formUrlEncodedList;
    }

    @Override
    public FormUrlEncoded findFormUrlencoded(@NotNull String id) {
        FormUrlEncoded formUrlencoded = findOne(id);

        joinTemplate.joinQuery(formUrlencoded);

        return formUrlencoded;
    }

    @Override
    public List<FormUrlEncoded> findAllFormUrlencoded() {
        List<FormUrlEncodedEntity> formUrlEncodedEntityList =  formUrlencodedDao.findAllFormUrlencoded();

        List<FormUrlEncoded> formUrlEncodedList =  BeanMapper.mapList(formUrlEncodedEntityList, FormUrlEncoded.class);

        joinTemplate.joinQuery(formUrlEncodedList);

        return formUrlEncodedList;
    }

    @Override
    public List<FormUrlEncoded> findFormUrlencodedList(FormUrlencodedQuery formUrlencodedQuery) {
        List<FormUrlEncodedEntity> formUrlEncodedEntityList = formUrlencodedDao.findFormUrlencodedList(formUrlencodedQuery);

        List<FormUrlEncoded> formUrlEncodedList = BeanMapper.mapList(formUrlEncodedEntityList, FormUrlEncoded.class);

        joinTemplate.joinQuery(formUrlEncodedList);

        return formUrlEncodedList;
    }

    @Override
    public Pagination<FormUrlEncoded> findFormUrlencodedPage(FormUrlencodedQuery formUrlencodedQuery) {
        Pagination<FormUrlEncodedEntity>  pagination = formUrlencodedDao.findFormUrlencodedPage(formUrlencodedQuery);

        List<FormUrlEncoded> formUrlEncodedList = BeanMapper.mapList(pagination.getDataList(), FormUrlEncoded.class);

        joinTemplate.joinQuery(formUrlEncodedList);

        return PaginationBuilder.build(pagination, formUrlEncodedList);
    }
}