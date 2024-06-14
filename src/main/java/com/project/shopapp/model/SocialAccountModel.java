package com.project.shopapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "social_accounts")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialAccountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String provider;

    @Column(length = 150)
    private String email;

    @Column(length = 150)
    private String name;

    @Column(name = "provider_id", nullable = false, length = 50)
    private String providerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel userId;
}
