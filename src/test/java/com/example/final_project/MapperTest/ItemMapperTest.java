package com.example.final_project.MapperTest;

import com.example.final_project.DTO.CategoryDto;
import com.example.final_project.DTO.CountryDto;
import com.example.final_project.DTO.ItemDto;
import com.example.final_project.Entity.Category;
import com.example.final_project.Entity.Country;
import com.example.final_project.Entity.Item;
import com.example.final_project.mapper.ItemMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ItemMapperTest {
    @Autowired
    private ItemMapper itemMapper;

    @Test
    void convertEntitytoDTO(){
        Item itemEntity = new Item(1L, "iPhone 15 Pro",1000, "Powerful Smartphone", new Category(1L, "Smartphone"), List.of(new Country(1L, "KZ", "Kazakhstan")));
        ItemDto itemDto= itemMapper.itemDto(itemEntity);
        Assertions.assertNotNull(itemDto);

        Assertions.assertNotNull(itemDto.getId());
        Assertions.assertNotNull(itemDto.getName());
        Assertions.assertNotNull(itemDto.getDescription());
        Assertions.assertNotNull(itemDto.getPrice());
        Assertions.assertNotNull(itemDto.getCategory());
        Assertions.assertNotNull(itemDto.getCountries());

        Assertions.assertEquals(itemEntity.getId(),itemDto.getId());
        Assertions.assertEquals(itemEntity.getName(),itemDto.getName());
        Assertions.assertEquals(itemEntity.getDescription(),itemDto.getDescription());
        Assertions.assertEquals(itemEntity.getPrice(),itemDto.getPrice());
        Assertions.assertEquals(itemEntity.getCategory().getId(),itemDto.getCategory().getId());
        Assertions.assertEquals(itemEntity.getCategory().getName(),itemDto.getCategory().getName());
        Assertions.assertEquals(itemEntity.getCountries().size(), itemDto.getCountries().size());
    }
    @Test
    void ConvertDtoEntity(){

        ItemDto itemDto = new ItemDto(1L, "iPhone 15 Pro", 1000, "Powerful Smartphone", new CategoryDto(1L, "Smartphone"), List.of(new CountryDto(1L, "KZ", "Kazakhstan")));
        Item item = itemMapper.item(itemDto);

        Assertions.assertNotNull(item);

        Assertions.assertNotNull(item.getId());
        Assertions.assertNotNull(item.getName());
        Assertions.assertNotNull(item.getDescription());
        Assertions.assertNotNull(item.getPrice());
        Assertions.assertNotNull(item.getCategory());
        Assertions.assertNotNull(item.getCountries());

        Assertions.assertEquals(item.getId(),itemDto.getId());
        Assertions.assertEquals(item.getName(),itemDto.getName());
        Assertions.assertEquals(item.getDescription(),itemDto.getDescription());
        Assertions.assertEquals(item.getPrice(),itemDto.getPrice());
        Assertions.assertEquals(item.getCategory().getId(),itemDto.getCategory().getId());
        Assertions.assertEquals(item.getCategory().getName(),itemDto.getCategory().getName());
        Assertions.assertEquals(item.getCountries().size(),itemDto.getCountries().size());
    }
    @Test
    void convertListEntitytoDto(){
        List<Item> itemEntityList = new ArrayList<>();
        itemEntityList.add(new Item(1L, "iPhone 15 Pro", 1000, "Powerful Smartphone", new Category(1L, "Smartphone"), List.of(new Country(1L, "KZ", "Kazakhstan"))));
        itemEntityList.add(new Item(2L, "Galaxy S24", 900, "Flagship Android", new Category(1L, "Smartphone"), List.of(new Country(2L, "US", "United States"))));
        itemEntityList.add(new Item(3L, "MacBook Air",1200 , "Lightweight laptop", new Category(2L, "Laptop"), List.of(new Country(3L, "JP", "Japan"))));

        List<ItemDto> itemDtoList = itemMapper.itemDtos(itemEntityList);
        Assertions.assertNotNull(itemDtoList);
        Assertions.assertNotEquals(0, itemDtoList.size());

        Assertions.assertEquals(itemEntityList.size(), itemDtoList.size());
        for (int i = 0;i<itemDtoList.size();i++){
            Item item = itemEntityList.get(i);
            ItemDto itemDto = itemDtoList.get(i);
            Assertions.assertNotNull(itemDto.getName());
            Assertions.assertNotNull(itemDto.getId());
            Assertions.assertNotNull(itemDto.getDescription());
            Assertions.assertNotNull(itemDto.getPrice());
            Assertions.assertNotNull(itemDto.getCategory());
            Assertions.assertNotNull(itemDto.getCountries());

            Assertions.assertEquals(item.getId(),itemDto.getId());
            Assertions.assertEquals(item.getName(),itemDto.getName());
            Assertions.assertEquals(item.getDescription(),itemDto.getDescription());
            Assertions.assertEquals(item.getPrice(),itemDto.getPrice());
            Assertions.assertEquals(item.getCategory().getId(),itemDto.getCategory().getId());
            Assertions.assertEquals(item.getCategory().getName(),itemDto.getCategory().getName());
            Assertions.assertEquals(item.getCountries().size(),itemDto.getCountries().size());


        }

    }


}
