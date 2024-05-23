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
    public void addAdmin(String id, Admin admin) {
        map.put(id, admin);
    }

    public Admin getAdmin(String id) {
        return map.get(id);
    }

    public void removeAdmin(String id) {
        map.remove(id);
    }

    // Other methods for interacting with the map...

}
