package com.turka.acer.login_register_mvp_relam.model;

import android.text.TextUtils;

import com.turka.acer.login_register_mvp_relam.interfaces.ISignUp;
import com.turka.acer.login_register_mvp_relam.utils.GlobalConstants;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class SignUpModel extends RealmObject implements ISignUp {

    private String firstName;
    private String lastName;

    @Required
    @PrimaryKey
    private String email;
    private String password;
    private String imageUri;

    public SignUpModel() {
    }

    public SignUpModel(String firstName, String lastName, String email, String password, String imageUri) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.imageUri = imageUri;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
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
    public String getImageUri() {
        return imageUri;
    }

    @Override
    public int isValidInfo() {

        if(TextUtils.isEmpty(getFirstName()))
            return GlobalConstants.INVALID_FIRST_NAME;

        else if(TextUtils.isEmpty(getLastName()))
            return GlobalConstants.INVALID_LAST_NAME;

        else if(TextUtils.isEmpty(getEmail()) || !android.util.Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches())
            return GlobalConstants.INVALID_EMAIL;

        else if(TextUtils.isEmpty(getPassword()) && getPassword().length() < 6)
            return GlobalConstants.INVALID_PASSWORD;

        else
            return GlobalConstants.VALID_ALL;
    }
}
