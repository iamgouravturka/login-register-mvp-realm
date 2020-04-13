package com.turka.acer.login_register_mvp_relam.presenter;
import android.app.Activity;

import com.turka.acer.login_register_mvp_relam.model.LoggedInModel;
import com.turka.acer.login_register_mvp_relam.model.SignUpModel;
import com.turka.acer.login_register_mvp_relam.utils.DecodeMessage;
import com.turka.acer.login_register_mvp_relam.utils.GlobalConstants;
import com.turka.acer.login_register_mvp_relam.utils.RealmController;
import com.turka.acer.login_register_mvp_relam.view.ISignUpView;

public class SignUpPresenter implements ISignUpPresenter{

    private ISignUpView iSignUpView;
    private Activity activity;

    public SignUpPresenter(ISignUpView iSignUpView, Activity activity) {
        this.iSignUpView = iSignUpView;
        this.activity = activity;
    }

    @Override
    public void doSignUp(String firstName, String lastName, String email, String password, String imageUri) {
        SignUpModel signUpModel = new SignUpModel(firstName, lastName, email, password, imageUri);
        int isSignUpSuccess = signUpModel.isValidInfo();

        if(isSignUpSuccess == GlobalConstants.VALID_ALL) {

            //-------------------If validations are true------------------

            //----------------------Store Data To Realm------------
            RealmController.with().addUser(signUpModel);

            //----------------------Set Logged in user-------------
            RealmController.with().loggedInUser(new LoggedInModel(firstName, lastName, email, password, imageUri));

            //----------------------Send Data to SignUp Fragment----------
            iSignUpView.onSignUpResult("SignUp Success");
        } else {

            //--------------------If not first decode the message and send the next screen----------
            String msg = DecodeMessage.msg(isSignUpSuccess);
            iSignUpView.onSignUpError(msg);
        }
    }
}
