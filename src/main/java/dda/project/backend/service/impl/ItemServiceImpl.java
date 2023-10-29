/* **********************************************************************
 * Copyright 2023 VMware, Inc.  All rights reserved. VMware Confidential
 * *********************************************************************/

package dda.project.backend.service.impl;

import dda.project.backend.entity.AddressModel;
import dda.project.backend.entity.ItemModel;
import dda.project.backend.entity.OrderModel;
import dda.project.backend.repository.ItemRepository;
import dda.project.backend.repository.OrderRepository;
import dda.project.backend.repository.UserRepository;
import dda.project.backend.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public ItemServiceImpl(OrderRepository orderRepository, UserRepository userRepository,
                           ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }


    @Override
    public ItemModel getItemForId(Long itemId) {
        List<ItemModel> itemModels = this.itemRepository.findAll();
        Optional<ItemModel> itemModel =
                itemModels.stream().filter(item -> item.getId().equals(itemId)).findFirst();
        return itemModel.isPresent() ? itemModel.get() : new ItemModel();
    }

    @Override
    public void addItem(ItemModel itemModel) {
        List<ItemModel> itemModels = this.itemRepository.findAll();
        Optional<ItemModel> matchingFound =
                itemModels.stream().filter(item -> item.equals(itemModel)).findFirst();
        if (matchingFound.isPresent()) {
            itemModel.setId(matchingFound.get().getId());
        }
        this.itemRepository.save(itemModel);
    }

    @Override
    public List<ItemModel> getAllItem() {
        List<ItemModel> itemModels = this.itemRepository.findAll();
        return itemModels;
    }
}

