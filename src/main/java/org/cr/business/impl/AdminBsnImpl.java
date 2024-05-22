package org.cr.business.impl;

import org.cr.business.AdminBsn;
import org.cr.model.user.Admin;

public class AdminBsnImpl implements AdminBsn {

    final String path = "D:/Temp/Freelance/car-rent-system/admin.txt";

    @Override
    public void create(Admin admin) {
        // add into map database
        // map database converted to string thru objMprUtil
    }

    @Override
    public Admin get(String id) {
        return null;
    }
}
