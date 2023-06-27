package com.arcesi.cliniquemedical.security.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcesi.cliniquemedical.security.entities.PasswordResetToken;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long>{

	Optional<PasswordResetToken> findByToken(String token);
}
