package uz.nasiba.avaz.memome.ui.create;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.Bindable;
import android.net.Uri;

import java.util.ArrayList;

import uz.nasiba.avaz.memome.utils.ObservableViewModel;

public class CreateViewModel extends ObservableViewModel {

    @Bindable
    public MutableLiveData<String> title = new MutableLiveData<>();
    @Bindable
    public MutableLiveData<String> content = new MutableLiveData<>();
    @Bindable
    public MutableLiveData<String> date = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Uri>> pictures = new MutableLiveData<>();

    public CreateViewModel(Application application) {
        super(application);
        pictures.setValue(new ArrayList<Uri>());
    }

    public void deleteDate() {
        date.setValue(null);
    }
}
