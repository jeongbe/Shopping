package com.example.ShowpingProject.service;

import com.example.ShowpingProject.entity.Payment;
import com.example.ShowpingProject.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    public void insertPayment(int userCode, Long orderID, String totalPrice) {

        Payment payment = new Payment();

        payment.setOrder_id(orderID);
        payment.setUser_code((long) userCode);
        payment.setTatal_price(totalPrice);

        paymentRepository.save(payment);
        log.info(payment.toString());

    }
}
