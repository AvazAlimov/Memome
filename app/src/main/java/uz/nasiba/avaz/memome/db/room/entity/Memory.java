package uz.nasiba.avaz.memome.db.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

//Nasiba: Memory class
@Entity(tableName = "memories")
public class Memory implements Serializable {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private long id = 0;

    @Ignore
    @SerializedName("account")
    private String uid;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("content")
    @ColumnInfo(name = "content")
    private String content;

    @SerializedName("date")
    @ColumnInfo(name = "date")
    private String date;

    @Ignore
    @SerializedName("pictures")
    private ArrayList<String> pictures;

    @Ignore
    private ArrayList<Uri> uris;

    public void setId(long id) {
        this.id = id;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPictures(ArrayList<String> pictures) {
        this.pictures = pictures;
    }

    public void setUris(ArrayList<Uri> uris) {
        this.uris = uris;
    }

    public long getId() {
        return id;
    }

    public String getUid() {
        return uid;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public ArrayList<String> getPictures() {
        return pictures;
    }

    public ArrayList<Uri> getUris() {
        return uris;
    }
}
