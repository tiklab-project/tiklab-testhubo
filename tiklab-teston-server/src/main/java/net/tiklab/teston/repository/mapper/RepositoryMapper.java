package net.tiklab.teston.repository.mapper;

import net.tiklab.beans.annotation.Mapper;
import net.tiklab.teston.repository.entity.RepositoryEntity;
import net.tiklab.teston.repository.model.Repository;

@Mapper(source = Repository.class,target = RepositoryEntity.class)

public class RepositoryMapper {
}
