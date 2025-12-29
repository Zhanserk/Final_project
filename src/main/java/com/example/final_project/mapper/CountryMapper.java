package com.example.final_project.mapper;

import com.example.final_project.DTO.CountryDto;
import com.example.final_project.Entity.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryDto countryDto(Country country);
    Country country(CountryDto countryDto);
    List<CountryDto> countryDtos(List<Country> countries);
}
