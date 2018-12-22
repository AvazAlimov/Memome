package uz.nasiba.avaz.memome.utils;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

//Avaz: common database operations
public interface BasicDao<T> {

    //Inserts an entity
    @Insert
    Long insert(T entity);

    //Updates an entity
    @Update
    void update(T entity);

    //Deletes an entity
    @Delete
    void delete(T entity);
}
