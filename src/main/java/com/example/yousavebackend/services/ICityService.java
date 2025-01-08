package com.example.yousavebackend.services;

import com.example.yousavebackend.DTOs.City.CityRequestDTO;
import com.example.yousavebackend.DTOs.City.CityResponseDTO;
import com.example.yousavebackend.entities.City;

import java.util.List;
import java.util.Optional;

public interface ICityService {
    List<CityResponseDTO> getAllCities();
    Optional<CityResponseDTO> getCityById(Long id);
    CityResponseDTO saveCity(CityRequestDTO cityRequestDTO);
    CityResponseDTO updateCity(Long id, CityRequestDTO cityRequestDTO);
    void deleteCity(Long id);
}