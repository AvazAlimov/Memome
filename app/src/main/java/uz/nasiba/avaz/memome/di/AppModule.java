package uz.nasiba.avaz.memome.di;

import android.arch.persistence.room.Room;

import uz.nasiba.avaz.memome.App;
import uz.nasiba.avaz.memome.db.retrofit.AppRetrofit;
import uz.nasiba.avaz.memome.db.room.AppDatabase;

//Avaz: Dependency injection module with basic components
public class AppModule {
    private AppDatabase database;
    private AppRetrofit retrofit;
    private App app;

    public AppModule(App app) {
        database = Room.databaseBuilder(app, AppDatabase.class, "database.db").allowMainThreadQueries().build();
        retrofit = new AppRetrofit();
        this.app = app;
    }

    //returns database component
    public AppDatabase getDatabase() {
        return database;
    }

    //returns retrofit component
    public AppRetrofit getRetrofit() {
        return retrofit;
    }

    //returns application instance
    public App getApp() {
        return app;
    }
}
