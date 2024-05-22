package org.cr.business;

import org.cr.model.Booking;

import java.util.List;

public interface BookingBsn {

    void add(Booking booking);

    Booking update(Booking booking);

    Booking get(String id);

    List<Booking> getAll();

    Booking delete(String id);

}
