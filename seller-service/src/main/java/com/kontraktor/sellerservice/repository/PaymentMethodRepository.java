package com.kontraktor.sellerservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kontraktor.sellerservice.model.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, String>{
	
}
