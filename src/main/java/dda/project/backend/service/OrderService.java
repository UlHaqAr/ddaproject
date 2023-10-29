/* **********************************************************************
 * Copyright 2023 VMware, Inc.  All rights reserved. VMware Confidential
 * *********************************************************************/

package dda.project.backend.service;

import dda.project.backend.entity.CustomerModel;
import dda.project.backend.entity.OrderModel;

import java.util.List;

public interface OrderService {
    OrderModel getOrderForUser(Long userid);
    OrderModel getOrderById(Long id);
    void addOrder(OrderModel orderModel);

    List<OrderModel> getAllOrders();
}

