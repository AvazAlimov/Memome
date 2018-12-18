package uz.nasiba.avaz.memome.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import uz.nasiba.avaz.memome.di.module.ActivityBindings;
import uz.nasiba.avaz.memome.di.module.AppModule;
import uz.nasiba.avaz.memome.di.module.FragmentModule;
import uz.nasiba.avaz.memome.di.module.RepositoryModule;
import uz.nasiba.avaz.memome.di.module.ViewModelBindings;
import uz.nasiba.avaz.memome.di.module.ViewModelFactoryModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        ActivityBindings.class,
        AppModule.class,
        FragmentModule.class,
        ViewModelBindings.class,
        ViewModelFactoryModule.class,
        RepositoryModule.class
})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(Application app);
}
