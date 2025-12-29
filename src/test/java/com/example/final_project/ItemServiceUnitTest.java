package com.example.final_project.ServiceTest;

import com.example.final_project.DTO.ItemDto;
import com.example.final_project.Entity.Item;
import com.example.final_project.Repository.ItemRepository;
import com.example.final_project.Service.imp.ItemServiceImp;
import com.example.final_project.mapper.ItemMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceUnitTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private ItemServiceImp itemService;

    @Test
    void getAllTest() {
        Item item = new Item();
        item.setId(1L);
        item.setName("Test Item");

        List<Item> mockItems = List.of(item);

        ItemDto itemDto = new ItemDto();
        itemDto.setName("Test Item");
        List<ItemDto> mockDtos = List.of(itemDto);

        when(itemRepository.findAll()).thenReturn(mockItems);
        when(itemMapper.itemDtos(mockItems)).thenReturn(mockDtos);

        List<ItemDto> result = itemService.getAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Test Item", result.get(0).getName());

        verify(itemRepository, times(1)).findAll();
    }

    @Test
    void addItemTest() {
        ItemDto inputDto = new ItemDto();
        inputDto.setName("New Phone");

        Item itemEntity = new Item();
        itemEntity.setName("New Phone");

        Item savedEntity = new Item();
        savedEntity.setId(10L); // База как будто присвоила ID
        savedEntity.setName("New Phone");

        ItemDto resultDto = new ItemDto();
        resultDto.setId(10L);
        resultDto.setName("New Phone");

        when(itemMapper.item(inputDto)).thenReturn(itemEntity);
        when(itemRepository.save(itemEntity)).thenReturn(savedEntity);
        when(itemMapper.itemDto(savedEntity)).thenReturn(resultDto);

        ItemDto actualResult = itemService.addItem(inputDto);

        Assertions.assertNotNull(actualResult.getId());
        Assertions.assertEquals(10L, actualResult.getId());

        verify(itemRepository).save(any(Item.class));
    }

    @Test
    void getById_NotFound_Test() {
        when(itemRepository.findById(99L)).thenReturn(Optional.empty());

        ItemDto result = itemService.getById(99L);

        Assertions.assertNull(result);
    }
}