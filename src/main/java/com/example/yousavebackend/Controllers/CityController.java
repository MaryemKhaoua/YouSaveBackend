package com.example.yousavebackend.Controllers;

import com.example.yousavebackend.DTOs.City.CityRequestDTO;
import com.example.yousavebackend.DTOs.City.CityResponseDTO;
import com.example.yousavebackend.services.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private ICityService cityService;

    @GetMapping("/all")
    public List<CityResponseDTO> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponseDTO> getCityById(@PathVariable Long id) {
        return cityService.getCityById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createCity(@RequestBody CityRequestDTO cityRequestDTO) {
        try {
            CityResponseDTO newCity = cityService.saveCity(cityRequestDTO);
            return ResponseEntity.ok(newCity);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityResponseDTO> updateCity(@PathVariable Long id, @RequestBody CityRequestDTO cityRequestDTO) {
        try {
            CityResponseDTO updatedCity = cityService.updateCity(id, cityRequestDTO);
            return ResponseEntity.ok(updatedCity);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        try {
            cityService.deleteCity(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}