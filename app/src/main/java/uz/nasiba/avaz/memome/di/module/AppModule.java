package uz.nasiba.avaz.memome.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import uz.nasiba.avaz.memome.database.AppDatabase;

@Module
public class AppModule {
    @Provides
    @Singleton
    public Context providesApplication(Application application) {
        return application;
    }

    @Provides
    @Singleton
    public AppDatabase providesDatabase(Application application) {
        return Room.databaseBuilder(application, AppDatabase.class, "database.db").fallbackToDestructiveMigration().build();
    }
}
