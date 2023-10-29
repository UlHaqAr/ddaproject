/* **********************************************************************
 * Copyright 2023 VMware, Inc.  All rights reserved. VMware Confidential
 * *********************************************************************/

package dda.project.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table
public class OrderToItemsModel {

    public OrderToItemsModel() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    public OrderToItemsModel(Long id, OrderDetailModel orderdetail, ItemModel item) {
        this.id = id;
        this.orderdetail = orderdetail;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderDetailModel getOrderdetail() {
        return orderdetail;
    }

    public void setOrderdetail(OrderDetailModel orderdetail) {
        this.orderdetail = orderdetail;
    }

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    @ManyToOne
    @JoinColumn(name = "orderdetailid")
    private OrderDetailModel orderdetail;

    @ManyToOne
    @JoinColumn(name = "itemid")
    private ItemModel item;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderToItemsModel that = (OrderToItemsModel) o;
        return orderdetail.equals(that.orderdetail) && item.equals(that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderdetail, item);
    }
}

