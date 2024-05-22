package org.cr.store;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cr.model.Car;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarStore {

    private HashMap<String, Car> map;

}
