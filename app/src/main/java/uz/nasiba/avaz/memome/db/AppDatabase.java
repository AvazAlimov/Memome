package uz.nasiba.avaz.memome.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import uz.nasiba.avaz.memome.db.entity.User;

@Database(entities = {
        User.class
}, version = 1, exportSchema = false)
abstract class AppDatabase extends RoomDatabase {

}
