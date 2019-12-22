package com.kontraktor.paypalservice.service;

import java.io.IOException;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.kontraktor.paypalservice.model.AuthToken;
import com.kontraktor.paypalservice.model.Order;
import com.kontraktor.paypalservice.model.SellerInfo;

import com.kontraktor.paypalservice.repository.SellerRepository;

import retrofit2.Call;
import retrofit2.Response;
@Service
public class SellerService {
	@Autowired
	private SellerRepository sellerRepo;
	public SellerInfo findOne(Long id){
		return sellerRepo.findOne(id);
	}
	
	
}
