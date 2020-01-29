package com.kontraktor.bitcoinservice.service;

import com.kontraktor.bitcoinservice.model.SellerInfo;
import com.kontraktor.bitcoinservice.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
	@Autowired
	private SellerRepository sellerRepo;
	public SellerInfo findOne(Long id){
		return sellerRepo.findOne(id);
	}
	
	
}
