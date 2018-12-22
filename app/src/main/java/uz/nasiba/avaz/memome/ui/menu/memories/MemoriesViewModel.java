package uz.nasiba.avaz.memome.ui.menu.memories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.ArrayList;

import uz.nasiba.avaz.memome.App;
import uz.nasiba.avaz.memome.db.room.entity.Memory;
import uz.nasiba.avaz.memome.utils.ObservableViewModel;

public class MemoriesViewModel extends ObservableViewModel {
    private MemoriesRepository repository;
    public LiveData<ArrayList<Memory>> memories;

    public MemoriesViewModel(Application application) {
        super(application);
        repository = new MemoriesRepository();
        memories = repository.memories;
    }

    //Avaz: calls load memories method
    void loadMemories() {
        repository.loadMemories(((App) getApplication()).getAppModule());
    }
}
