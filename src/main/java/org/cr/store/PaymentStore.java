package org.cr.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cr.model.Booking;
import org.cr.model.Car;
import org.cr.model.Payment;
import org.cr.model.user.Customer;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.UUID;

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

    public int calcBill(Booking booking) {
        CarStore carStore = new CarStore().get();
        CustomerStore customerStore = new CustomerStore().get();

        int hours = (int) ChronoUnit.HOURS.between(booking.getStTm(), booking.getEndTm());

        Car car = carStore.getCar(booking.getPlateNo());
        Customer customer = customerStore.getCustomer(booking.getCustomerId());

        if (car == null) {
            return 0;
        }

        int amount = (int) (car.getRentPerHour() * hours);

        Payment payment = new Payment().builder()
                .id(UUID.randomUUID().toString())
                .amount(new BigDecimal(amount))
                .customer(customer)
                .crtTm(LocalDateTime.now())
                .carPlateNo(car.getPlateNo())
                .build();

        addPayment(payment);
        if (hours != 0) {
            return (int) amount;
        } else {
            return (int) (car.getRentPerHour() * 1);
        }
    }

}
