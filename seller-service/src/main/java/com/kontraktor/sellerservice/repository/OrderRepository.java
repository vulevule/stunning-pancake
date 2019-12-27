package com.kontraktor.sellerservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.kontraktor.sellerservice.model.CustomOrder;


public interface OrderRepository extends JpaRepository<CustomOrder, Long> {
	public CustomOrder findByToken(String token);
}
