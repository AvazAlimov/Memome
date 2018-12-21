package uz.nasiba.avaz.memome.ui.menu;

import android.arch.lifecycle.LiveData;

import uz.nasiba.avaz.memome.db.room.AppDatabase;
import uz.nasiba.avaz.memome.db.room.entity.User;

class MenuRepository {
    LiveData<User> getUser(AppDatabase database) {
        return database.getUserDao().getUser();
    }

    void logout(AppDatabase database) {
        database.getUserDao().drop();
    }
}
