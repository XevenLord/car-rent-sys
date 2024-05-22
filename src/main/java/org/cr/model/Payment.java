package org.cr.model;

import org.cr.model.user.Customer;

import java.io.Serializable;
import java.math.BigDecimal;

public class Payment implements Serializable {

    private String id;

    private Customer customer;

    private BigDecimal amount;

}
