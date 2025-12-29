package com.example.final_project.controller;

import com.example.final_project.DTO.CountryDto;
import com.example.final_project.Service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/item/country")
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public List<CountryDto> getAll() {
        return countryService.getAll();
    }

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<CountryDto> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(countryService.getById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CountryDto> addCountry(@RequestBody CountryDto countryDto) {
        CountryDto created = countryService.addCountry(countryDto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id:\\d+}")
    public ResponseEntity<CountryDto> updateCountry(@PathVariable Long id, @RequestBody CountryDto countryDto) {
        try {
            return ResponseEntity.ok(countryService.updateById(id, countryDto));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id:\\d+}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        try {
            countryService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
