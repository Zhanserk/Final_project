package com.example.final_project.DTO;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemDto {
    private Long id;
    private String name;
    private int price;
    private String description;
    private CategoryDto category;
    private List<CountryDto> countries;
}
