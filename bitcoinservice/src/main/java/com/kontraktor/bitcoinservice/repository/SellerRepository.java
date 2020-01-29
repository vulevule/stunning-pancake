package com.kontraktor.bitcoinservice.repository;

import com.kontraktor.bitcoinservice.model.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<SellerInfo, Long> {

}
