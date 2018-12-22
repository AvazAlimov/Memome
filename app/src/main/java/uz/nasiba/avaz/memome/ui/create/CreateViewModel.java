package uz.nasiba.avaz.memome.ui.create;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.Bindable;
import android.net.Uri;

import java.util.ArrayList;
import java.util.Objects;

import uz.nasiba.avaz.memome.App;
import uz.nasiba.avaz.memome.db.room.entity.Memory;
import uz.nasiba.avaz.memome.di.AppModule;
import uz.nasiba.avaz.memome.utils.ObservableViewModel;

public class CreateViewModel extends ObservableViewModel {
    long id = 0;
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

    public void save() {
        if (!Objects.equals(title.getValue(), "") && !Objects.equals(content.getValue(), "")) {
            AppModule module = ((App) getApplication()).getAppModule();
            Memory memory = new Memory();
            memory.setTitle(title.getValue());
            memory.setContent(content.getValue());
            memory.setDate(date.getValue() == null ? "" : date.getValue());
            memory.setUid(repository.getUser(module.getDatabase()).getUid());
            memory.setUris(pictures.getValue());
            if (id == 0) {
                repository.create(module, memory);
            } else {
                memory.setId(id);
                repository.update(module, memory);
            }
        }
    }
}
