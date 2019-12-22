package com.kontraktor.paypalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kontraktor.paypalservice.model.AuthToken;

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long>{

}
