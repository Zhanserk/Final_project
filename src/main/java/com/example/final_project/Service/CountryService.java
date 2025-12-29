package com.example.final_project.Service;

import com.example.final_project.DTO.CountryDto;
import com.example.final_project.Entity.Country;

import java.util.List;

public interface CountryService {
    List<CountryDto> getAll();
    CountryDto getById(Long id);
    CountryDto addCountry(CountryDto countryDto);
    CountryDto updateById(Long id, CountryDto countryDto);
    void deleteById(Long id);
}
