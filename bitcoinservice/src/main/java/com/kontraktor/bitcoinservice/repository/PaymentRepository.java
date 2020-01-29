package com.kontraktor.bitcoinservice.repository;

import com.kontraktor.bitcoinservice.model.PaymentRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentRef, Long>{
	public PaymentRef findByPaymentId(String paymentId);
}
