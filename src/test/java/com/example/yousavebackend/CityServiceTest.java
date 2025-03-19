package com.example.yousavebackend;
import com.example.yousavebackend.DTOs.City.CityRequestDTO;
import com.example.yousavebackend.DTOs.City.CityResponseDTO;
import com.example.yousavebackend.entities.City;
import com.example.yousavebackend.repositories.CityRepository;
import com.example.yousavebackend.services.implementations.CityServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityServiceImpl cityService;

    private City city;
    private CityRequestDTO cityRequestDTO;

    @BeforeEach
    void setUp() {
        city = new City();
        city.setId(1L);
        city.setName("Paris");

        cityRequestDTO = new CityRequestDTO();
        cityRequestDTO.setName("Paris");
    }

    @Test
    void getAllCities_ShouldReturnCityList() {
        when(cityRepository.findAll()).thenReturn(Collections.singletonList(city));

        var result = cityService.getAllCities();

        assertEquals(1, result.size());
        assertEquals("Paris", result.get(0).getName());
        verify(cityRepository, times(1)).findAll();
    }

    @Test
    void getCityById_ShouldReturnCity() {
        when(cityRepository.findById(1L)).thenReturn(Optional.of(city));

        Optional<CityResponseDTO> result = cityService.getCityById(1L);

        assertTrue(result.isPresent());
        assertEquals("Paris", result.get().getName());
        verify(cityRepository, times(1)).findById(1L);
    }

    @Test
    void getCityById_ShouldReturnEmpty() {
        when(cityRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<CityResponseDTO> result = cityService.getCityById(2L);

        assertFalse(result.isPresent());
        verify(cityRepository, times(1)).findById(2L);
    }

    @Test
    void saveCity_ShouldSaveCitySuccessfully() {
        when(cityRepository.findByName("Paris")).thenReturn(Optional.empty());
        when(cityRepository.save(any(City.class))).thenReturn(city);

        CityResponseDTO result = cityService.saveCity(cityRequestDTO);

        assertEquals("Paris", result.getName());
        verify(cityRepository, times(1)).save(any(City.class));
    }

    @Test
    void saveCity_ShouldThrowException_WhenCityAlreadyExists() {
        when(cityRepository.findByName("Paris")).thenReturn(Optional.of(city));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            cityService.saveCity(cityRequestDTO);
        });

        assertTrue(exception.getMessage().contains("City already exists"));
    }

    @Test
    void updateCity_ShouldUpdateCitySuccessfully() {
        when(cityRepository.findById(1L)).thenReturn(Optional.of(city));
        when(cityRepository.save(any(City.class))).thenReturn(city);

        CityResponseDTO result = cityService.updateCity(1L, cityRequestDTO);

        assertEquals("Paris", result.getName());
        verify(cityRepository, times(1)).save(any(City.class));
    }

    @Test
    void updateCity_ShouldThrowException_WhenCityNotFound() {
        when(cityRepository.findById(2L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            cityService.updateCity(2L, cityRequestDTO);
        });

        assertTrue(exception.getMessage().contains("City not found"));
    }

    @Test
    void deleteCity_ShouldDeleteCitySuccessfully() {
        when(cityRepository.existsById(1L)).thenReturn(true);
        doNothing().when(cityRepository).deleteById(1L);

        cityService.deleteCity(1L);

        verify(cityRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteCity_ShouldThrowException_WhenCityNotFound() {
        when(cityRepository.existsById(2L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            cityService.deleteCity(2L);
        });

        assertTrue(exception.getMessage().contains("City not found"));
    }
}

