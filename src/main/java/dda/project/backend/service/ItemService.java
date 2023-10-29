/* **********************************************************************
 * Copyright 2023 VMware, Inc.  All rights reserved. VMware Confidential
 * *********************************************************************/

package dda.project.backend.service;

import dda.project.backend.entity.ItemModel;
import dda.project.backend.entity.OrderModel;

import java.util.List;

public interface ItemService {
    ItemModel getItemForId(Long itemId);
    void addItem(ItemModel itemModel);

    List<ItemModel> getAllItem();
}

