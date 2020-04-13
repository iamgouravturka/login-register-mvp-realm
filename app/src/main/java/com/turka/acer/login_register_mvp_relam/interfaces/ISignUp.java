package com.turka.acer.login_register_mvp_relam.interfaces;

public interface ISignUp {
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPassword();
    String getImageUri();

    int isValidInfo();
}
