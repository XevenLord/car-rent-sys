package org.cr.business;

import org.cr.model.Payment;

import java.util.List;

public interface PaymentBsn {

    void create(Payment payment);

    Payment update(Payment payment);

    Payment get(String id);

    Payment delete(String id);

    List<Payment> getAll();

}
