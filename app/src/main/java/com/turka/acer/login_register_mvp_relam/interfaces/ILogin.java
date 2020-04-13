package com.turka.acer.login_register_mvp_relam.interfaces;

public interface ILogin {
    String getEmail();
    String getPassword();

    int isValidInfo();
}
