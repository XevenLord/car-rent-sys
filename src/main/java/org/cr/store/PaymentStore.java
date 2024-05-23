package org.cr.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cr.model.Payment;

import java.io.*;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentStore implements BaseStore {

    final String path = "D:/Temp/Freelance/car-rent-system/payment.txt";

    private HashMap<String, Payment> map;

    @Override
    public PaymentStore get() {
        readFromFile();
        return this;
    }

    private void readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            map = (HashMap<String, Payment>) ois.readObject();
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

    public void addPayment(Payment payment) {
        if (getPayment(payment.getId()) != null) {
            System.out.println("WARNING: Payment exists");
            return;
        }
        map.put(payment.getId(), payment);
        saveToFile();
    }

    public Payment getPayment(String id) {
        return map.get(id);
    }

    public void removePayment(String id) {
        map.remove(id);
        saveToFile();
    }

}
