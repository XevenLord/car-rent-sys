package org.cr.store;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cr.model.user.Customer;

import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerStore implements BaseStore {

    final String path = "D:/Temp/Freelance/car-rent-system/customer.txt";

    private HashMap<String, Customer> map;

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Object get(String id) {
        return null;
    }

    @Override
    public void add(Object entt) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(Object entt) {

    }

}
