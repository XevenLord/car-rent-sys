package org.cr.store;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cr.model.Car;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarStore implements BaseStore {

    final String path = "C:/Users/Admin/Projects/car-rent-sys/car.txt";

    private HashMap<String, Car> map;

    @Override
    public Object get() {
        return null;
    }
}
