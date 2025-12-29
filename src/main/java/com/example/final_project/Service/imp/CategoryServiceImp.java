package com.example.final_project.Service.imp;

import com.example.final_project.DTO.CategoryDto;
import com.example.final_project.Entity.Category;
import com.example.final_project.Repository.CategoryRepository;
import com.example.final_project.Service.CategoryService;
import com.example.final_project.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public List<CategoryDto> getAll() {
        return categoryMapper.categoryDtos(categoryRepository.findAll());
    }

    @Override
    public CategoryDto getById(Long id) {
        return categoryMapper.categoryDto(categoryRepository.findById(id).orElse(null));
    }

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        return categoryMapper.categoryDto(categoryRepository.save(categoryMapper.category(categoryDto)));
    }

    @Override
    public CategoryDto updateById(Long id, CategoryDto categoryDto) {
        Category category = categoryMapper.category(categoryDto);
        Category updateCategory = categoryRepository.findById(id).orElseThrow(() ->new NoSuchElementException("Not found"));
        updateCategory.setName(category.getName());
        return categoryMapper.categoryDto(categoryRepository.save(updateCategory));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
