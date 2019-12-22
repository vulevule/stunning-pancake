package com.kontraktor.paypalservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kontraktor.paypalservice.model.PaymentRef;
@Repository
public interface PaymentRepository extends JpaRepository<PaymentRef, Long>{
	public PaymentRef findByPaymentId(String paymentId);
}
