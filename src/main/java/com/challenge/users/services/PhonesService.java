package com.challenge.users.services;

import com.challenge.users.dtos.PhoneRequestDTO;
import com.challenge.users.entities.PhonesEntity;
import com.challenge.users.mappers.PhonesEntityMapper;
import com.challenge.users.repositories.PhonesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PhonesService {
    private final PhonesEntityMapper phonesEntityMapper;
    private final PhonesRepository phonesRepository;

    public PhonesService(@Autowired PhonesEntityMapper phonesEntityMapper, @Autowired PhonesRepository phonesRepository) {
        this.phonesEntityMapper = phonesEntityMapper;
        this.phonesRepository = phonesRepository;
    }

    public List<PhonesEntity> createPhones(List<PhoneRequestDTO> phonesRequestDto) {
        List<PhonesEntity> phonesEntities = new ArrayList<>();

        if (phonesRequestDto == null || phonesRequestDto.isEmpty()) {
            return phonesEntities;
        }

        List<PhonesEntity> phones = phonesRequestDto
                .stream()
                .map(this.phonesEntityMapper::createPhonesEntity)
                .collect(Collectors.toList());

        this.phonesRepository.saveAll(phones).forEach(phonesEntities::add);

        return phonesEntities;
    }
}
