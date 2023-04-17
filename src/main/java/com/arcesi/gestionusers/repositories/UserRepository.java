package com.arcesi.gestionusers.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.gestionusers.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Page<UserEntity> findByFirstNameContainingIgnoreCase(String patialFirstName, Pageable pageable);

	Optional<UserEntity> findByEmailIgnoreCase(String trim);

	Optional<UserEntity> findByCodeUniqueIgnoreCase(String codeUnique);

	void findByCode(Long codeUser);

}
