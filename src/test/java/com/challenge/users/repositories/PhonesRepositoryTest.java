package com.challenge.users.repositories;

import com.challenge.users.dtos.PhoneRequestDto;
import com.challenge.users.entities.PhonesEntity;
import com.challenge.users.factories.DtoFactory;
import com.challenge.users.factories.EntityFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class PhonesRepositoryTest {

    @Autowired
    private PhonesRepository phonesRepository ;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void shouldSavePhone() {
        PhoneRequestDto phoneRequestDto = DtoFactory.createPhoneRequestDto("1234", "51", "02");
        PhonesEntity phonesEntity = EntityFactory.createPhonesEntity(phoneRequestDto);

        phonesRepository.save(phonesEntity);

        assertNotNull(entityManager.getId(phonesEntity));
    }
}
