package com.aradnab.boot.general.service.service_controller;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public interface PaypalServiceInterface {
    Payment createPayment(
            Double total,
            String currency,
            String method,
            String intent,
            String description,
            String cancelMessage,
            String successMessage) throws PayPalRESTException;

    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
}
