package com.kontraktor.sellerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kontraktor.sellerservice.model.SellerInfo;

public interface SellerRepository extends JpaRepository<SellerInfo, Long>{
	
}
