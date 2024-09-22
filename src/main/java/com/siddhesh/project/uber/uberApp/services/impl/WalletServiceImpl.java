package com.siddhesh.project.uber.uberApp.services.impl;

import com.siddhesh.project.uber.uberApp.dto.RideDto;
import com.siddhesh.project.uber.uberApp.dto.WalletTransactionDto;
import com.siddhesh.project.uber.uberApp.entities.Ride;
import com.siddhesh.project.uber.uberApp.entities.User;
import com.siddhesh.project.uber.uberApp.entities.Wallet;
import com.siddhesh.project.uber.uberApp.entities.WalletTransaction;
import com.siddhesh.project.uber.uberApp.entities.enums.TransactionMethod;
import com.siddhesh.project.uber.uberApp.entities.enums.TransactionType;
import com.siddhesh.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.siddhesh.project.uber.uberApp.repositories.WalletRepository;
import com.siddhesh.project.uber.uberApp.services.WalletService;
import com.siddhesh.project.uber.uberApp.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private final ModelMapper modelMapper;
    private final WalletTransactionService walletTransactionService;
    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, double money, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance() + money);
        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.CREDIT)
                .transactionMethod(transactionMethod)
                .amount(money)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    public void withdrawAllMoneyFromWallet(Long userId, double money) {

    }

    @Override
    public Wallet findWalletById(Long id) {
        return walletRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Wallet not found"));
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Wallet not found"));
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, double money, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        wallet.setBalance(wallet.getBalance() - money);
        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.DEBIT)
                .transactionMethod(transactionMethod)
                .amount(money)
                .build();
        walletTransactionService.createNewWalletTransaction(walletTransaction);
        return walletRepository.save(wallet);
    }
}
