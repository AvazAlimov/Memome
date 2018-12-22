package uz.nasiba.avaz.memome.ui.create;

import android.arch.lifecycle.MutableLiveData;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uz.nasiba.avaz.memome.db.room.AppDatabase;
import uz.nasiba.avaz.memome.db.room.entity.Memory;
import uz.nasiba.avaz.memome.db.room.entity.User;
import uz.nasiba.avaz.memome.di.AppModule;

class CreateRepository {
    MutableLiveData<String> error = new MutableLiveData<>();
    MutableLiveData<Boolean> finished = new MutableLiveData<>();

    CreateRepository() {
        finished.setValue(false);
    }

    User getUser(AppDatabase database) {
        return database.getUserDao().getUser();
    }

    void create(AppModule appModule, Memory memory) {
        MultipartBody.Part account = MultipartBody.Part.createFormData("account", memory.getUid());
        MultipartBody.Part title = MultipartBody.Part.createFormData("title", memory.getTitle());
        MultipartBody.Part content = MultipartBody.Part.createFormData("content", memory.getContent());
        MultipartBody.Part date = MultipartBody.Part.createFormData("date", memory.getDate());

        MultipartBody.Part[] pictures = null;
        if (memory.getUris() != null) {
            pictures = new MultipartBody.Part[memory.getUris().size()];
            for (int i = 0; i < memory.getUris().size(); i++) {
                try {
                    File file = File.createTempFile("img", "tmp");
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(appModule.getApp().getContentResolver(), memory.getUris().get(i));
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
                    bitmap.recycle();
                    RequestBody body = RequestBody.create(MediaType.parse("image"), file);
                    pictures[i] = (MultipartBody.Part.createFormData("pictures[" + i + "]", memory.getUris().get(i).getPath(), body));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Call<Memory> call = appModule.getRetrofit().getMemoryService().create(account, title, content, date, pictures);
        call.enqueue(new Callback<Memory>() {
            @Override
            public void onResponse(@NonNull Call<Memory> call, @NonNull Response<Memory> response) {
                if (response.isSuccessful()) {
                    finished.setValue(true);
                } else {
                    error.setValue(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Memory> call, @NonNull Throwable t) {
                error.setValue(t.getMessage());
            }
        });
    }

    void update(AppModule appModule, Memory memory) {

    }
}
