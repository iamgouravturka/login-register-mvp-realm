package com.turka.acer.login_register_mvp_relam.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.turka.acer.login_register_mvp_relam.R;
import com.turka.acer.login_register_mvp_relam.presenter.LoginPresenter;
import com.turka.acer.login_register_mvp_relam.ui.activity.MainActivity;
import com.turka.acer.login_register_mvp_relam.utils.GlobalConstants;
import com.turka.acer.login_register_mvp_relam.view.ILoginView;

import java.util.Objects;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements ILoginView {

    public static final String TAG = LoginFragment.class.getName();
    @BindView(R.id.txt_input_email)
    TextInputEditText txtInputEmail;
    @BindView(R.id.txt_input_password)
    TextInputEditText txtInputPassword;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.txt_register)
    public void onRegisterViewClicked() {
        ((MainActivity) Objects.requireNonNull(getContext())).callFragment(GlobalConstants.FRAG_SIGN_UP, new SignUpFragment(), true);
    }

    @OnClick(R.id.btn_login)
    public void onLoginViewClicked() {

        new LoginPresenter(this, getActivity()).doLogin(
                Objects.requireNonNull(txtInputEmail.getText()).toString(),
                Objects.requireNonNull(txtInputPassword.getText()).toString()
        );
    }


    @Override
    public void onLoginSuccess(String message) {
        ((MainActivity) Objects.requireNonNull(getContext())).callMainActivity();
    }

    @Override
    public void onLoginFailure(String error) {
        Toasty.error(Objects.requireNonNull(getContext()), error).show();
    }
}
