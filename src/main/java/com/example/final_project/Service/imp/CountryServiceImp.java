package com.example.final_project.Service.imp;

import com.example.final_project.DTO.CountryDto;
import com.example.final_project.Entity.Country;
import com.example.final_project.Repository.CountryRepository;
import com.example.final_project.Service.CountryService;
import com.example.final_project.mapper.CountryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImp implements CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;
    @Override
    public List<CountryDto> getAll() {
        return countryMapper.countryDtos(countryRepository.findAll());
    }

    @Override
    public CountryDto getById(Long id) {
        return countryMapper.countryDto(countryRepository.findById(id).orElse(null));
    }

    @Override
    public CountryDto addCountry(CountryDto countryDto) {
        return countryMapper.countryDto(countryRepository.save(countryMapper.country(countryDto)));
    }

    @Override
    public CountryDto updateById(Long id, CountryDto countryDto) {
        Country country = countryMapper.country(countryDto);
        Country UpdateCountry = countryRepository.findById(id).orElse(null);
        UpdateCountry.setName(country.getName());
        UpdateCountry.setCode(country.getCode());
        countryRepository.save(UpdateCountry);
        return countryMapper.countryDto(UpdateCountry);
    }

    @Override
    public void deleteById(Long id) {
        countryRepository.deleteById(id);
    }
}
