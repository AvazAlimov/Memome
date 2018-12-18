package uz.nasiba.avaz.memome.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import uz.nasiba.avaz.memome.database.entity.User;

@Database(entities = {
        User.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

}
