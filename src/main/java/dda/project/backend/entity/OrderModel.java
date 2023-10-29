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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToOne
    @JoinColumn(name = "orderdetailid")
    private OrderDetailModel orderdetail;

    @Column
    private int ordertype;

    @Column
    private int orderstatus;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserModel user;

    public ItemModel getItem() {
        return item;
    }

    public void setItem(ItemModel item) {
        this.item = item;
    }

    @OneToOne
    @JoinColumn(name = "itemid")
    private ItemModel item;

    public OrderModel() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderModel that = (OrderModel) o;
        return ordertype == that.ordertype && orderstatus == that.orderstatus && orderdetail.equals(
                that.orderdetail) && user.equals(that.user) && item.equals(that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderdetail, ordertype, orderstatus, user, item);
    }

    public OrderModel(Long id, OrderDetailModel orderdetail, int ordertype, int orderstatus,
                      UserModel user, ItemModel item) {
        this.id = id;
        this.orderdetail = orderdetail;
        this.ordertype = ordertype;
        this.orderstatus = orderstatus;
        this.user = user;
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

    public int getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(int ordertype) {
        this.ordertype = ordertype;
    }

    public int getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(int orderstatus) {
        this.orderstatus = orderstatus;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}

