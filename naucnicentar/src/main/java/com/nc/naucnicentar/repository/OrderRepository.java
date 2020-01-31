package com.nc.naucnicentar.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.nc.naucnicentar.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
