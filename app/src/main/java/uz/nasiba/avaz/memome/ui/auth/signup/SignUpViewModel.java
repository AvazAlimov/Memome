package uz.nasiba.avaz.memome.ui.auth.signup;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.Bindable;

import java.util.Objects;

import uz.nasiba.avaz.memome.utils.ObservableViewModel;

public class SignUpViewModel extends ObservableViewModel {
    @Bindable
    public MutableLiveData<String> email = new MutableLiveData<>();

    @Bindable
    public MutableLiveData<String> password = new MutableLiveData<>();

    public SignUpViewModel() {

    }

    public void signUp() {
        if (!Objects.equals(email.getValue(), "") && !Objects.equals(password.getValue(), "")) {

        }
    }
}
