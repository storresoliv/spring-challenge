package com.challenge.users.repositories;

import com.challenge.users.entities.PhonesEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PhonesRepository extends CrudRepository<PhonesEntity, UUID> {
}
