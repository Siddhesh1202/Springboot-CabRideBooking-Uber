package com.siddhesh.project.uber.uberApp.services;

import com.siddhesh.project.uber.uberApp.entities.Ride;
import com.siddhesh.project.uber.uberApp.entities.User;
import com.siddhesh.project.uber.uberApp.entities.Wallet;
import com.siddhesh.project.uber.uberApp.entities.enums.TransactionMethod;

public interface WalletService {
    Wallet addMoneyToWallet(User user, double money, String transactionId, Ride ride, TransactionMethod transactionMethod);

    void withdrawAllMoneyFromWallet(Long userId, double money);

    Wallet findWalletById(Long id);

    Wallet createNewWallet(User user);

    Wallet findByUser(User user);

    Wallet deductMoneyFromWallet(User user, double money, String transactionId, Ride ride, TransactionMethod transactionMethod);


}
