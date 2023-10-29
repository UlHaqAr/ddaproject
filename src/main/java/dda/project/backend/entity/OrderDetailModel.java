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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table
public class OrderDetailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @OneToOne
    @JoinColumn(name = "senderid")
    private CustomerModel sender;

    @OneToOne
    @JoinColumn(name = "receiverid")
    private CustomerModel receiver;

    @Column
    private int totalamount;

    public OrderDetailModel() {
    }

    public OrderDetailModel(Long id, CustomerModel sender, CustomerModel receiver,
                            int totalamount) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.totalamount = totalamount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderDetailModel that = (OrderDetailModel) o;
        return totalamount == that.totalamount && sender.equals(that.sender) && receiver.equals(
                that.receiver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver, totalamount);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerModel getSender() {
        return sender;
    }

    public void setSender(CustomerModel sender) {
        this.sender = sender;
    }

    public CustomerModel getReceiver() {
        return receiver;
    }

    public void setReceiver(CustomerModel receiver) {
        this.receiver = receiver;
    }

    public int getTotalamount() {
        return totalamount;
    }

    public void setTotalamount(int totalamount) {
        this.totalamount = totalamount;
    }
}

