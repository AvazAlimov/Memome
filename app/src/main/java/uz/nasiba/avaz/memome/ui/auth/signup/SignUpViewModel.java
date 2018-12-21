package uz.nasiba.avaz.memome.ui.auth.signup;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.Bindable;

import java.util.Objects;

import uz.nasiba.avaz.memome.App;
import uz.nasiba.avaz.memome.db.room.entity.User;
import uz.nasiba.avaz.memome.utils.ObservableViewModel;

public class SignUpViewModel extends ObservableViewModel {
    private SignUpRepository repository;
    @Bindable
    public MutableLiveData<String> username = new MutableLiveData<>();

    @Bindable
    public MutableLiveData<String> password = new MutableLiveData<>();
    public LiveData<Boolean> loading;
    LiveData<String> error;

    public SignUpViewModel(Application application) {
        super(application);
        repository = new SignUpRepository();
        error = repository.error;
        loading = repository.loading;
    }

    public void signUp() {
        App app = getApplication();
        if (!Objects.equals(username.getValue(), "") && !Objects.equals(password.getValue(), "")) {
            repository.signup(app.getAppModule(), new User(username.getValue(), password.getValue()));
        }
    }
}
