package uz.nasiba.avaz.memome.db.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import uz.nasiba.avaz.memome.db.room.dao.UserDao;
import uz.nasiba.avaz.memome.db.room.entity.User;

@Database(entities = {
        User.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();
}
