package uz.nasiba.avaz.memome;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import uz.nasiba.avaz.memome.di.AppModule;
import uz.nasiba.avaz.memome.utils.LocaleManager;

public class App extends Application {
    private AppModule appModule;

    @Override
    public void onCreate() {
        super.onCreate();
        appModule = new AppModule(this);
    }

    public AppModule getAppModule() {
        return appModule;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
    }
}
