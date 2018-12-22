package uz.nasiba.avaz.memome.di;

import android.arch.persistence.room.Room;

import uz.nasiba.avaz.memome.App;
import uz.nasiba.avaz.memome.db.retrofit.AppRetrofit;
import uz.nasiba.avaz.memome.db.room.AppDatabase;

public class AppModule {
    private AppDatabase database;
    private AppRetrofit retrofit;
    private App app;

    public AppModule(App app) {
        database = Room.databaseBuilder(app, AppDatabase.class, "database.db").allowMainThreadQueries().build();
        retrofit = new AppRetrofit();
        this.app = app;
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public AppRetrofit getRetrofit() {
        return retrofit;
    }

    public App getApp() {
        return app;
    }
}
