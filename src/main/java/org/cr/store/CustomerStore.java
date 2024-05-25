package org.cr.store;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cr.config.Config;
import org.cr.model.user.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerStore implements BaseStore {

    final String path = Config.getProperty("customer.path");

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
        if (getCustomer(customer.getId()) != null || getByEmail(customer.getEmail()) != null) {
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

    public void updCustomer(Customer customer) {
        map.put(customer.getId(), customer);
    }

    public Customer getCustomerByIC(String ic) {
        return map.values().stream()
                .filter(customer -> ic.equals(customer.getIc()))
                .findFirst().orElse(null);
    }

    public ArrayList<Customer> getCustomerByName(String name) {
        return (ArrayList<Customer>) map.values().stream()
                .filter(customer -> name.equals(customer.getName()))
                .collect(Collectors.toList());
    }

    public ArrayList<Customer> getAll() {
        return new ArrayList<>(map.values());
    }

    public Customer getByEmail(String email) {
        // Iterate through the map and find the Admin with the given email
        return map.values().stream()
                .filter(customer -> customer.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }
}
