package uz.nasiba.avaz.memome.db.room.dao;

import android.arch.persistence.room.Dao;

import uz.nasiba.avaz.memome.db.room.entity.User;
import uz.nasiba.avaz.memome.utils.BasicDao;

@Dao
public interface UserDao extends BasicDao<User> {

}
