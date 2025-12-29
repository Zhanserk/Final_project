package com.example.final_project.ServiceTest;



import com.example.final_project.DTO.CountryDto;
import com.example.final_project.Entity.Country;
import com.example.final_project.Service.CountryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
public class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    private CountryDto createCountryDto(String name, String code) {
        return CountryDto.builder()
                .name(name)
                .code(code)
                .build();
    }

    @Test
    void addCountryTest() {
        CountryDto dto = createCountryDto("Kazakhstan", "KZ");
        CountryDto saved = countryService.addCountry(dto);

        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals("Kazakhstan", saved.getName());
    }

    @Test
    void getByIdTest() {
        CountryDto saved = countryService.addCountry(createCountryDto("Japan", "JP"));
        CountryDto found = countryService.getById(saved.getId());

        Assertions.assertNotNull(found);
        Assertions.assertEquals("Japan", found.getName());
    }

    @Test
    void getAllTest() {
        countryService.addCountry(createCountryDto("USA", "US"));
        countryService.addCountry(createCountryDto("France", "FR"));

        List<CountryDto> list = countryService.getAll();

        Assertions.assertNotNull(list);
        Assertions.assertTrue(list.size() >= 2);
    }

    @Test
    void updateByIdTest() {
        CountryDto saved = countryService.addCountry(createCountryDto("Old Name", "OLD"));
        CountryDto updateDto = createCountryDto("New Name", "NEW");

        CountryDto updated = countryService.updateById(saved.getId(), updateDto);

        Assertions.assertEquals("New Name", updated.getName());
        Assertions.assertEquals("NEW", updated.getCode());
    }

    @Test
    void deleteByIdTest() {
        CountryDto saved = countryService.addCountry(createCountryDto("To Delete", "DEL"));
        Long id = saved.getId();

        countryService.deleteById(id);

        Assertions.assertNull(countryService.getById(id));
    }
}
