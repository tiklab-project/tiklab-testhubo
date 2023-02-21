package net.tiklab.teston.testplan.mapper;

import net.tiklab.beans.annotation.Mapper;
import net.tiklab.teston.testplan.entity.TestPlanEntity;
import net.tiklab.teston.testplan.model.TestPlan;

@Mapper(source = TestPlan.class,target = TestPlanEntity.class)
public class TestPlanMapper{
}
