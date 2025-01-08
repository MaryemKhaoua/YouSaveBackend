package com.example.yousavebackend.services.implementations;

import com.example.yousavebackend.DTOs.City.CityRequestDTO;
import com.example.yousavebackend.DTOs.City.CityResponseDTO;
import com.example.yousavebackend.entities.City;
import com.example.yousavebackend.repositories.CityRepository;
import com.example.yousavebackend.services.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements ICityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<CityResponseDTO> getAllCities() {
        return cityRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CityResponseDTO> getCityById(Long id) {
        return cityRepository.findById(id).map(this::mapToDTO);
    }

    @Override
    public CityResponseDTO saveCity(CityRequestDTO cityRequestDTO) {
        Optional<City> existingCity = cityRepository.findByName(cityRequestDTO.getName());
        if (existingCity.isPresent()) {
            throw new RuntimeException("City already exists with name " + cityRequestDTO.getName());
        }
        City city = new City();
        city.setName(cityRequestDTO.getName());
        City savedCity = cityRepository.save(city);
        return mapToDTO(savedCity);
    }

    @Override
    public CityResponseDTO updateCity(Long id, CityRequestDTO cityRequestDTO) {
        Optional<City> optionalCity = cityRepository.findById(id);
        if (optionalCity.isPresent()) {
            City city = optionalCity.get();
            city.setName(cityRequestDTO.getName());
            City updatedCity = cityRepository.save(city);
            return mapToDTO(updatedCity);
        } else {
            throw new RuntimeException("City not found with id " + id);
        }
    }

    @Override
    public void deleteCity(Long id) {
        if (cityRepository.existsById(id)) {
            cityRepository.deleteById(id);
        } else {
            throw new RuntimeException("City not found with id " + id);
        }
    }

    private CityResponseDTO mapToDTO(City city) {
        CityResponseDTO cityResponseDTO = new CityResponseDTO();
        cityResponseDTO.setId(city.getId());
        cityResponseDTO.setName(city.getName());
        return cityResponseDTO;
    }
}