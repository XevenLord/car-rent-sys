package org.cr.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cr.config.Config;
import org.cr.enums.CarRentSts;
import org.cr.model.Booking;
import org.cr.model.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingStore implements BaseStore {

    final String path = Config.getProperty("booking.path");

    private HashMap<String, Booking> map;

    @Override
    public BookingStore get() {
        readFromFile();
        return this;
    }

    private void readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            map = (HashMap<String, Booking>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // If file doesn't exist or other error occurs, initialize an empty map
            map = new HashMap<>();
        }
    }

    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(map);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately, e.g., logging
        }
    }

    public void addBooking(Booking booking) {
        if (getBooking(booking.getId()) != null) {
            System.out.println("WARNING: Booking exists");
            return;
        }
        map.put(booking.getId(), booking);
        saveToFile();
    }

    public void updBooking(Booking booking) {
        map.put(booking.getId(), booking);
        saveToFile();
    }

    public Booking getBooking(String id) {
        return map.get(id);
    }

    public void removeBooking(String id) {
        map.remove(id);
        saveToFile();
    }

    public ArrayList<Booking> getAll() {
        return new ArrayList<>(map.values());
    }

    public ArrayList<Booking> getUnbookedCars() {
        return (ArrayList<Booking>) map.values().stream()
                .filter(booking -> CarRentSts.AVAILABLE.toString().equals(booking.isStatus()))
                .collect(Collectors.toList());
    }

    public ArrayList<Booking> getBookedCars() {
        return (ArrayList<Booking>) map.values().stream()
                .filter(booking -> CarRentSts.NON_AVAILABLE.toString().equals(booking.isStatus()))
                .collect(Collectors.toList());
    }

    public ArrayList<Booking> getByCustomerId(String customerId) {
        return (ArrayList<Booking>) map.values().stream()
                .filter(booking -> customerId.equals(booking.getCustomerId()))
                .collect(Collectors.toList());
    }

    public ArrayList<Booking> getByPlateNo(String plateNo) {
        return (ArrayList<Booking>) map.values().stream()
                .filter(booking -> plateNo.equals(booking.getPlateNo()))
                .collect(Collectors.toList());
    }

}
