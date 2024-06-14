package com.project.shopapp.repository;

import com.project.shopapp.model.OrderModel;
import com.project.shopapp.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    // Tìm đơn hàng của 1 user nào đó
    List<OrderModel> findByUserId(UserModel userId);
}
