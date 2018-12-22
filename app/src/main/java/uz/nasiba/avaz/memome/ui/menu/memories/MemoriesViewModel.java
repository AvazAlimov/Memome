package uz.nasiba.avaz.memome.ui.menu.memories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import uz.nasiba.avaz.memome.db.room.entity.Memory;
import uz.nasiba.avaz.memome.utils.ObservableViewModel;

public class MemoriesViewModel extends ObservableViewModel {
    public LiveData<Memory> memories;

    public MemoriesViewModel(Application application) {
        super(application);
    }
}
