package uz.nasiba.avaz.memome.db.retrofit.service;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import uz.nasiba.avaz.memome.db.room.entity.Memory;

//Nasiba: REST API methods for managing memories
public interface MemoryService {
    //method for creating a memory
    @Multipart
    @POST("memories/create")
    Call<Memory> create(
            @Part MultipartBody.Part account,
            @Part MultipartBody.Part title,
            @Part MultipartBody.Part content,
            @Part MultipartBody.Part date,
            @Part MultipartBody.Part[] pictures
    );

    //method for updating a memory
    @Multipart
    @POST("memories/update")
    Call<Memory> update(
            @Part MultipartBody.Part id,
            @Part MultipartBody.Part account,
            @Part MultipartBody.Part title,
            @Part MultipartBody.Part content,
            @Part MultipartBody.Part date,
            @Part MultipartBody.Part[] pictures
    );

    //method for loading a memory
    @Multipart
    @POST("memories/get")
    Call<ArrayList<Memory>> get(@Part MultipartBody.Part account);

    //method for deleting a memory
    @Multipart
    @POST("memories/delete")
    Call<Void> delete(@Part MultipartBody.Part id, @Part MultipartBody.Part account);
}
