package com.project.shopapp.repository;

import com.project.shopapp.model.OrderDetailModel;
import com.project.shopapp.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailModel, Long> {
    List<OrderDetailModel> findByOrderId(OrderModel orderId);
}
