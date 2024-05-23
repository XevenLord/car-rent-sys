package org.cr;

import org.cr.model.user.Admin;
import org.cr.store.AdminStore;
public class Main {
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

        System.out.println("adminStore get admin");
        System.out.println(adminStore.getAdmin("1"));

    }
}