package com.example.final_project.MapperTest;

import com.example.final_project.DTO.CategoryDto;
import com.example.final_project.Entity.Category;
import com.example.final_project.mapper.CategoryMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class CategoryMapperTest {
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void convertEntityToDtoTest() {
        Category category = new Category(1L, "Phone");

        CategoryDto categoryDto = categoryMapper.categoryDto(category);

        Assertions.assertNotNull(categoryDto);

        Assertions.assertNotNull(categoryDto.getId());
        Assertions.assertNotNull(categoryDto.getName());

        Assertions.assertEquals(category.getId(), categoryDto.getId());
        Assertions.assertEquals(category.getName(), categoryDto.getName());

    }

    @Test
    void convertDtoToEntityTest() {
        CategoryDto categoryDto = new CategoryDto(1L, "Phone");

        Category category = categoryMapper.category(categoryDto);

        Assertions.assertNotNull(category);

        Assertions.assertNotNull(category.getId());
        Assertions.assertNotNull(category.getName());

        Assertions.assertEquals(categoryDto.getId(), category.getId());
        Assertions.assertEquals(categoryDto.getName(), category.getName());

    }

    @Test
    void convertEntityListToDtoListTest() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Phone"));
        categories.add(new Category(2L, "Laptop"));
        categories.add(new Category(3L, "Headphones"));

        List<CategoryDto> categoriesDto = categoryMapper.categoryDtos(categories);

        Assertions.assertNotNull(categoriesDto);

        Assertions.assertNotEquals(0, categoriesDto.size());

        Assertions.assertEquals(categories.size(), categoriesDto.size());

        for(int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);

            CategoryDto categoryDto = categoriesDto.get(i);

            Assertions.assertNotNull(categoryDto);

            Assertions.assertNotNull(categoryDto.getId());
            Assertions.assertNotNull(categoryDto.getName());

            Assertions.assertEquals(category.getId(), categoryDto.getId());
            Assertions.assertEquals(category.getName(), categoryDto.getName());

        }
    }
}