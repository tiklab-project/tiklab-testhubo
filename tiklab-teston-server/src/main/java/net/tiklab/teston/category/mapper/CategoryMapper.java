package net.tiklab.teston.category.mapper;

import net.tiklab.beans.annotation.Mapper;
import net.tiklab.teston.category.entity.CategoryEntity;
import net.tiklab.teston.category.model.Category;

@Mapper(source = Category.class,target = CategoryEntity.class)
public class CategoryMapper {
}
