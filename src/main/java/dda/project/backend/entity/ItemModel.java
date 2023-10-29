/* **********************************************************************
 * Copyright 2023 VMware, Inc.  All rights reserved. VMware Confidential
 * *********************************************************************/

package dda.project.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table
public class ItemModel {
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ItemModel itemModel = (ItemModel) o;
        return itemtype == itemModel.itemtype && itemquantity == itemModel.itemquantity
                && itemprice == itemModel.itemprice && itemname.equals(itemModel.itemname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemtype, itemquantity, itemname, itemprice);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private int itemtype;

    @Column
    private int itemquantity;

    @Column
    private String itemname;

    @Column
    private int itemprice;

    public ItemModel() {
    }

    public ItemModel(Long id, int itemtype, int itemquantity, String itemname, int itemprice) {
        this.id = id;
        this.itemtype = itemtype;
        this.itemquantity = itemquantity;
        this.itemname = itemname;
        this.itemprice = itemprice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getItemtype() {
        return itemtype;
    }

    public void setItemtype(int itemtype) {
        this.itemtype = itemtype;
    }

    public int getItemquantity() {
        return itemquantity;
    }

    public void setItemquantity(int itemquantity) {
        this.itemquantity = itemquantity;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public int getItemprice() {
        return itemprice;
    }

    public void setItemprice(int itemprice) {
        this.itemprice = itemprice;
    }
}

