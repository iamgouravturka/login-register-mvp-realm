package com.turka.acer.login_register_mvp_relam.model;

import android.text.TextUtils;

import com.turka.acer.login_register_mvp_relam.interfaces.ISignUp;
import com.turka.acer.login_register_mvp_relam.utils.GlobalConstants;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class LoggedInModel extends RealmObject {

    private String firstName;
    private String lastName;

    @Required
    @PrimaryKey
    private String email;
    private String password;
    private String imageUri;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public LoggedInModel() {
    }

    public LoggedInModel(String firstName, String lastName, String email, String password, String imageUri) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.imageUri = imageUri;
    }


}
