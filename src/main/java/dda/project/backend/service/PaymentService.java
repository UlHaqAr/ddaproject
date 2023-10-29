/* **********************************************************************
 * Copyright 2023 VMware, Inc.  All rights reserved. VMware Confidential
 * *********************************************************************/

package dda.project.backend.service;

import dda.project.backend.entity.ItemModel;
import dda.project.backend.entity.PaymentModel;

import java.util.List;

public interface PaymentService {
    PaymentModel getPaymentForId(Long payId);
    void addPayment(PaymentModel paymentModel);
    void updatePayment(Long payId, PaymentModel newModel);

    PaymentModel getPaymentForOrderId(Long orderId);

    List<PaymentModel> getAllPayment();
}

