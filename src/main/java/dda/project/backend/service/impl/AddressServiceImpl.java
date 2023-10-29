/* **********************************************************************
 * Copyright 2023 VMware, Inc.  All rights reserved. VMware Confidential
 * *********************************************************************/

package dda.project.backend.service.impl;

import dda.project.backend.entity.AddressModel;
import dda.project.backend.entity.CustomerModel;
import dda.project.backend.repository.AddressRepository;
import dda.project.backend.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void saveAddress(AddressModel addressModel) {
        List<AddressModel> addressModels = this.addressRepository.findAll();
        Optional<AddressModel> matchingFound =
                addressModels.stream().filter(address -> address.equals(addressModel)).findFirst();
        if (matchingFound.isPresent()) {
            addressModel.setId(matchingFound.get().getId());
        }
        this.addressRepository.save(addressModel);
    }

    @Override
    public AddressModel getAddress(Integer id) {
        List<AddressModel> addressModels = this.addressRepository.findAll();
        Optional<AddressModel> addressModel =
                addressModels.stream().filter(address -> address.getId().equals(id)).findFirst();
        return addressModel.isPresent() ? addressModel.get() : new AddressModel();
    }

    @Override
    public List<AddressModel> getAllAddress() {
        List<AddressModel> addressModels = this.addressRepository.findAll();
        return addressModels;
    }
}

