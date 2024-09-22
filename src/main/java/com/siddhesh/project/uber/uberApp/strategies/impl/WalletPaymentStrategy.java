package com.siddhesh.project.uber.uberApp.strategies.impl;

import com.siddhesh.project.uber.uberApp.entities.Driver;
import com.siddhesh.project.uber.uberApp.entities.Payment;
import com.siddhesh.project.uber.uberApp.entities.Rider;
import com.siddhesh.project.uber.uberApp.entities.enums.PaymentStatus;
import com.siddhesh.project.uber.uberApp.entities.enums.TransactionMethod;
import com.siddhesh.project.uber.uberApp.repositories.PaymentRepository;
import com.siddhesh.project.uber.uberApp.services.PaymentService;
import com.siddhesh.project.uber.uberApp.services.WalletService;
import com.siddhesh.project.uber.uberApp.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepository;
    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();
        walletService.deductMoneyFromWallet(rider.getUser(), payment.getAmount(), null, payment.getRide(), TransactionMethod.RIDE);
        double platformCommission = payment.getAmount() * PLATFORM_COMMISSION;
        walletService.addMoneyToWallet(driver.getUser(), payment.getAmount() - platformCommission, null, payment.getRide(), TransactionMethod.RIDE);
        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
