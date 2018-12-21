package uz.nasiba.avaz.memome.ui.auth.signin;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.Bindable;

import java.util.Objects;

import uz.nasiba.avaz.memome.App;
import uz.nasiba.avaz.memome.db.room.entity.User;
import uz.nasiba.avaz.memome.utils.ObservableViewModel;

public class SignInViewModel extends ObservableViewModel {
    private SignInRepository repository;
    @Bindable
    public MutableLiveData<String> username = new MutableLiveData<>();

    @Bindable
    public MutableLiveData<String> password = new MutableLiveData<>();

    LiveData<String> error;

    public SignInViewModel(Application application) {
        super(application);
        repository = new SignInRepository();
        error = repository.error;

        username.setValue("alimov");
        password.setValue("avaz23");
    }

    public void signIn() {
        App app = getApplication();
        if (!Objects.equals(username.getValue(), "") && !Objects.equals(password.getValue(), "")) {
            repository.signin(app.getAppModule(), new User(username.getValue(), password.getValue()));
        }
    }
}
