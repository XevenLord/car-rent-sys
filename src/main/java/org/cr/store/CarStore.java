package org.cr.store;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cr.model.Car;

import java.io.*;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarStore implements BaseStore {

    final String path = "C:/Users/Admin/Projects/car-rent-sys/car.txt";

    private HashMap<String, Car> map;

    @Override
    public CarStore get() {
        readFromFile();
        return this;
    }

    private void readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            map = (HashMap<String, Car>) ois.readObject();
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

    public void addCar(Car car) {
        if (getCar(car.getId()) != null) {
            System.out.println("WARNING: Car exists");
            return;
        }
        map.put(car.getId(), car);
        saveToFile();
    }

    public Car getCar(String id) {
        return map.get(id);
    }

    public void removeCar(String id) {
        map.remove(id);
        saveToFile();
    }

}
