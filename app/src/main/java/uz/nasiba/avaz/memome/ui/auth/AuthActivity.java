package uz.nasiba.avaz.memome.ui.auth;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import uz.nasiba.avaz.memome.R;
import uz.nasiba.avaz.memome.ui.auth.signin.SignInFragment;

public class AuthActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
}
