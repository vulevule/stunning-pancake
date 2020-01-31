package com.nc.naucnicentar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nc.naucnicentar.model.SellerInfo;

public interface UserRepository extends JpaRepository<SellerInfo, Long> {

}
