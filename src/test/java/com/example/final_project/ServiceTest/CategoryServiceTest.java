package com.example.final_project.ServiceTest;

import com.example.final_project.DTO.CategoryDto;
import com.example.final_project.Service.CategoryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    private CategoryDto createCategoryDto(String name) {
        return CategoryDto.builder()
                .name(name)
                .build();
    }

    @Test
    void addCategoryTest() {
        CategoryDto dto = createCategoryDto("Electronics");
        CategoryDto saved = categoryService.addCategory(dto);

        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals("Electronics", saved.getName());
    }

    @Test
    void getByIdTest() {
        CategoryDto saved = categoryService.addCategory(createCategoryDto("Laptops"));
        CategoryDto found = categoryService.getById(saved.getId());

        Assertions.assertNotNull(found);
        Assertions.assertEquals("Laptops", found.getName());
    }

    @Test
    void getAllTest() {
        categoryService.addCategory(createCategoryDto("Category A"));
        categoryService.addCategory(createCategoryDto("Category B"));

        List<CategoryDto> categories = categoryService.getAll();

        Assertions.assertNotNull(categories);
        Assertions.assertTrue(categories.size() >= 2);
    }

    @Test
    void updateByIdTest() {
        CategoryDto saved = categoryService.addCategory(createCategoryDto("Old Category"));
        CategoryDto updateData = createCategoryDto("New Category");

        CategoryDto updated = categoryService.updateById(saved.getId(), updateData);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals("New Category", updated.getName());
        Assertions.assertEquals("New Category", categoryService.getById(saved.getId()).getName());
    }

    @Test
    void deleteByIdTest() {
        CategoryDto saved = categoryService.addCategory(createCategoryDto("To Delete"));
        Long id = saved.getId();

        categoryService.deleteById(id);

        Assertions.assertNull(categoryService.getById(id));
    }
}
