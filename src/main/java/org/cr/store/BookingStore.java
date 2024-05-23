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
    public Object get() {
        return this;
    }
}
