package io.tiklab.teston.test.func.service;

import io.tiklab.beans.BeanMapper;
import io.tiklab.core.page.Pagination;
import io.tiklab.core.page.PaginationBuilder;
import io.tiklab.join.JoinTemplate;
import io.tiklab.teston.test.func.dao.FuncUnitStepDao;
import io.tiklab.teston.test.func.entity.FuncUnitStepEntity;
import io.tiklab.teston.test.func.model.FuncUnitStep;
import io.tiklab.teston.test.func.model.FuncUnitStepQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
* 功能用例下步骤 服务
*/
@Service
public class FuncUnitStepServiceImpl implements FuncUnitStepService {

    @Autowired
    FuncUnitStepDao funcUnitStepDao;

    @Autowired
    JoinTemplate joinTemplate;

    @Override
    public String createFuncUnitStep(@NotNull @Valid FuncUnitStep funcUnitStep) {
        FuncUnitStepEntity funcUnitStepEntity = BeanMapper.map(funcUnitStep, FuncUnitStepEntity.class);
        funcUnitStepEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        FuncUnitStepQuery funcUnitStepQuery = new FuncUnitStepQuery();
        funcUnitStepQuery.setFuncUnitId(funcUnitStep.getFuncUnitId());
        List<FuncUnitStepEntity> funcUnitStepList = funcUnitStepDao.findFuncUnitStepList(funcUnitStepQuery);
        if(funcUnitStepList!= null && funcUnitStepList.size() > 0){
            funcUnitStepEntity.setSort(funcUnitStepList.size());
        }else {
            funcUnitStepEntity.setSort(0);
        }

        return funcUnitStepDao.createFuncUnitStep(funcUnitStepEntity);
    }

    @Override
    public void updateFuncUnitStep(@NotNull @Valid FuncUnitStep funcUnitStep) {

        // 如果oldSort为空，则不是改变位置
        if(funcUnitStep.getOldSort()!=null){
            Integer curSort = funcUnitStep.getSort();
            Integer oldSort = funcUnitStep.getOldSort();

            FuncUnitStepQuery funcUnitStepQuery = new FuncUnitStepQuery();
            funcUnitStepQuery.setFuncUnitId(funcUnitStep.getFuncUnitId());
            List<FuncUnitStepEntity> funcUnitStepList = funcUnitStepDao.findFuncUnitStepList(funcUnitStepQuery);

            //如果当前排序大于源排序，中间项的排序都得减1
            if(curSort > oldSort){
                for(int i=oldSort+1;i<=curSort;i++){
                    FuncUnitStepEntity funcUnitStepEntity1 = funcUnitStepList.get(i);
                    funcUnitStepEntity1.setSort(funcUnitStepEntity1.getSort()-1);
                    funcUnitStepDao.updateFuncUnitStep(funcUnitStepEntity1);
                }
            }

            //如果当前排序小于源排序，中间项的排序都得加1
            if(curSort < oldSort){
                for (int i=oldSort-1;i>=curSort;i--){
                    FuncUnitStepEntity funcUnitStepEntity1 = funcUnitStepList.get(i);
                    funcUnitStepEntity1.setSort(funcUnitStepEntity1.getSort()+1);
                    funcUnitStepDao.updateFuncUnitStep(funcUnitStepEntity1);
                }
            }
        }

        FuncUnitStepEntity funcUnitStepEntity = BeanMapper.map(funcUnitStep, FuncUnitStepEntity.class);
        funcUnitStepEntity.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        funcUnitStepDao.updateFuncUnitStep(funcUnitStepEntity);
    }

    @Override
    public void deleteFuncUnitStep(@NotNull String id) {

        FuncUnitStepEntity funcUnitStep = funcUnitStepDao.findFuncUnitStep(id);
        if(funcUnitStep== null){return;}
        Integer sort = funcUnitStep.getSort();

        FuncUnitStepQuery funcUnitStepQuery = new FuncUnitStepQuery();
        funcUnitStepQuery.setFuncUnitId(funcUnitStep.getFuncUnitId());
        List<FuncUnitStepEntity> funcUnitStepList = funcUnitStepDao.findFuncUnitStepList(funcUnitStepQuery);
        for(FuncUnitStepEntity funcUnitStepEntity:funcUnitStepList){
            if(funcUnitStepEntity.getSort() > sort){
                funcUnitStepEntity.setSort(funcUnitStepEntity.getSort()-1);
                funcUnitStepDao.updateFuncUnitStep(funcUnitStepEntity);
            }

            if(funcUnitStepEntity.getSort().equals(sort)){
                funcUnitStepDao.deleteFuncUnitStep(id);
            }
        }
    }

    @Override
    public FuncUnitStep findOne(String id) {
        FuncUnitStepEntity funcUnitStepEntity = funcUnitStepDao.findFuncUnitStep(id);

        FuncUnitStep funcUnitStep = BeanMapper.map(funcUnitStepEntity, FuncUnitStep.class);
        return funcUnitStep;
    }

    @Override
    public List<FuncUnitStep> findList(List<String> idList) {
        List<FuncUnitStepEntity> funcUnitStepEntityList =  funcUnitStepDao.findFuncUnitStepList(idList);

        List<FuncUnitStep> funcUnitStepList =  BeanMapper.mapList(funcUnitStepEntityList,FuncUnitStep.class);
        return funcUnitStepList;
    }

    @Override
    public FuncUnitStep findFuncUnitStep(@NotNull String id) {
        FuncUnitStep funcUnitStep = findOne(id);

        joinTemplate.joinQuery(funcUnitStep);

        return funcUnitStep;
    }

    @Override
    public List<FuncUnitStep> findAllFuncUnitStep() {
        List<FuncUnitStepEntity> funcUnitStepEntityList =  funcUnitStepDao.findAllFuncUnitStep();

        List<FuncUnitStep> funcUnitStepList =  BeanMapper.mapList(funcUnitStepEntityList,FuncUnitStep.class);

        joinTemplate.joinQuery(funcUnitStepList);

        return funcUnitStepList;
    }

    @Override
    public List<FuncUnitStep> findFuncUnitStepList(FuncUnitStepQuery funcUnitStepQuery) {
        List<FuncUnitStepEntity> funcUnitStepEntityList = funcUnitStepDao.findFuncUnitStepList(funcUnitStepQuery);

        List<FuncUnitStep> funcUnitStepList = BeanMapper.mapList(funcUnitStepEntityList,FuncUnitStep.class);

        joinTemplate.joinQuery(funcUnitStepList);

        return funcUnitStepList;
    }

    @Override
    public Pagination<FuncUnitStep> findFuncUnitStepPage(FuncUnitStepQuery funcUnitStepQuery) {
        Pagination<FuncUnitStepEntity>  pagination = funcUnitStepDao.findFuncUnitStepPage(funcUnitStepQuery);

        List<FuncUnitStep> funcUnitStepList = BeanMapper.mapList(pagination.getDataList(),FuncUnitStep.class);

        joinTemplate.joinQuery(funcUnitStepList);

        return PaginationBuilder.build(pagination,funcUnitStepList);
    }
}