/* **********************************************************************
 * Copyright 2023 VMware, Inc.  All rights reserved. VMware Confidential
 * *********************************************************************/

package dda.project.backend.service.impl;

import dda.project.backend.entity.OrderModel;
import dda.project.backend.entity.PaymentModel;
import dda.project.backend.repository.ItemRepository;
import dda.project.backend.repository.OrderRepository;
import dda.project.backend.repository.PaymentRepository;
import dda.project.backend.repository.UserRepository;
import dda.project.backend.service.CustomerService;
import dda.project.backend.service.OrderService;
import dda.project.backend.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderService orderService;
    private final CustomerService customerService;

    public PaymentServiceImpl(OrderRepository orderRepository, UserRepository userRepository,
                              ItemRepository itemRepository, PaymentRepository paymentRepository,
                              OrderService orderService, CustomerService customerService) {
        this.paymentRepository = paymentRepository;
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @Override
    public PaymentModel getPaymentForId(Long payId) {
        List<PaymentModel> paymentModels = this.paymentRepository.findAll();
        Optional<PaymentModel> paymentModel =
                paymentModels.stream().filter(item -> item.getId().equals(payId)).findFirst();
        return paymentModel.isPresent() ? paymentModel.get() : new PaymentModel();
    }

    @Override
    public void addPayment(PaymentModel paymentModel) {
        this.customerService.registerUser(paymentModel.getUser());
        this.orderService.addOrder(paymentModel.getOrder());

        List<PaymentModel> paymentModels = this.paymentRepository.findAll();
        Optional<PaymentModel> matchingFound =
                paymentModels.stream().filter(payment -> payment.equals(paymentModel)).findFirst();
        if (matchingFound.isPresent()) {
            paymentModel.setId(matchingFound.get().getId());
        }
        this.paymentRepository.save(paymentModel);
    }

    @Override
    public void updatePayment(Long payId, PaymentModel newModel) {
        PaymentModel oldModel = this.getPaymentForId(payId);
//        newModel.setId(oldModel.getId());
        oldModel.setPaystatus(newModel.getPaystatus());
        oldModel.setPaytype(newModel.getPaytype());
        this.paymentRepository.save(oldModel);
    }

    @Override
    public PaymentModel getPaymentForOrderId(Long orderId) {
        List<PaymentModel> paymentModels = this.paymentRepository.findAll();
        Optional<PaymentModel> paymentModel =
                paymentModels.stream().filter(pay -> pay.getOrder().getId().equals(orderId)).findFirst();
        return paymentModel.isPresent() ? paymentModel.get() : new PaymentModel();
    }

    @Override
    public List<PaymentModel> getAllPayment() {
        return this.paymentRepository.findAll();
    }
}

