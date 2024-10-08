package com.siddhesh.project.uber.uberApp.entities;


import com.siddhesh.project.uber.uberApp.entities.enums.TransactionMethod;
import com.siddhesh.project.uber.uberApp.entities.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(indexes = {
        @Index(name = "idx_walletTransaction_wallet", columnList = "wallet_id"),
        @Index(name = "idx_walletTransaction_ride", columnList = "ride_id")
})
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    @ManyToOne
    private Ride ride;

    private String transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Wallet wallet;

    @CreationTimestamp
    private LocalDateTime timestamp;

}
