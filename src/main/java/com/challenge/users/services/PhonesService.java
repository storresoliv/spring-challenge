package com.challenge.users.services;

import com.challenge.users.dtos.PhoneRequestDto;
import com.challenge.users.entities.PhonesEntity;
import com.challenge.users.factories.EntityFactory;
import com.challenge.users.repositories.PhonesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PhonesService {

    private PhonesRepository phonesRepository;

    public PhonesService(@Autowired PhonesRepository phonesRepository) {
        this.phonesRepository = phonesRepository;
    }

    public List<PhonesEntity> createPhones(List<PhoneRequestDto> phonesRequestDto) {
        if (phonesRequestDto == null || phonesRequestDto.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        List<PhonesEntity> phonesEntities = new ArrayList<>();

        List<PhonesEntity> phones = phonesRequestDto
                .stream()
                .map(phone -> EntityFactory.createPhonesEntity(phone))
                .collect(Collectors.toList());

        phonesRepository.saveAll(phones).forEach(phoneEntity -> phonesEntities.add(phoneEntity));

        return phonesEntities;
    }
}
