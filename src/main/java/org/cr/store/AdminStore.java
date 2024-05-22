package org.cr.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cr.model.user.Admin;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminStore implements BaseStore {

    final String path = "D:/Temp/Freelance/car-rent-system/admin.txt";

    private HashMap<String, Admin> map;

    @Override
    public AdminStore get(String id) {
        // retrieve data from Admin text file
        // OutputStream to map
        // if map is empty, initialise a new one
        return null;
    }

}
