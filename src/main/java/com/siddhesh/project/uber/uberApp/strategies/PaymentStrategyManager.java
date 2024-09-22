package com.siddhesh.project.uber.uberApp.strategies;


import com.siddhesh.project.uber.uberApp.entities.enums.PaymentMethod;
import com.siddhesh.project.uber.uberApp.strategies.impl.CashPaymentStrategy;
import com.siddhesh.project.uber.uberApp.strategies.impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {
    private final WalletPaymentStrategy walletPaymentStrategy;
    private final CashPaymentStrategy cashPaymentStrategy;
    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod) {
        switch (paymentMethod) {
            case WALLET:
                return walletPaymentStrategy;
            case CASH:
                return cashPaymentStrategy;
            default:
                throw new RuntimeException("Invalid payment method");
        }
    }
}
