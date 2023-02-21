package net.tiklab.teston.testplan.mapper;

import net.tiklab.beans.annotation.Mapper;
import net.tiklab.teston.testplan.entity.TestPlanDetailEntity;
import net.tiklab.teston.testplan.model.TestPlanDetail;

@Mapper(source = TestPlanDetail.class,target = TestPlanDetailEntity.class)
public class TestPlanDetailMapper{
}
