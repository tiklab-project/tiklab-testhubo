package net.tiklab.teston.integration.actiontype.mapper;

import net.tiklab.beans.annotation.Mapper;
import net.tiklab.teston.integration.actiontype.entity.ActionTypeEntity;
import net.tiklab.teston.integration.actiontype.model.ActionType;

@Mapper(source = ActionType.class,target = ActionTypeEntity.class)
public class ActionTypeMapper {
}
