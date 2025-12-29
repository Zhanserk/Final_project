package com.example.final_project.mapper;




import com.example.final_project.DTO.ItemDto;
import com.example.final_project.Entity.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item item(ItemDto itemDto);
    ItemDto itemDto(Item item);
    List<ItemDto> itemDtos(List<Item> items);
}

