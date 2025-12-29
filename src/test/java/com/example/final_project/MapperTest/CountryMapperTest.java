package com.example.final_project.MapperTest;

import com.example.final_project.DTO.CountryDto;
import com.example.final_project.Entity.Country;
import com.example.final_project.mapper.CountryMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CountryMapperTest {
    @Autowired
    private CountryMapper countryMapper;

    @Test
    void convertEntityToDtoTest() {
        Country country = new Country(1L, "KZ", "Kazakhstan");
        var countryDto = countryMapper.countryDto(country);

        Assertions.assertNotNull(countryDto);

        Assertions.assertNotNull(countryDto.getId());
        Assertions.assertNotNull(countryDto.getCode());
        Assertions.assertNotNull(countryDto.getName());

        Assertions.assertEquals(country.getId(), countryDto.getId());
        Assertions.assertEquals(country.getCode(), countryDto.getCode());
        Assertions.assertEquals(country.getName(), countryDto.getName());

    }

    @Test
    void convertDtoToEntityTest() {
        CountryDto countryDto = new CountryDto(1L, "KZ", "Kazakhstan");
        Country country = countryMapper.country(countryDto);

        Assertions.assertNotNull(country);

        Assertions.assertNotNull(country.getId());
        Assertions.assertNotNull(country.getCode());
        Assertions.assertNotNull(country.getName());

        Assertions.assertEquals(countryDto.getId(), country.getId());
        Assertions.assertEquals(countryDto.getCode(), country.getCode());
        Assertions.assertEquals(countryDto.getName(), country.getName());

    }

    @Test
    void convertEntityListToDtoListTest() {
        List<Country> countries = new ArrayList<>();
        countries.add(new Country(1L, "KZ", "Kazakhstan"));
        countries.add(new Country(2L, "US", "United States"));
        countries.add(new Country(3L, "FR", "France"));

        List<CountryDto> countriesDto = countryMapper.countryDtos(countries);

        Assertions.assertNotNull(countriesDto);

        Assertions.assertNotEquals(0, countriesDto.size());

        Assertions.assertEquals(countries.size(), countriesDto.size());

        for(int i = 0; i < countries.size(); i++) {
            Country country = countries.get(i);

            CountryDto countryDto = countriesDto.get(i);

            Assertions.assertNotNull(countryDto);

            Assertions.assertNotNull(countryDto.getId());
            Assertions.assertNotNull(countryDto.getCode());
            Assertions.assertNotNull(countryDto.getName());

            Assertions.assertEquals(country.getId(), countryDto.getId());
            Assertions.assertEquals(country.getCode(), countryDto.getCode());
            Assertions.assertEquals(country.getName(), countryDto.getName());

        }
    }
}
