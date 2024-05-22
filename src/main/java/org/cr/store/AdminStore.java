package org.cr.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.cr.model.user.Admin;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminStore {

    private HashMap<String, Admin> map;

}
