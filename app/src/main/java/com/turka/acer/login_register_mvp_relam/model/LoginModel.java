package com.turka.acer.login_register_mvp_relam.model;

import android.text.TextUtils;

import com.turka.acer.login_register_mvp_relam.interfaces.ILogin;
import com.turka.acer.login_register_mvp_relam.utils.GlobalConstants;

import io.realm.RealmObject;

public class LoginModel extends RealmObject implements ILogin {

    private String email;
    private String password;

    public LoginModel() {
    }

    public LoginModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int isValidInfo() {

        if(TextUtils.isEmpty(getEmail()) || !android.util.Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches())
            return GlobalConstants.INVALID_EMAIL;

        else if(TextUtils.isEmpty(getPassword()) && getPassword().length() < 6)
            return GlobalConstants.INVALID_PASSWORD;

        else
            return GlobalConstants.VALID_ALL;
    }
}
