package uz.nasiba.avaz.memome.db.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import uz.nasiba.avaz.memome.db.room.entity.User;
import uz.nasiba.avaz.memome.utils.BasicDao;

@Dao
public interface UserDao extends BasicDao<User> {
    @Query("SELECT * FROM users LIMIT 1")
    LiveData<User> getUser();

    @Query("DELETE FROM users")
    void drop();
}
