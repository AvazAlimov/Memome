package uz.nasiba.avaz.memome.ui.menu;

import android.arch.lifecycle.LiveData;

import uz.nasiba.avaz.memome.db.room.AppDatabase;
import uz.nasiba.avaz.memome.db.room.entity.User;

class MenuRepository {
    //get signed in account info
    LiveData<User> getUser(AppDatabase database) {
        return database.getUserDao().getUserAsync();
    }

    //Avaz: logout method
    void logout(AppDatabase database) {
        database.getUserDao().drop();
    }
}
