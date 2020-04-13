package com.turka.acer.login_register_mvp_relam.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.turka.acer.login_register_mvp_relam.R;
import com.turka.acer.login_register_mvp_relam.model.LoggedInModel;
import com.turka.acer.login_register_mvp_relam.utils.RealmController;

import java.util.Objects;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileFragment extends Fragment {

    public static final String TAG = EditProfileFragment.class.getName();
    @BindView(R.id.circle_img)
    CircleImageView circleImg;
    @BindView(R.id.txt_input_first_name)
    TextInputEditText txtInputFirstName;
    @BindView(R.id.txt_input_last_name)
    TextInputEditText txtInputLastName;
    @BindView(R.id.txt_input_email)
    TextInputEditText txtInputEmail;
    @BindView(R.id.txt_input_password)
    TextInputEditText txtInputPassword;

    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        ButterKnife.bind(this, view);

        initViews();

        return view;
    }

    private void initViews() {
        LoggedInModel loggedInModel = RealmController.with().loggedInUserData();

        if(!loggedInModel.getImageUri().equals(""))
            circleImg.setImageURI(Uri.parse(loggedInModel.getImageUri()));

        txtInputFirstName.setText(loggedInModel.getFirstName());
        txtInputLastName.setText(loggedInModel.getLastName());
        txtInputEmail.setText(loggedInModel.getEmail());
    }

    @OnClick(R.id.btn_save)
    public void onSaveViewClicked() {
        Objects.requireNonNull(getActivity()).onBackPressed();
    }
}
