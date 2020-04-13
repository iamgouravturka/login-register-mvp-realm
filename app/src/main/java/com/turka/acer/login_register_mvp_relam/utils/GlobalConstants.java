package com.turka.acer.login_register_mvp_relam.utils;

import com.turka.acer.login_register_mvp_relam.ui.fragment.EditProfileFragment;
import com.turka.acer.login_register_mvp_relam.ui.fragment.HomeFragment;
import com.turka.acer.login_register_mvp_relam.ui.fragment.LoginFragment;
import com.turka.acer.login_register_mvp_relam.ui.fragment.SignUpFragment;

public class GlobalConstants {

    //------------------------------------Fragment Constants----------------------------------------
    public static final String FRAG_LOGIN = LoginFragment.TAG;
    public static final String FRAG_SIGN_UP = SignUpFragment.TAG;
    public static final String FRAG_HOME = HomeFragment.TAG;
    public static final String FRAG_EDIT_PROFILE = EditProfileFragment.TAG;

    //-------------------------------------Validations----------------------------------------------
    public static final int INVALID_FIRST_NAME = 1;
    public static final int INVALID_LAST_NAME = 2;
    public static final int INVALID_EMAIL = 3;
    public static final int INVALID_PASSWORD = 4;
    public static final int VALID_ALL = 0;

    static final String STR_INVALID_FIRST_NAME = "First Name is invalid";
    static final String STR_INVALID_LAST_NAME = "Last Name is invalid";
    static final String STR_INVALID_EMAIL = "Email is invalid";
    static final String STR_INVALID_PASSWORD = "Password is invalid";

    //-----------------------------Testing Credentials----------------------------------------------
    public static final boolean isTesting = true;
    public static final String STR_FIRST_NAME = "test";
    public static final String STR_LAST_NAME = "test last name";
    public static final String STR_EMAIL = "email@gmail.com";
    public static final String STR_PASSWORD = "qwerty";
}
