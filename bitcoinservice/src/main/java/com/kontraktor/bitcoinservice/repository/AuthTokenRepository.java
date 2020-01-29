package com.kontraktor.bitcoinservice.repository;

import com.kontraktor.bitcoinservice.model.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTokenRepository extends JpaRepository<AuthToken, Long>{

}
