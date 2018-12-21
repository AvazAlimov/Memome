package uz.nasiba.avaz.memome.ui.auth.signup;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uz.nasiba.avaz.memome.db.room.entity.User;
import uz.nasiba.avaz.memome.di.AppModule;

class SignUpRepository {
    MutableLiveData<String> error = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();

    SignUpRepository() {
        loading.setValue(false);
    }

    void signup(final AppModule module, final User user) {
        loading.setValue(true);
        Call<User> call = module.getRetrofit().getAccountService().signup(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        user.setUid(response.body().getUid());
                        long id = module.getDatabase().getUserDao().insert(user);
                        Log.e("Inserted", id + "");
                    }
                } else {
                    error.setValue(response.message());
                }
                loading.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                error.setValue(t.getMessage());
                loading.setValue(false);
            }
        });
    }
}