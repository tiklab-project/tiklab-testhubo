package net.tiklab.teston.apitest.http.scenetest.mapper;

import net.tiklab.beans.annotation.Mapper;
import net.tiklab.teston.apitest.http.scenetest.entity.ApiSceneCaseEntity;
import net.tiklab.teston.apitest.http.scenetest.model.ApiSceneCase;

@Mapper(source = ApiSceneCase.class,target = ApiSceneCaseEntity.class)
public class ApiSceneCaseMapper {
}
