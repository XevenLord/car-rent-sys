package org.cr.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cr.model.Booking;

import java.io.*;
import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingStore implements BaseStore {

    final String path = "D:/Temp/Freelance/car-rent-system/booking.txt";

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

    public Booking getBooking(String id) {
        return map.get(id);
    }

    public void removeBooking(String id) {
        map.remove(id);
        saveToFile();
    }

}
