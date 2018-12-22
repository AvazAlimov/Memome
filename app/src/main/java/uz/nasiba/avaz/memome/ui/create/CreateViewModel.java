package uz.nasiba.avaz.memome.ui.create;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.database.Cursor;
import android.databinding.Bindable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Objects;

import uz.nasiba.avaz.memome.App;
import uz.nasiba.avaz.memome.db.room.entity.Memory;
import uz.nasiba.avaz.memome.di.AppModule;
import uz.nasiba.avaz.memome.utils.ObservableViewModel;

public class CreateViewModel extends ObservableViewModel {
    private CreateRepository repository;
    @Bindable
    public MutableLiveData<String> title = new MutableLiveData<>();
    @Bindable
    public MutableLiveData<String> content = new MutableLiveData<>();
    @Bindable
    public MutableLiveData<String> date = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Uri>> pictures = new MutableLiveData<>();
    LiveData<Boolean> finished;
    LiveData<String> error;

    public CreateViewModel(Application application) {
        super(application);
        title.setValue("");
        content.setValue("");
        repository = new CreateRepository();
        error = repository.error;
        finished = repository.finished;
        pictures.setValue(new ArrayList<Uri>());
    }

    public void deleteDate() {
        date.setValue(null);
    }

    public void create() {
        if (!Objects.equals(title.getValue(), "") && !Objects.equals(content.getValue(), "")) {
            AppModule module = ((App) getApplication()).getAppModule();
            Memory memory = new Memory();
            memory.setTitle(title.getValue());
            memory.setContent(content.getValue());
            memory.setDate(date.getValue() == null ? "" : date.getValue());
            memory.setUid(repository.getUser(module.getDatabase()).getUid());
            memory.setUris(pictures.getValue());
            repository.create(module, memory);
        }
    }
}
