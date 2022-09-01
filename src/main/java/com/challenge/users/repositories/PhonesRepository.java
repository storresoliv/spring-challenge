package com.challenge.users.repositories;

import com.challenge.users.entities.PhonesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhonesRepository extends JpaRepository<PhonesEntity, UUID> {
}
