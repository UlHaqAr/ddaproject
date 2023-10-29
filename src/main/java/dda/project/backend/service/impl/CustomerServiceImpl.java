/* **********************************************************************
 * Copyright 2023 VMware, Inc.  All rights reserved. VMware Confidential
 * *********************************************************************/

package dda.project.backend.service.impl;

import dda.project.backend.entity.AddressModel;
import dda.project.backend.entity.CustomerModel;
import dda.project.backend.entity.OrderDetailModel;
import dda.project.backend.entity.UserModel;
import dda.project.backend.repository.CustomerRepository;
import dda.project.backend.repository.UserRepository;
import dda.project.backend.service.AddressService;
import dda.project.backend.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final AddressService addressService;

    public CustomerServiceImpl(CustomerRepository customerRepository, UserRepository userRepository,
                               AddressService addressService) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.addressService = addressService;
    }

    @Override
    public void saveCustomer(CustomerModel customerModel) {
        this.addressService.saveAddress(customerModel.getAddress());

        List<CustomerModel> customerModels = this.customerRepository.findAll();
        Optional<CustomerModel> matchingFound =
                customerModels.stream().filter(cust -> cust.equals(customerModel)).findFirst();
        if (matchingFound.isPresent()) {
            customerModel.setId(matchingFound.get().getId());
        }
        this.customerRepository.save(customerModel);
    }

    @Override
    public CustomerModel getCustomer(Long id) {
        List<CustomerModel> customerModels = this.customerRepository.findAll();
        Optional<CustomerModel> customerModel =
                customerModels.stream().filter(customer -> customer.getId().equals(id)).findFirst();
        return customerModel.isPresent() ? customerModel.get() : new CustomerModel();
    }

    @Override
    public CustomerModel getCustomer(String name) {
        List<CustomerModel> customerModels = this.customerRepository.findAll();
        Optional<CustomerModel> customerModel =
                customerModels.stream().filter(customer -> customer.getName().equals(name)).findFirst();
        return customerModel.isPresent() ? customerModel.get() : new CustomerModel();
    }

    @Override
    public boolean loginAndGetUserDetails(UserModel userModel) {
        CustomerModel customerModel = new CustomerModel();
        List<UserModel> userModels = this.userRepository.findAll();
        Optional<UserModel> userModel1 =
                userModels.stream().filter(user -> user.getUserName().equals(userModel.getUserName()) && user.getPassword().equals(userModel.getPassword())).findFirst();
        if(userModel1.isPresent())
        {
            return true;
        }
        return false;
    }

    @Override
    public UserModel getUserById(Long userid) {
        List<UserModel> userModels = this.userRepository.findAll();
        Optional<UserModel> userModel =
                userModels.stream().filter(user -> user.getId().equals(userid)).findFirst();
        return userModel.isPresent()? userModel.get(): new UserModel();
    }

    @Override
    public void registerUser(UserModel userModel) {
        List<UserModel> userModels = this.userRepository.findAll();
        Optional<UserModel> matchingFound =
                userModels.stream().filter(user -> user.equals(userModel)).findFirst();
        if (matchingFound.isPresent()) {
            userModel.setId(matchingFound.get().getId());
        }
        this.userRepository.save(userModel);
    }

    @Override
    public List<CustomerModel> getAllCustomer() {
        List<CustomerModel> customerModels = this.customerRepository.findAll();
        return customerModels;
    }
}

