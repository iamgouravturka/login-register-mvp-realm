package com.turka.acer.login_register_mvp_relam.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.turka.acer.login_register_mvp_relam.R;
import com.turka.acer.login_register_mvp_relam.presenter.SignUpPresenter;
import com.turka.acer.login_register_mvp_relam.ui.activity.MainActivity;
import com.turka.acer.login_register_mvp_relam.utils.GlobalConstants;
import com.turka.acer.login_register_mvp_relam.utils.Utils;
import com.turka.acer.login_register_mvp_relam.view.ISignUpView;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class SignUpFragment extends Fragment implements ISignUpView {

    public static final String TAG = SignUpFragment.class.getName();
    private String imageUri = "";

    @BindView(R.id.txt_input_first_name)
    TextInputEditText txtInputFirstName;
    @BindView(R.id.txt_input_last_name)
    TextInputEditText txtInputLastName;
    @BindView(R.id.txt_input_email)
    TextInputEditText txtInputEmail;
    @BindView(R.id.txt_input_password)
    TextInputEditText txtInputPassword;
    @BindView(R.id.circle_img)
    CircleImageView circleImg;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        ButterKnife.bind(this, view);

        if(GlobalConstants.isTesting)
            testingCredentials();

        return view;
    }

    private void testingCredentials() {
        txtInputFirstName.setText(GlobalConstants.STR_FIRST_NAME);
        txtInputLastName.setText(GlobalConstants.STR_LAST_NAME);
        txtInputEmail.setText(GlobalConstants.STR_EMAIL);
        txtInputPassword.setText(GlobalConstants.STR_PASSWORD);
    }


    @OnClick(R.id.btn_sign_up)
    public void onViewClicked() {
        SignUpPresenter signUpPresenter = new SignUpPresenter(this, getActivity());

        signUpPresenter.doSignUp(
                Objects.requireNonNull(txtInputFirstName.getText()).toString(),
                Objects.requireNonNull(txtInputLastName.getText()).toString(),
                Objects.requireNonNull(txtInputEmail.getText()).toString(),
                Objects.requireNonNull(txtInputPassword.getText()).toString(),
                imageUri
        );
    }

    @SuppressLint("CheckResult")
    @Override
    public void onSignUpResult(String message) {
        ((MainActivity) Objects.requireNonNull(getContext())).callMainActivity();
    }

    @Override
    public void onSignUpError(String msg) {
        printErrorToast(msg);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    private void printErrorToast(String msg) {
        Toasty.error(Objects.requireNonNull(getContext()), msg, Toast.LENGTH_SHORT, false).show();
    }

    @OnClick(R.id.circle_img)
    public void onImageViewClicked() {
        ImagePicker.Companion.with(Objects.requireNonNull(this))
                .crop()                    //Crop image(Optional), Check Customization for more option
                .compress(1024)            //Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d(TAG, "onActivityResult: " + resultCode);

        if (resultCode == Activity.RESULT_OK) {

            //Image Uri will not be null for RESULT_OK
            assert data != null;
            imageUri = Objects.requireNonNull(data.getData()).getPath();
            Uri uri = Uri.parse(imageUri);
            circleImg.setImageURI(uri);

            Log.d(TAG, "onActivityResult: " + Utils.saveFile(data, Objects.requireNonNull(getActivity())));


        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            printErrorToast(ImagePicker.Companion.getError(data));
        } else {
            printErrorToast("Canceled By The User");
        }
    }
}
