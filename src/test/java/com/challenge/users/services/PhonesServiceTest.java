package com.challenge.users.services;

import com.challenge.users.dtos.PhoneRequestDTO;
import com.challenge.users.entities.PhonesEntity;
import com.challenge.users.mappers.PhoneRequestDTOMapper;
import com.challenge.users.mappers.PhonesEntityMapper;
import com.challenge.users.repositories.PhonesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = { PhoneRequestDTOMapper.class, PhonesEntityMapper.class })
public class PhonesServiceTest {
    private PhonesService phonesService;

    @MockBean
    private PhonesRepository phonesRepository;

    @Autowired
    private PhoneRequestDTOMapper phoneRequestDTOMapper;

    @Autowired
    private PhonesEntityMapper phonesEntityMapper;

    @BeforeEach
    public void init() {
        this.phonesService = new PhonesService(this.phonesEntityMapper, this.phonesRepository);
    }

    @Test
    public void shouldCreated() {
        assertNotNull(this.phonesService);
    }


    @Test
    public void shouldSavePhones() {
        List<PhoneRequestDTO> phonesRequestDto = new ArrayList<>();
        List<PhonesEntity> phonesEntities = new ArrayList<>();

        PhoneRequestDTO phoneOneRequestDto = this.phoneRequestDTOMapper.createPhoneRequestDto("1", "1", "1");
        PhoneRequestDTO phoneTwoRequestDto = this.phoneRequestDTOMapper.createPhoneRequestDto("1", "1", "1");
        PhoneRequestDTO phoneThreeRequestDto = this.phoneRequestDTOMapper.createPhoneRequestDto("1", "1", "1");

        phonesRequestDto.add(phoneOneRequestDto);
        phonesRequestDto.add(phoneTwoRequestDto);
        phonesRequestDto.add(phoneThreeRequestDto);

        PhonesEntity phoneOneEntity = this.phonesEntityMapper.createPhonesEntity(phoneOneRequestDto);
        PhonesEntity phoneTwoEntity = this.phonesEntityMapper.createPhonesEntity(phoneTwoRequestDto);
        PhonesEntity phoneThreeEntity = this.phonesEntityMapper.createPhonesEntity(phoneThreeRequestDto);

        phonesEntities.add(phoneOneEntity);
        phonesEntities.add(phoneTwoEntity);
        phonesEntities.add(phoneThreeEntity);

        when(this.phonesRepository.saveAll(phonesEntities)).thenReturn(phonesEntities);

        List<PhonesEntity> phonesEntitiesSaved = this.phonesService.createPhones(phonesRequestDto);

        assertEquals(3, phonesEntitiesSaved.size());
    }
}
