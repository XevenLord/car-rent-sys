package org.cr.store;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cr.model.user.Customer;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerStore {

    private HashMap<String, Customer> map;

}
