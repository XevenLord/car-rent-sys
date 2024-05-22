package org.cr.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cr.model.Payment;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStore implements BaseStore {

    final String path = "D:/Temp/Freelance/car-rent-system/payment.txt";

    private HashMap<String, Payment> map;

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
