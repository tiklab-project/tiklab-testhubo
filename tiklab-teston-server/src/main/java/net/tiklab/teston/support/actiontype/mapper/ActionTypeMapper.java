package net.tiklab.teston.support.actiontype.mapper;

import net.tiklab.beans.annotation.Mapper;
import net.tiklab.teston.support.actiontype.entity.ActionTypeEntity;
import net.tiklab.teston.support.actiontype.model.ActionType;

@Mapper(source = ActionType.class,target = ActionTypeEntity.class)
public class ActionTypeMapper {
}
