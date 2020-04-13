package com.turka.acer.login_register_mvp_relam.presenter;

import android.app.Activity;
import android.util.Log;

import com.turka.acer.login_register_mvp_relam.model.LoggedInModel;
import com.turka.acer.login_register_mvp_relam.model.LoginModel;
import com.turka.acer.login_register_mvp_relam.utils.DecodeMessage;
import com.turka.acer.login_register_mvp_relam.utils.GlobalConstants;
import com.turka.acer.login_register_mvp_relam.utils.RealmController;
import com.turka.acer.login_register_mvp_relam.view.ILoginView;

import static com.turka.acer.login_register_mvp_relam.ui.fragment.LoginFragment.TAG;

public class LoginPresenter implements ILoginPresenter {

    private ILoginView iLoginView;

    public LoginPresenter(ILoginView iLoginView, Activity activity) {
        this.iLoginView = iLoginView;
    }

    @Override
    public void doLogin(String username, String password) {

        int loginStatus = new LoginModel(username, password).isValidInfo();

        if(loginStatus == GlobalConstants.VALID_ALL) {
            try {

                //---------------------Checking if user is avaliable or not-------------------------
                LoggedInModel loggedInModel = RealmController.with().getUser(username, password);

                //---------------------If user is exist than save to logged in table----------------
                RealmController.with().loggedInUser(loggedInModel);

                iLoginView.onLoginSuccess("User Login Success");
            } catch (Exception e) {
                Log.d(TAG, "doLogin: " + e.getLocalizedMessage());
                iLoginView.onLoginFailure("Login Failed");
                e.printStackTrace();
            }
        }else {
            iLoginView.onLoginFailure(DecodeMessage.msg(loginStatus));
        }
    }
}
