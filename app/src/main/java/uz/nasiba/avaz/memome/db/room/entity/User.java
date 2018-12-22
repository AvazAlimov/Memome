package uz.nasiba.avaz.memome.db.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

//Nasiba: user class
@Entity(tableName = "users")
public class User {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "uid")
    private String uid = "";

    @SerializedName("username")
    @ColumnInfo(name = "username")
    private String username;

    @SerializedName("password")
    @Ignore
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(@NonNull String uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    public String getPassword() {
        return password;
    }
}
