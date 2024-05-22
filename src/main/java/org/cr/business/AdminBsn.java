package org.cr.business;

import org.cr.model.user.Admin;

public interface AdminBsn {

    void create(Admin admin);

    Admin get(String id);

}
