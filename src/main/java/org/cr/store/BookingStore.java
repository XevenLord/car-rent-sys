package org.cr.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cr.model.Booking;

import java.util.HashMap;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingStore implements BaseStore {

    final String path = "D:/Temp/Freelance/car-rent-system/booking.txt";

    private HashMap<String, Booking> map;

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
