package org.cr.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cr.model.user.Admin;

import java.io.*;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminStore implements BaseStore {

    final String path = "D:/Temp/Freelance/car-rent-system/admin.txt";

    private HashMap<String, Admin> map;

    @Override
    public AdminStore get() {
        // Retrieve data from the text file
        readFromFile();
        return this;
    }

    private void readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            map = (HashMap<String, Admin>) ois.readObject();
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
    public void addAdmin(Admin admin) {
        if (getAdmin(admin.getId()) != null) {
            System.out.println("WARNING: Admin exists");
            return;
        }
        map.put(admin.getId(), admin);
        saveToFile();
    }

    public Admin getAdmin(String id) {
        return map.get(id);
    }

    public void removeAdmin(String id) {
        map.remove(id);
        saveToFile();
    }

    public Admin getByEmail(String email) {
        // Iterate through the map and find the Admin with the given email
        return map.values().stream()
                .filter(admin -> admin.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

}
