package com.example.final_project.Service;

import com.example.final_project.DTO.ItemDto;

import java.util.List;

public interface ItemService {
    List<ItemDto> getAll();
    ItemDto getById(Long id);
    ItemDto addItem(ItemDto itemDto);
    ItemDto updateById(Long id, ItemDto itemDto);
    boolean deleteById(Long id);
}
