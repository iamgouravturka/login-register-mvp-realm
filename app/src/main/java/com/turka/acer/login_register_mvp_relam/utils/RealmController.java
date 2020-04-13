package com.turka.acer.login_register_mvp_relam.utils;

import android.util.Log;

import com.turka.acer.login_register_mvp_relam.model.LoggedInModel;
import com.turka.acer.login_register_mvp_relam.model.SignUpModel;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmController {

    private static final String TAG = RealmController.class.getName();

    //------------------Realm storage path-----------------
    ///data/data/com.turka.acer.login_register_mvp_relam/files/mvp_app_2.realm

    private static RealmController instance;
    private final Realm realm;

    private RealmController() {
        realm = Realm.getInstance(new RealmConfiguration.Builder()
                .name("my_relam")
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(2)
                .build());

        Log.d(TAG, "RealmController: ");
    }

//    public static RealmController with(Fragment fragment) {
//
//        if (instance == null) {
//            instance = new RealmController(Objects.requireNonNull(fragment.getActivity()).getApplication());
//        }
//        return instance;
//    }

    public static RealmController with() {

        if (instance == null) {
            instance = new RealmController();
        }
        return instance;
    }

//    public static RealmController with(Application application) {
//
//        if (instance == null) {
//            instance = new RealmController(application);
//        }
//        return instance;
//    }
//
//    public static RealmController getInstance() {
//
//        return instance;
//    }

//    public Realm getRealm() {
//
//        return realm;
//    }

    //Refresh the realm instance
//    public void refresh() {
//
//        realm.refresh();
//    }

    //clear all objects from SignUpModel.class
//    public void clearAll() {
//
//        realm.beginTransaction();
//        RealmResults<SignUpModel> signUpModels = realm.where(SignUpModel.class).findAll();
//        signUpModels.deleteAllFromRealm();
//        realm.commitTransaction();
//    }

    public void addUser(SignUpModel signUpModel){
        realm.beginTransaction();
        realm.copyToRealm(signUpModel);
        realm.commitTransaction();
    }

    public void loggedInUser(LoggedInModel loggedInModel){
        realm.beginTransaction();
        RealmResults<LoggedInModel> signUpModels = realm.where(LoggedInModel.class).findAll();
        signUpModels.deleteAllFromRealm();
        realm.copyToRealm(loggedInModel);
        realm.commitTransaction();
    }

    public void loggedOutUser(){
        realm.beginTransaction();
        RealmResults<LoggedInModel> signUpModels = realm.where(LoggedInModel.class).findAll();
        signUpModels.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public LoggedInModel loggedInUserData(){
        RealmResults<LoggedInModel> loggedInModels = realm.where(LoggedInModel.class).findAll();
        return loggedInModels.get(0);
    }


//    public void clearLoggedUser(){
//        realm.beginTransaction();
//        RealmResults<LoggedInModel> signUpModels = realm.where(LoggedInModel.class).findAll();
//        signUpModels.deleteAllFromRealm();
//        realm.commitTransaction();
//    }

    //find all objects in the SignUpModel.class
//    public RealmResults<SignUpModel> getUsers() {
//
//        return realm.where(SignUpModel.class).findAll();
//    }

    //query a single item with the given id
    public LoggedInModel getUser(String email, String password) {

        SignUpModel signUpModel = realm.where(SignUpModel.class)
                .equalTo("email", email)
                .and()
                .equalTo("password", password)
                .findFirst();

        assert signUpModel != null;
        return new LoggedInModel(signUpModel.getFirstName(), signUpModel.getLastName(), signUpModel.getEmail(), signUpModel.getPassword(), signUpModel.getImageUri());
    }

    //check if SignUpModel.class is empty
    public boolean isUserLoggedIn() {

        RealmResults<LoggedInModel> loggedInModels = realm.where(LoggedInModel.class).findAll();

        try {
            return loggedInModels.get(0) != null;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    //query example
//    public RealmResults<SignUpModel> queryBooks() {
//
//        return realm.where(SignUpModel.class)
//                .contains("author", "Author 0")
//                .or()
//                .contains("title", "Realm")
//                .findAll();
//
//    }
}