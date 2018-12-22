package uz.nasiba.avaz.memome.db.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import uz.nasiba.avaz.memome.db.room.entity.User;
import uz.nasiba.avaz.memome.utils.BasicDao;

//Nasiba: user management methods in database
@Dao
public interface UserDao extends BasicDao<User> {
    //return dynamic user
    @Query("SELECT * FROM users LIMIT 1")
    LiveData<User> getUserAsync();

    //return static user
    @Query("SELECT * FROM users LIMIT 1")
    User getUser();

    //delete a user
    @Query("DELETE FROM users")
    void drop();
}
