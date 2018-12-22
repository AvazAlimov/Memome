package uz.nasiba.avaz.memome.ui.menu.memories;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uz.nasiba.avaz.memome.db.room.entity.Memory;
import uz.nasiba.avaz.memome.db.room.entity.User;
import uz.nasiba.avaz.memome.di.AppModule;

class MemoriesRepository {
    MutableLiveData<ArrayList<Memory>> memories = new MutableLiveData<>();

    void loadMemories(AppModule appModule) {
        User user = appModule.getDatabase().getUserDao().getUser();
        if (user != null) {
            Call<ArrayList<Memory>> call = appModule.getRetrofit().getMemoryService().get(MultipartBody.Part.createFormData("account", user.getUid()));
            call.enqueue(new Callback<ArrayList<Memory>>() {
                @Override
                public void onResponse(@NonNull Call<ArrayList<Memory>> call, @NonNull Response<ArrayList<Memory>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        memories.setValue(response.body());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ArrayList<Memory>> call, @NonNull Throwable t) {

                }
            });
        }
    }
}
