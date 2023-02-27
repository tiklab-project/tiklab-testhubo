package net.tiklab.teston.apix.http.unit.http.cases.service;



import net.tiklab.core.page.Pagination;
import net.tiklab.core.page.PaginationBuilder;
import net.tiklab.beans.BeanMapper;
import net.tiklab.join.JoinTemplate;
import net.tiklab.teston.apix.http.unit.cases.model.FormUrlencoded;
import net.tiklab.teston.apix.http.unit.cases.model.FormUrlencodedQuery;
import net.tiklab.teston.apix.http.unit.cases.service.FormUrlencodedService;
import net.tiklab.teston.apix.http.unit.http.cases.dao.FormUrlencodedDao;
import net.tiklab.teston.apix.http.unit.http.cases.entity.FormUrlencodedEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
* FormUrlencodedServiceImpl
*/
@Service
public class FormUrlencodedServiceImpl implements FormUrlencodedService {

    @Autowired
    FormUrlencodedDao formUrlencodedDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createFormUrlencoded(@NotNull @Valid FormUrlencoded formUrlencoded) {
        FormUrlencodedEntity formUrlencodedEntity = BeanMapper.map(formUrlencoded, FormUrlencodedEntity.class);

        return formUrlencodedDao.createFormUrlencoded(formUrlencodedEntity);
    }

    @Override
    public void updateFormUrlencoded(@NotNull @Valid FormUrlencoded formUrlencoded) {
        FormUrlencodedEntity formUrlencodedEntity = BeanMapper.map(formUrlencoded, FormUrlencodedEntity.class);

        formUrlencodedDao.updateFormUrlencoded(formUrlencodedEntity);
    }

    @Override
    public void deleteFormUrlencoded(@NotNull String id) {
        formUrlencodedDao.deleteFormUrlencoded(id);
    }

    @Override
    public FormUrlencoded findOne(String id) {
        FormUrlencodedEntity formUrlencodedEntity = formUrlencodedDao.findFormUrlencoded(id);

        FormUrlencoded formUrlencoded = BeanMapper.map(formUrlencodedEntity, FormUrlencoded.class);
        return formUrlencoded;
    }

    @Override
    public List<FormUrlencoded> findList(List<String> idList) {
        List<FormUrlencodedEntity> formUrlencodedEntityList =  formUrlencodedDao.findFormUrlencodedList(idList);

        List<FormUrlencoded> formUrlencodedList =  BeanMapper.mapList(formUrlencodedEntityList,FormUrlencoded.class);
        return formUrlencodedList;
    }

    @Override
    public FormUrlencoded findFormUrlencoded(@NotNull String id) {
        FormUrlencoded formUrlencoded = findOne(id);

        joinTemplate.joinQuery(formUrlencoded);

        return formUrlencoded;
    }

    @Override
    public List<FormUrlencoded> findAllFormUrlencoded() {
        List<FormUrlencodedEntity> formUrlencodedEntityList =  formUrlencodedDao.findAllFormUrlencoded();

        List<FormUrlencoded> formUrlencodedList =  BeanMapper.mapList(formUrlencodedEntityList,FormUrlencoded.class);

        joinTemplate.joinQuery(formUrlencodedList);

        return formUrlencodedList;
    }

    @Override
    public List<FormUrlencoded> findFormUrlencodedList(FormUrlencodedQuery formUrlencodedQuery) {
        List<FormUrlencodedEntity> formUrlencodedEntityList = formUrlencodedDao.findFormUrlencodedList(formUrlencodedQuery);

        List<FormUrlencoded> formUrlencodedList = BeanMapper.mapList(formUrlencodedEntityList,FormUrlencoded.class);

        joinTemplate.joinQuery(formUrlencodedList);

        return formUrlencodedList;
    }

    @Override
    public Pagination<FormUrlencoded> findFormUrlencodedPage(FormUrlencodedQuery formUrlencodedQuery) {
        Pagination<FormUrlencodedEntity>  pagination = formUrlencodedDao.findFormUrlencodedPage(formUrlencodedQuery);

        List<FormUrlencoded> formUrlencodedList = BeanMapper.mapList(pagination.getDataList(),FormUrlencoded.class);

        joinTemplate.joinQuery(formUrlencodedList);

        return PaginationBuilder.build(pagination,formUrlencodedList);
    }
}