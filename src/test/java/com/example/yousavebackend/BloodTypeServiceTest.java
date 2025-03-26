package com.example.yousavebackend;

import com.example.yousavebackend.DTOs.Blood.BloodTypeRequestDTO;
import com.example.yousavebackend.DTOs.Blood.BloodTypeResponseDTO;
import com.example.yousavebackend.entities.BloodType;
import com.example.yousavebackend.repositories.BloodTypeRepository;
import com.example.yousavebackend.services.implementations.BloodTypeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BloodTypeServiceTest {

    @Mock
    private BloodTypeRepository bloodTypeRepository;

    @InjectMocks
    private BloodTypeServiceImpl bloodTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBloodTypes() {
        BloodType bloodType = new BloodType();
        bloodType.setId(1L);
        bloodType.setType("A+");
        bloodType.setDonationCount(5);

        when(bloodTypeRepository.findAll()).thenReturn(Collections.singletonList(bloodType));

        var result = bloodTypeService.getAllBloodTypes();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("A+", result.get(0).getType());
    }

    @Test
    void testGetBloodTypeById() {
        BloodType bloodType = new BloodType();
        bloodType.setId(1L);
        bloodType.setType("O-");

        when(bloodTypeRepository.findById(1L)).thenReturn(Optional.of(bloodType));

        Optional<BloodTypeResponseDTO> result = bloodTypeService.getBloodTypeById(1L);
        assertTrue(result.isPresent());
        assertEquals("O-", result.get().getType());
    }

    @Test
    void testSaveBloodType() {
        BloodTypeRequestDTO requestDTO = new BloodTypeRequestDTO();
        requestDTO.setType("AB+");

        BloodType savedBloodType = new BloodType();
        savedBloodType.setId(2L);
        savedBloodType.setType("AB+");
        savedBloodType.setDonationCount(0);

        when(bloodTypeRepository.save(any())).thenReturn(savedBloodType);

        BloodTypeResponseDTO result = bloodTypeService.saveBloodType(requestDTO);
        assertEquals("AB+", result.getType());
        assertEquals(0, result.getDonationCount());
    }

    @Test
    void testDeleteBloodType() {
        when(bloodTypeRepository.existsById(1L)).thenReturn(true);
        doNothing().when(bloodTypeRepository).deleteById(1L);

        assertDoesNotThrow(() -> bloodTypeService.deleteBloodType(1L));
        verify(bloodTypeRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteBloodType_NotFound() {
        when(bloodTypeRepository.existsById(99L)).thenReturn(false);
        Exception exception = assertThrows(RuntimeException.class, () -> bloodTypeService.deleteBloodType(99L));
        assertEquals("BloodType not found with id 99", exception.getMessage());
    }
}
