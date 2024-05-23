package org.cr.store;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cr.model.user.Customer;

import java.io.*;
import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerStore implements BaseStore {

    final String path = "D:/Temp/Freelance/car-rent-system/customer.txt";

    private HashMap<String, Customer> map;

    @Override
    public CustomerStore get() {
        readFromFile();
        return this;
    }

    private void readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            map = (HashMap<String, Customer>) ois.readObject();
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

    // Additional methods to interact with the map as needed
    public void addCustomer(Customer customer) {
        if (getCustomer(customer.getId()) != null) {
            System.out.println("WARNING: Customer exists");
            return;
        }
        map.put(customer.getId(), customer);
        saveToFile();
    }

    public Customer getCustomer(String id) {
        return map.get(id);
    }

    public void removeCustomer(String id) {
        map.remove(id);
        saveToFile();
    }
}
