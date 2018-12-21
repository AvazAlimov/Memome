package uz.nasiba.avaz.memome.ui.auth.signin;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uz.nasiba.avaz.memome.db.room.entity.User;
import uz.nasiba.avaz.memome.di.AppModule;

class SignInRepository {
    MutableLiveData<String> error = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();

    SignInRepository() {
        loading.setValue(false);
    }

    void signin(final AppModule module, final User user) {
        loading.setValue(true);
        Call<User> call = module.getRetrofit().getAccountService().signin(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        user.setUid(response.body().getUid());
                        module.getDatabase().getUserDao().insert(user);
                    }
                } else {
                    error.setValue(response.message());
                }
                loading.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                loading.setValue(false);
                error.setValue(t.getMessage());
            }
        });
    }
}
