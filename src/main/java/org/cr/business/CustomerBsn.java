package org.cr.business;

import org.cr.model.user.Customer;

import java.util.List;

public interface CustomerBsn {

    void create(Customer customer);

    Customer update(Customer customer);

    Customer get(String id);

    Customer delete(String id);

    List<Customer> getAll();

}
