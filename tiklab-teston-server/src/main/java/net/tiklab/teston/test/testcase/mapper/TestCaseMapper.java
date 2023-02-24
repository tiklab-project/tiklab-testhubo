package net.tiklab.teston.test.testcase.mapper;

import net.tiklab.beans.annotation.Mapper;
import net.tiklab.teston.test.testcase.entity.TestCaseEntity;
import net.tiklab.teston.test.testcase.model.TestCase;

@Mapper(source = TestCase.class,target = TestCaseEntity.class)
public class TestCaseMapper {
}
