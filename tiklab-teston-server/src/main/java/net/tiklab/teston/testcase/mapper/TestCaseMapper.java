package net.tiklab.teston.testcase.mapper;

import net.tiklab.beans.annotation.Mapper;
import net.tiklab.teston.testcase.entity.TestCaseEntity;
import net.tiklab.teston.testcase.model.TestCase;

@Mapper(source = TestCase.class,target = TestCaseEntity.class)
public class TestCaseMapper {
}
