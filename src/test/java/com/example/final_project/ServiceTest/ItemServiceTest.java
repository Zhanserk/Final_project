package com.example.final_project.ServiceTest;

import com.example.final_project.DTO.CategoryDto;
import com.example.final_project.DTO.CountryDto;
import com.example.final_project.DTO.ItemDto;
import com.example.final_project.Service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    private ItemDto createTestItemDto(String name) {
        return new ItemDto(null, name, 1500, "Description",
                new CategoryDto(1L, "Electronics"),
                List.of(new CountryDto(1L, "KZ", "Kazakhstan")));
    }

    @Test
    void addItemTest() {
        ItemDto dto = createTestItemDto("MacBook M3");
        ItemDto saved = itemService.addItem(dto);

        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals("MacBook M3", saved.getName());
    }

    @Test
    void getByIdTest() {
        ItemDto saved = itemService.addItem(createTestItemDto("iPhone 15"));
        ItemDto found = itemService.getById(saved.getId());

        Assertions.assertNotNull(found);
        Assertions.assertEquals(saved.getId(), found.getId());
        Assertions.assertEquals("iPhone 15", found.getName());
    }

    @Test
    void getAllTest() {
        itemService.addItem(createTestItemDto("Item A"));
        itemService.addItem(createTestItemDto("Item B"));

        List<ItemDto> allItems = itemService.getAll();

        Assertions.assertNotNull(allItems);
        Assertions.assertTrue(allItems.size() >= 2);
    }

    @Test
    void updateByIdTest() {
        ItemDto saved = itemService.addItem(createTestItemDto("Original Name"));
        ItemDto updateData = createTestItemDto("Updated Name");
        updateData.setPrice(999);

        ItemDto result = itemService.updateById(saved.getId(), updateData);

        Assertions.assertEquals("Updated Name", result.getName());
        Assertions.assertEquals(999, result.getPrice());
        Assertions.assertEquals("Updated Name", itemService.getById(saved.getId()).getName());
    }

    @Test
    void updateById_NotFound_Test() {
        ItemDto updateData = createTestItemDto("Name");
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            itemService.updateById(-1L, updateData);
        });
    }

    @Test
    void deleteByIdTest() {
        ItemDto saved = itemService.addItem(createTestItemDto("To Delete"));
        Long id = saved.getId();

        boolean isDeleted = itemService.deleteById(id);

        Assertions.assertTrue(isDeleted);
        Assertions.assertNull(itemService.getById(id));
    }
}