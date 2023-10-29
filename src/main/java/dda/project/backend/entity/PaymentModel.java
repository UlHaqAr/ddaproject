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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Table
@Entity
public class PaymentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToOne
    @JoinColumn(name = "orderid")
    private OrderModel order;

    @Column
    private int amount;

    @Column
    private int paystatus;

    @Column
    private int paytype;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserModel user;

    public PaymentModel() {
    }

    public PaymentModel(Long id, OrderModel order, int amount, int paystatus, int paytype,
                        UserModel user) {
        this.id = id;
        this.order = order;
        this.amount = amount;
        this.paystatus = paystatus;
        this.paytype = paytype;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaymentModel that = (PaymentModel) o;
        return amount == that.amount && paystatus == that.paystatus && paytype == that.paytype
                && order.equals(that.order) && user.equals(that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, amount, paystatus, paytype, user);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(int paystatus) {
        this.paystatus = paystatus;
    }

    public int getPaytype() {
        return paytype;
    }

    public void setPaytype(int paytype) {
        this.paytype = paytype;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}

