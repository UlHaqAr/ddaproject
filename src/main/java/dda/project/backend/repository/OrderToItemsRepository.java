/* **********************************************************************
 * Copyright 2023 VMware, Inc.  All rights reserved. VMware Confidential
 * *********************************************************************/

package dda.project.backend.repository;

import dda.project.backend.entity.ItemModel;
import dda.project.backend.entity.OrderToItemsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderToItemsRepository extends JpaRepository<OrderToItemsModel,Long> {}

