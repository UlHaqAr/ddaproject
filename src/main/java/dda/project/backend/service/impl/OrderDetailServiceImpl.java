/* **********************************************************************
 * Copyright 2023 VMware, Inc.  All rights reserved. VMware Confidential
 * *********************************************************************/

package dda.project.backend.service.impl;

import dda.project.backend.entity.CustomerModel;
import dda.project.backend.entity.OrderDetailModel;
import dda.project.backend.repository.OrderDetailRepository;
import dda.project.backend.repository.OrderRepository;
import dda.project.backend.repository.UserRepository;
import dda.project.backend.service.CustomerService;
import dda.project.backend.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final CustomerService customerService;

    public OrderDetailServiceImpl(OrderRepository orderRepository, UserRepository userRepository,
                                  OrderDetailRepository orderDetailRepository,
                                  CustomerService customerService) {
        this.orderDetailRepository = orderDetailRepository;
        this.customerService = customerService;
    }

    @Override
    public OrderDetailModel getOrderDetailForId(Long orderDetailId) {
        List<OrderDetailModel> orderDetailModels = this.orderDetailRepository.findAll();
        Optional<OrderDetailModel> orderDetailModel =
                orderDetailModels.stream().filter(item -> item.getId().equals(orderDetailId)).findFirst();
        return orderDetailModel.isPresent() ? orderDetailModel.get() : new OrderDetailModel();
    }

    @Override
    public void addOrderDetail(OrderDetailModel orderDetailModel) {
        this.customerService.saveCustomer(orderDetailModel.getReceiver());
        this.customerService.saveCustomer(orderDetailModel.getSender());

        List<OrderDetailModel> orderDetailModels = this.orderDetailRepository.findAll();
        Optional<OrderDetailModel> matchingFound =
                orderDetailModels.stream().filter(odm -> odm.equals(orderDetailModel)).findFirst();
        if (matchingFound.isPresent()) {
            orderDetailModel.setId(matchingFound.get().getId());
        }
        this.orderDetailRepository.save(orderDetailModel);

    }
}

