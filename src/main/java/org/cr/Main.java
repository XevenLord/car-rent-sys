package org.cr;

import org.cr.enums.CarRentSts;
import org.cr.model.Car;
import org.cr.model.user.Admin;
import org.cr.store.AdminStore;
import org.cr.store.CarStore;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final JFrame frame = new JFrame();

    public static void main(String[] args) {
        AdminStore adminStore = new AdminStore().get();
        Admin superadmin = (Admin) Admin.builder()
                                .id("1")
                                .name("superadmin")
                                .email("superadmin@gmail.com")
                                .pw("superadmin")
                                .build();

        adminStore.addAdmin(superadmin);
        adminStore.saveToFile();
        adminStore = adminStore.get();

        CarStore carStore = new CarStore().get();
        Car car1 = new Car().builder()
                        .id("1")
                        .plateNo("ANE4313")
                        .rentSts(CarRentSts.AVAILABLE.toString()).build();
        Car car2 = new Car().builder()
                .id("2")
                .plateNo("PKH9552")
                .rentSts(CarRentSts.AVAILABLE.toString()).build();
        Car car3 = new Car().builder()
                .id("3")
                .plateNo("ANE8873")
                .rentSts(CarRentSts.AVAILABLE.toString()).build();
        Car car4 = new Car().builder()
                .id("4")
                .plateNo("WPR1328")
                .rentSts(CarRentSts.AVAILABLE.toString()).build();
        Car car5 = new Car().builder()
                .id("5")
                .plateNo("DEV1223")
                .rentSts(CarRentSts.AVAILABLE.toString()).build();
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        cars.add(car5);
        carStore.addCars(cars);

        System.out.println(carStore.getCar("3"));

        System.out.println("adminStore get admin");
        System.out.println(adminStore.getAdmin("1"));

    }
}