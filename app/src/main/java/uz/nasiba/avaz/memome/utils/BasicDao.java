package uz.nasiba.avaz.memome.utils;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

public interface BasicDao<T> {
    @Insert
    Long insert(T entity);

    @Update
    void update(T entity);

    @Delete
    void delete(T entity);
}
