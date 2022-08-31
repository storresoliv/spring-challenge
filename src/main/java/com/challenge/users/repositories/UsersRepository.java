package com.challenge.users.repositories;

import com.challenge.users.entities.UsersEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UsersRepository extends CrudRepository<UsersEntity, UUID> {
}
