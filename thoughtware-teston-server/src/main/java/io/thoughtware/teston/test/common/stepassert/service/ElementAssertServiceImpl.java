package io.thoughtware.teston.test.common.stepassert.service;

import io.thoughtware.toolkit.beans.BeanMapper;
import io.thoughtware.toolkit.join.JoinTemplate;
import io.thoughtware.teston.test.common.stepassert.model.ElementAssert;
import io.thoughtware.teston.test.common.stepassert.dao.ElementAssertDao;
import io.thoughtware.teston.test.common.stepassert.entity.ElementAssertEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
* 元素断言 服务
*/
@Service
public class ElementAssertServiceImpl implements ElementAssertService {

    @Autowired
    ElementAssertDao elementAssertDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createElementAssert(@NotNull @Valid ElementAssert elementAssert) {
        ElementAssertEntity elementAssertEntity = BeanMapper.map(elementAssert, ElementAssertEntity.class);

        return elementAssertDao.createElementAssert(elementAssertEntity);
    }

    @Override
    public void updateElementAssert(@NotNull @Valid ElementAssert elementAssert) {

        ElementAssertEntity elementAssertEntity = BeanMapper.map(elementAssert, ElementAssertEntity.class);

        elementAssertDao.updateElementAssert(elementAssertEntity);
    }

    @Override
    public void deleteElementAssert(@NotNull String id) {

        elementAssertDao.deleteElementAssert(id);
    }

    @Override
    public ElementAssert findOne(String id) {
        ElementAssertEntity elementAssertEntity = elementAssertDao.findElementAssert(id);

        ElementAssert elementAssert = BeanMapper.map(elementAssertEntity, ElementAssert.class);
        return elementAssert;
    }

    @Override
    public ElementAssert findElementAssert(@NotNull String id) {
        ElementAssert elementAssert = findOne(id);

        joinTemplate.joinQuery(elementAssert);

        return elementAssert;
    }


}