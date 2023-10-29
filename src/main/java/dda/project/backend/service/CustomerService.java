/* **********************************************************************
 * Copyright 2023 VMware, Inc.  All rights reserved. VMware Confidential
 * *********************************************************************/

package dda.project.backend.service;

import dda.project.backend.entity.CustomerModel;
import dda.project.backend.entity.OrderModel;
import dda.project.backend.entity.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CustomerService {

    void saveCustomer(CustomerModel customerModel);
    CustomerModel getCustomer(Long id);
    CustomerModel getCustomer(String name);

    boolean loginAndGetUserDetails(UserModel userModel);

    UserModel getUserById(Long userid);

    void registerUser(UserModel userModel);

    List<CustomerModel> getAllCustomer();
}
