package org.cr.store;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cr.enums.CarRentSts;
import org.cr.model.Booking;
import org.cr.model.Car;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarStore implements BaseStore {

    final String path = "D:/Temp/Freelance/car-rent-system/car.txt";

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
        if (getCar(car.getPlateNo()) != null) {
            System.out.println("WARNING: Car exists");
            return;
        }
        map.put(car.getPlateNo(), car);
        saveToFile();
    }

    public void addCars(List<Car> cars) {
        cars.forEach(this::addCar);
    }

    public Car getCar(String id) {
        return map.get(id);
    }

    public void removeCar(String id) {
        map.remove(id);
        saveToFile();
    }

    public ArrayList<Car> getAll() {
        return new ArrayList<>(map.values());
    }

    public ArrayList<Car> getByName(String name) {
        return (ArrayList<Car>) map.values().stream()
                .filter(car -> car.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public void updCar(Car car) {
        map.put(car.getPlateNo(), car);
    }

    public ArrayList<Car> getUnbookedCars() {
        return (ArrayList<Car>) map.values().stream()
                .filter(car -> CarRentSts.AVAILABLE.toString().equals(car.getRentSts()))
                .collect(Collectors.toList());
    }

    public ArrayList<Car> getBookedCars() {
        return (ArrayList<Car>) map.values().stream()
                .filter(car -> CarRentSts.NON_AVAILABLE.toString().equals(car.getRentSts()))
                .collect(Collectors.toList());
    }

}
