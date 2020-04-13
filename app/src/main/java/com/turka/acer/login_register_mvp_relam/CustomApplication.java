package com.turka.acer.login_register_mvp_relam;

import android.app.Application;

import es.dmoral.toasty.Toasty;
import io.realm.Realm;

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //----------------------------------Realm Init----------------------------------------------
        Realm.init(this);

//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
//                        .build());

        //----------------------------------Toast init----------------------------------------------
        Toasty.Config.getInstance().apply();
    }
}
