package org.cr.business;

public interface AuthBsn {

    void login(String email, String pw);

    void logout(String email);

}
