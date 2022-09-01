package com.challenge.users.repositories;

import com.challenge.users.dtos.PhoneRequestDTO;
import com.challenge.users.entities.PhonesEntity;
import com.challenge.users.mappers.PhoneRequestDTOMapper;
import com.challenge.users.mappers.PhonesEntityMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest()
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@EnableJpaRepositories
public class PhonesRepositoryTest {
    @Autowired
    private PhonesRepository phonesRepository;

    @Autowired
    private PhoneRequestDTOMapper phoneRequestDTOMapper;

    @Autowired
    private PhonesEntityMapper phonesEntityMapper;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void shouldSavePhone() {
        PhoneRequestDTO phoneRequestDto = this.phoneRequestDTOMapper.createPhoneRequestDto("1234", "51", "02");

        assertNotNull(phoneRequestDto);

        PhonesEntity phonesEntity = this.phonesEntityMapper.createPhonesEntity(phoneRequestDto);

        assertNotNull(phonesEntity);

        this.phonesRepository.save(phonesEntity);

        assertNotNull(this.entityManager.getId(phonesEntity));
    }
}
