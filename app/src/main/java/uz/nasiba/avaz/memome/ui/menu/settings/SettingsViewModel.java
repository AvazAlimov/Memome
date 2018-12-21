package uz.nasiba.avaz.memome.ui.menu.settings;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;

import java.util.Locale;
import java.util.Objects;

import uz.nasiba.avaz.memome.utils.LocaleManager;
import uz.nasiba.avaz.memome.utils.ObservableViewModel;

public class SettingsViewModel extends ObservableViewModel {
    public MutableLiveData<String> language = new MutableLiveData<>();

    public SettingsViewModel(Application application) {
        super(application);
        language.setValue(Locale.getDefault().getLanguage());
    }

    public void changeLanguage(String code) {
        boolean newLanguage = !Locale.getDefault().getLanguage().equals(code);
        LocaleManager.setNewLocale(getApplication(), code);
        if (newLanguage) {
            getApplication()
                    .startActivity(Objects.requireNonNull(getApplication()
                            .getPackageManager().getLaunchIntentForPackage(getApplication().getPackageName()))
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    );
        }
    }
}
