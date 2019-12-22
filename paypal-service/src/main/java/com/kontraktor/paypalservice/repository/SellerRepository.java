package com.kontraktor.paypalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kontraktor.paypalservice.model.SellerInfo;

@Repository
public interface SellerRepository extends JpaRepository<SellerInfo, Long>{

}
