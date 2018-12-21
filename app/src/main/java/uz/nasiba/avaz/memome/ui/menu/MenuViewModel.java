package uz.nasiba.avaz.memome.ui.menu;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import uz.nasiba.avaz.memome.App;
import uz.nasiba.avaz.memome.db.room.entity.User;
import uz.nasiba.avaz.memome.utils.ObservableViewModel;

public class MenuViewModel extends ObservableViewModel {
    private MenuRepository repository;
    LiveData<User> user;

    public MenuViewModel(Application application) {
        super(application);
        repository = new MenuRepository();
        user = repository.getUser(((App) application).getAppModule().getDatabase());
    }

    void logout() {
        repository.logout(((App) getApplication()).getAppModule().getDatabase());
    }
}
