/* **********************************************************************
 * Copyright 2023 VMware, Inc.  All rights reserved. VMware Confidential
 * *********************************************************************/

package dda.project.backend.service;

import dda.project.backend.entity.OrderToItemsModel;

import java.util.List;

public interface OrderToItemsService {
    OrderToItemsModel getEntryForId(Long id);
    List<OrderToItemsModel> getEntriesForOrderDetailId(Long orderDetailId);
    List<OrderToItemsModel> getEntriesForItemId(Long ItemId);
    void addEntry(OrderToItemsModel orderToItemsModel);
}

