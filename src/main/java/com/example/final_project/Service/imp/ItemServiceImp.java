package com.example.final_project.Service.imp;

import com.example.final_project.DTO.ItemDto;
import com.example.final_project.Entity.Item;
import com.example.final_project.Repository.ItemRepository;
import com.example.final_project.Service.ItemService;
import com.example.final_project.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ItemServiceImp implements ItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;


    @Override
    public List<ItemDto> getAll() {
        return itemMapper.itemDtos(itemRepository.findAll());
    }

    @Override
    public ItemDto getById(Long id) {
        return itemMapper.itemDto(itemRepository.findById(id).orElse(null));
    }

    @Override
    public ItemDto addItem(ItemDto itemDto) {
        return itemMapper.itemDto(itemRepository.save(itemMapper.item(itemDto)));
    }

    @Override
    public ItemDto updateById(Long id, ItemDto itemDto) {
        Item item = itemMapper.item(itemDto);
        Item updateItem = itemRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Not found"));
        updateItem.setName(item.getName());
        updateItem.setDescription(item.getDescription());
        updateItem.setPrice(item.getPrice());
        updateItem.setCategory(item.getCategory());
        updateItem.setCountries(item.getCountries());
        itemRepository.save(updateItem);
        return itemMapper.itemDto(updateItem);
    }

    @Override
    public boolean deleteById(Long id) {
        itemRepository.deleteById(id);

        Item item = itemRepository.findById(id).orElse(null);

        return item == null;
    }
}
