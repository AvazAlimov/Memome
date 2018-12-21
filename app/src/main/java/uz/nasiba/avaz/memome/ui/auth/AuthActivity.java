package uz.nasiba.avaz.memome.ui.auth;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import uz.nasiba.avaz.memome.R;
import uz.nasiba.avaz.memome.db.room.entity.User;
import uz.nasiba.avaz.memome.ui.auth.signin.SignInFragment;
import uz.nasiba.avaz.memome.ui.menu.MenuActivity;
import uz.nasiba.avaz.memome.utils.LocaleManager;

public class AuthActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AuthViewModel viewModel = ViewModelProviders
                .of(this, new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(AuthViewModel.class);

        viewModel.user.observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable User user) {
                if (user != null) {
                    Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });

        setContentView(R.layout.activity_auth);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new SignInFragment())
                .addToBackStack("signin")
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            moveTaskToBack(true);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
    }
}
