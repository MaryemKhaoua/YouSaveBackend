package com.example.yousavebackend.services.implementations;

import com.example.yousavebackend.DTOs.Blood.BloodTypeRequestDTO;
import com.example.yousavebackend.DTOs.Blood.BloodTypeResponseDTO;
import com.example.yousavebackend.entities.BloodType;
import com.example.yousavebackend.repositories.BloodTypeRepository;
import com.example.yousavebackend.services.IBloodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
public class BloodTypeServiceImpl implements IBloodTypeService {

    @Autowired
    private BloodTypeRepository bloodTypeRepository;

    @Override
    public List<BloodTypeResponseDTO> getAllBloodTypes() {
        return bloodTypeRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BloodTypeResponseDTO> getBloodTypeById(Long id) {
        return bloodTypeRepository.findById(id).map(this::mapToDTO);
    }

    @Override
    public BloodTypeResponseDTO saveBloodType(BloodTypeRequestDTO bloodTypeRequestDTO) {
        BloodType bloodType = new BloodType();
        bloodType.setType(bloodTypeRequestDTO.getType());
        BloodType savedBloodType = bloodTypeRepository.save(bloodType);
        return mapToDTO(savedBloodType);
    }

    @Override
    public BloodTypeResponseDTO updateBloodType(Long id, BloodTypeRequestDTO bloodTypeRequestDTO) {
        BloodType bloodType = bloodTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BloodType not found with id " + id));
        bloodType.setType(bloodTypeRequestDTO.getType());
        BloodType updatedBloodType = bloodTypeRepository.save(bloodType);
        return mapToDTO(updatedBloodType);
    }

    @Override
    public void deleteBloodType(Long id) {
        if (bloodTypeRepository.existsById(id)) {
            bloodTypeRepository.deleteById(id);
        } else {
            throw new RuntimeException("BloodType not found with id " + id);
        }
    }

    private BloodTypeResponseDTO mapToDTO(BloodType bloodType) {
        BloodTypeResponseDTO bloodTypeResponseDTO = new BloodTypeResponseDTO();
        bloodTypeResponseDTO.setId(bloodType.getId());
        bloodTypeResponseDTO.setType(bloodType.getType());
        return bloodTypeResponseDTO;
    }
}