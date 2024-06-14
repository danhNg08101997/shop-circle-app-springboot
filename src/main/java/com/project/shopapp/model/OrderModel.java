package com.project.shopapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel userId;

    @Column(name = "fullname", length = 100, nullable = false)
    private String fullName;

    @Column(length = 150)
    private String email;

    @Column(name = "phone_number", length = 20, nullable = false)
    private String phoneNumber;

    @Column(length = 100)
    private String address;

    private String note;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    private String status;

    @Column(name = "total_money")
    private Float totalMoney;

    @Column(name = "shipping_address", length = 200)
    private String shippingAddress;

    @Column(name = "shipping_method")
    private String shippingMethod;

    @Column(name = "shipping_date")
    private Date shippingDate;

    @Column(name = "tracking_number")
    private String trackingNumber;

    @Column(name = "payment_method")
    private String paymentMethod;

    private boolean active; // thuộc về admin
}
