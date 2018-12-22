package uz.nasiba.avaz.memome;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import uz.nasiba.avaz.memome.di.AppModule;
import uz.nasiba.avaz.memome.utils.LocaleManager;

//Avaz: Entry point of a project
public class App extends Application {
    private AppModule appModule;

    //Avaz: create app along with dependencies
    @Override
    public void onCreate() {
        super.onCreate();
        appModule = new AppModule(this);
    }

    //Avaz: returns dependency module
    public AppModule getAppModule() {
        return appModule;
    }

    //Nasiba: load a saved locale by user
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }

    //Nasiba: set a new locale
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
    }
}
