/* **********************************************************************
 * Copyright 2023 VMware, Inc.  All rights reserved. VMware Confidential
 * *********************************************************************/

package dda.project.backend.service;

import dda.project.backend.entity.AddressModel;
import dda.project.backend.entity.CustomerModel;

import java.util.List;

public interface AddressService {

    void saveAddress(AddressModel addressModel);
    AddressModel getAddress(Integer id);

    List<AddressModel> getAllAddress();
}
