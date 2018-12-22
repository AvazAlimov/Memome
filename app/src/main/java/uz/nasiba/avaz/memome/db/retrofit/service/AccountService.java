package uz.nasiba.avaz.memome.db.retrofit.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import uz.nasiba.avaz.memome.db.room.entity.User;

//Avaz: REST API methods for managing accounts
public interface AccountService {
    //method for signup an account
    @POST("accounts/signup")
    Call<User> signup(@Body User user);

    //method for signin an account
    @POST("accounts/signin")
    Call<User> signin(@Body User user);
}
