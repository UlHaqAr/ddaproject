/* **********************************************************************
 * Copyright 2023 VMware, Inc.  All rights reserved. VMware Confidential
 * *********************************************************************/

package dda.project.backend.service.impl;

import dda.project.backend.entity.ItemModel;
import dda.project.backend.entity.OrderToItemsModel;
import dda.project.backend.repository.OrderToItemsRepository;
import dda.project.backend.service.ItemService;
import dda.project.backend.service.OrderDetailService;
import dda.project.backend.service.OrderService;
import dda.project.backend.service.OrderToItemsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderToItemsServiceImpl implements OrderToItemsService {

    private final OrderToItemsRepository orderToItemsRepository;
    private final OrderDetailService orderDetailService;
    private final ItemService itemService;

    public OrderToItemsServiceImpl(OrderToItemsRepository orderToItemsRepository, OrderDetailService orderDetailService,
                                   ItemService itemService) {
        this.orderToItemsRepository = orderToItemsRepository;
        this.orderDetailService = orderDetailService;
        this.itemService = itemService;
    }

    @Override
    public OrderToItemsModel getEntryForId(Long id) {
        List<OrderToItemsModel> orderToItemsModels = this.orderToItemsRepository.findAll();
        Optional<OrderToItemsModel> orderToItemsModel =
                orderToItemsModels.stream().filter(item -> item.getId().equals(id)).findFirst();
        return orderToItemsModel.isPresent() ? orderToItemsModel.get() : new OrderToItemsModel();
    }

    @Override
    public List<OrderToItemsModel> getEntriesForOrderDetailId(Long orderDetailId) {
        List<OrderToItemsModel> orderToItemsModels = this.orderToItemsRepository.findAll();
        List<OrderToItemsModel> orderToItemsModelList =
                orderToItemsModels.stream().filter(item -> item.getOrderdetail().getId().equals(orderDetailId)).collect(
                        Collectors.toList());
        return orderToItemsModelList.isEmpty() ? new ArrayList<>() :
                orderToItemsModelList;
    }

    @Override
    public List<OrderToItemsModel> getEntriesForItemId(Long itemId) {
        List<OrderToItemsModel> orderToItemsModels = this.orderToItemsRepository.findAll();
        List<OrderToItemsModel> orderToItemsModelList =
                orderToItemsModels.stream().filter(model -> model.getItem().getId().equals(itemId)).collect(
                        Collectors.toList());
        return orderToItemsModelList.isEmpty() ? new ArrayList<>() :
                orderToItemsModelList;
    }

    @Override
    public void addEntry(OrderToItemsModel orderToItemsModel) {
        this.orderDetailService.addOrderDetail(orderToItemsModel.getOrderdetail());
        this.itemService.addItem(orderToItemsModel.getItem());

        List<OrderToItemsModel> orderToItemsModels = this.orderToItemsRepository.findAll();
        Optional<OrderToItemsModel> matchingFound =
                orderToItemsModels.stream().filter(item -> item.equals(orderToItemsModel)).findFirst();
        if (matchingFound.isPresent()) {
            orderToItemsModel.setId(matchingFound.get().getId());
        }
        this.orderToItemsRepository.save(orderToItemsModel);
    }
}

