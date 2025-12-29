package com.example.final_project.mapper;

import com.example.final_project.DTO.CategoryDto;
import com.example.final_project.Entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto categoryDto(Category category);
    Category category(CategoryDto categoryDto);
    List<CategoryDto> categoryDtos(List<Category> categories);
}
