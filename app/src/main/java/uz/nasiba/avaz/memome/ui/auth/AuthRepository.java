package uz.nasiba.avaz.memome.ui.auth;

import android.arch.lifecycle.LiveData;

import uz.nasiba.avaz.memome.db.room.AppDatabase;
import uz.nasiba.avaz.memome.db.room.entity.User;

//Nasiba: Data management class for auth activity
class AuthRepository {
    LiveData<User> getUser(AppDatabase database) {
        return database.getUserDao().getUserAsync();
    }
}
