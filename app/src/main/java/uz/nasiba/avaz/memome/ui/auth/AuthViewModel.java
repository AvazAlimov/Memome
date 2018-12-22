package uz.nasiba.avaz.memome.ui.auth;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import uz.nasiba.avaz.memome.App;
import uz.nasiba.avaz.memome.db.room.entity.User;
import uz.nasiba.avaz.memome.utils.ObservableViewModel;

//Business logic class for auth activity
public class AuthViewModel extends ObservableViewModel {
    LiveData<User> user;

    public AuthViewModel(Application application) {
        super(application);
        AuthRepository repository = new AuthRepository();
        user = repository.getUser(((App) application).getAppModule().getDatabase());
    }
}
