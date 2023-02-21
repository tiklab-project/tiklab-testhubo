package net.tiklab.teston.apitest.http.unittest.mapper;

import net.tiklab.beans.annotation.Mapper;
import net.tiklab.teston.apitest.http.unittest.entity.ApiUnitCaseEntity;
import net.tiklab.teston.apitest.http.unittest.model.ApiUnitCase;

@Mapper(source = ApiUnitCase.class,target = ApiUnitCaseEntity.class)
public class ApiUnitCaseMapper {
}
