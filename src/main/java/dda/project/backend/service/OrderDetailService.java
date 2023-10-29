/* **********************************************************************
 * Copyright 2023 VMware, Inc.  All rights reserved. VMware Confidential
 * *********************************************************************/

package dda.project.backend.service;

import dda.project.backend.entity.ItemModel;
import dda.project.backend.entity.OrderDetailModel;

public interface OrderDetailService {
    OrderDetailModel getOrderDetailForId(Long orderDetailId);
    void addOrderDetail(OrderDetailModel orderDetailModel);
}

