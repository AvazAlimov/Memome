package uz.nasiba.avaz.memome.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import uz.nasiba.avaz.memome.di.scope.ActivityScope;
import uz.nasiba.avaz.memome.ui.intro.IntroActivity;

@Module
public abstract class ActivityBindings {
    @ActivityScope
    @ContributesAndroidInjector(modules = {})
    public abstract IntroActivity contributesIntroActivity();
}
