package com.turka.acer.login_register_mvp_relam.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.turka.acer.login_register_mvp_relam.R;
import com.turka.acer.login_register_mvp_relam.model.LoggedInModel;
import com.turka.acer.login_register_mvp_relam.ui.activity.HomeActivity;
import com.turka.acer.login_register_mvp_relam.utils.GlobalConstants;
import com.turka.acer.login_register_mvp_relam.utils.RealmController;

import java.util.Objects;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public static final String TAG = HomeFragment.class.getName();
    @BindView(R.id.circle_img)
    CircleImageView circleImg;
    @BindView(R.id.txt_input_first_name)
    TextView txtInputFirstName;
    @BindView(R.id.txt_input_last_name)
    TextView txtInputLastName;
    @BindView(R.id.txt_input_email)
    TextView txtInputEmail;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

        setValues();

        return view;
    }

    private void setValues() {
        //------------------------------------Get Data from realm database--------------------------
        LoggedInModel loggedInModel = RealmController.with().loggedInUserData();

        txtInputFirstName.setText(loggedInModel.getFirstName());
        txtInputLastName.setText(loggedInModel.getLastName());
        txtInputEmail.setText(loggedInModel.getEmail());

        if (!loggedInModel.getImageUri().equals(""))
            circleImg.setImageURI(Uri.parse(loggedInModel.getImageUri()));
    }

    @OnClick(R.id.txt_edit_profile)
    public void onViewClicked() {
        ((HomeActivity) Objects.requireNonNull(getContext())).callFragment(new EditProfileFragment(), GlobalConstants.FRAG_EDIT_PROFILE, true);
    }

    @OnClick(R.id.txt_logout_profile)
    public void onLogoutViewClicked() {
        ((HomeActivity) Objects.requireNonNull(getContext())).logoutUser();
    }
}
