package uz.nasiba.avaz.memome.ui.auth.signin;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uz.nasiba.avaz.memome.db.room.entity.User;
import uz.nasiba.avaz.memome.di.AppModule;

class SignInRepository {
    MutableLiveData<String> error = new MutableLiveData<>();

    void signin(final AppModule module, final User user) {
        Log.e("signin: ", "Processing");
        Call<User> call = module.getRetrofit().getAccountService().signin(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        user.setUid(response.body().getUid());
                        long id = module.getDatabase().getUserDao().insert(user);
                        Log.e("onResponse: ", id + "");
                    }
                } else {
                    error.setValue(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {

            }
        });
    }
}
