package uz.nasiba.avaz.memome.db.retrofit.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import uz.nasiba.avaz.memome.db.room.entity.User;

public interface AccountService {
    @POST("accounts/signup")
    Call<User> signup(@Body User user);
}
