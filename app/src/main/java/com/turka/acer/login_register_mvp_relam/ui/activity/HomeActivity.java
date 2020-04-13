package com.turka.acer.login_register_mvp_relam.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.turka.acer.login_register_mvp_relam.R;
import com.turka.acer.login_register_mvp_relam.ui.fragment.HomeFragment;
import com.turka.acer.login_register_mvp_relam.utils.GlobalConstants;
import com.turka.acer.login_register_mvp_relam.utils.RealmController;

public class HomeActivity extends AppCompatActivity {

    private final String TAG = HomeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        callFragment(new HomeFragment(), GlobalConstants.FRAG_HOME, false);
    }

    public void callFragment(Fragment fragment, String tag, boolean addToBackStack){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.home_container, fragment, tag);

        if(addToBackStack)
            fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    public void logoutUser(){
        RealmController.with().loggedOutUser();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    //--------------------------------------------Interfaces----------------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
