package com.turka.acer.login_register_mvp_relam.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.turka.acer.login_register_mvp_relam.R;
import com.turka.acer.login_register_mvp_relam.ui.fragment.LoginFragment;
import com.turka.acer.login_register_mvp_relam.utils.GlobalConstants;
import com.turka.acer.login_register_mvp_relam.utils.RealmController;
import com.turka.acer.login_register_mvp_relam.utils.Utils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //---------------------------------Check if user is already logged in-----------------------
        if(RealmController.with().isUserLoggedIn()){
            callMainActivity();
            return;
        }

        //-----------------------First we are calling login fragment--------------------------------
        callFragment(GlobalConstants.FRAG_LOGIN, new LoginFragment(), false);
    }

    //------------------------This Method We can use form calling another fragment------------------
    public void callFragment(String tag, Fragment fragment, boolean backStack){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.container_main, fragment, tag);

        if(backStack)
            fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }

    public void callMainActivity(){
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            if(data != null)
                Log.d(TAG, "onActivityResult: " + Utils.saveFile(data, this));
        }
    }

    //-----------------------------------Interfaces-------------------------------------------------
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
