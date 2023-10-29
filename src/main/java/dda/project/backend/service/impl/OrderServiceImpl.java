/* **********************************************************************
 * Copyright 2023 VMware, Inc.  All rights reserved. VMware Confidential
 * *********************************************************************/

package dda.project.backend.service.impl;

import dda.project.backend.entity.ItemModel;
import dda.project.backend.entity.OrderDetailModel;
import dda.project.backend.entity.OrderToItemsModel;
import dda.project.backend.entity.UserModel;
import dda.project.backend.entity.OrderModel;
import dda.project.backend.repository.OrderRepository;
import dda.project.backend.repository.UserRepository;
import dda.project.backend.service.CustomerService;
import dda.project.backend.service.ItemService;
import dda.project.backend.service.OrderDetailService;
import dda.project.backend.service.OrderService;
import dda.project.backend.service.OrderToItemsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderDetailService orderDetailService;
    private final CustomerService customerService;
    private final ItemService itemService;
    private final OrderToItemsService orderToItemsService;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository,
                            OrderDetailService orderDetailService, CustomerService customerService,
                            ItemService itemService, OrderToItemsService orderToItemsService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderDetailService = orderDetailService;
        this.customerService = customerService;
        this.itemService = itemService;
        this.orderToItemsService = orderToItemsService;
    }

    @Override
    public OrderModel getOrderForUser(Long userid) {
        OrderModel orderModelToReturn = new OrderModel();
        List<UserModel> userModels = this.userRepository.findAll();
        Optional<UserModel> userModel =
                userModels.stream().filter(user -> user.getId().equals(userid)).findFirst();
        if( userModel.isPresent())
        {
            UserModel user = userModel.get();
            List<OrderModel> orderModels = this.orderRepository.findAll();
            Optional<OrderModel> orderModel =
                    orderModels.stream().filter(order -> order.getUser().equals(user)).findFirst();
            if(orderModel.isPresent())
            {
                orderModelToReturn = orderModel.get();
            }
        }
        return orderModelToReturn;
    }

    @Override
    public OrderModel getOrderById(Long id) {
        OrderModel orderModelToReturn = new OrderModel();
        List<OrderModel> orderModels = this.orderRepository.findAll();
        Optional<OrderModel> orderModel =
                orderModels.stream().filter(order -> order.getId().equals(id)).findFirst();
        if (orderModel.isPresent()) {
            orderModelToReturn = orderModel.get();
        }
        return orderModelToReturn;
    }

    @Override
    public void addOrder(OrderModel orderModel) {
        OrderDetailModel orderDetail = orderModel.getOrderdetail();
        this.orderDetailService.addOrderDetail(orderDetail);

        ItemModel itemModel = orderModel.getItem();
        this.itemService.addItem(itemModel);

        UserModel user = orderModel.getUser();
        this.customerService.registerUser(user);

        List<OrderModel> orderModels = this.orderRepository.findAll();
        Optional<OrderModel> matchingFound =
                orderModels.stream().filter(order -> order.equals(orderModel)).findFirst();
        if (matchingFound.isPresent()) {
            orderModel.setId(matchingFound.get().getId());
        }
        this.orderRepository.save(orderModel);

        OrderToItemsModel orderToItemsModel = new OrderToItemsModel();
        orderToItemsModel.setItem(itemModel);
        orderToItemsModel.setOrderdetail(orderDetail);
        this.orderToItemsService.addEntry(orderToItemsModel);
    }

    @Override
    public List<OrderModel> getAllOrders() {
        List<OrderModel> orderModels = this.orderRepository.findAll();
        return orderModels;
    }

}

