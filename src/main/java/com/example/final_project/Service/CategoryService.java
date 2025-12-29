package com.example.final_project.Service;

import com.example.final_project.DTO.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();
    CategoryDto getById(Long id);
    CategoryDto addCategory(CategoryDto categoryDto);
    CategoryDto updateById(Long id, CategoryDto categoryDto);
    void deleteById(Long id);
}
