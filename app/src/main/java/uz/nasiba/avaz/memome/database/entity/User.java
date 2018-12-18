package uz.nasiba.avaz.memome.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "users")
public class User {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "uid")
    public String uid;
}
