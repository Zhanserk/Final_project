package com.example.final_project.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CountryDto {
    private Long id;
    private String name;
    private String code;
}
