package com.siddhesh.project.uber.uberApp.dto;

import com.siddhesh.project.uber.uberApp.entities.WalletTransaction;
import lombok.Data;

import java.util.List;

@Data
public class WalletDto {
    private Long id;

    private UserDto user;

    private Double balance;

    private List<WalletTransaction> walletTransactions;
}
