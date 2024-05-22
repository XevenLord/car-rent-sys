package org.cr.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cr.model.Booking;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingStore {

    private HashMap<String, Booking> map;

}
